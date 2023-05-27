package br.inatel.trabalho_dm110.queueMDB;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.google.gson.Gson;

import br.inatel.trabalho_dm110.api.supplier.AuditTO;
import br.inatel.trabalho_dm110.interfaces.supplier.AuditLocal;


@MessageDriven(activationConfig = { 
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"), 
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/trabalho_dm110queue")})

public class QueueMDB implements MessageListener {
	
	private static Logger log = Logger.getLogger(QueueMDB.class.getName());
	
	@EJB
	private AuditLocal auditBean;

	@Override
	public void onMessage(Message message) {

		try {
			if (message instanceof TextMessage) {
			TextMessage txtMessage = (TextMessage) message; 
			String text = txtMessage.getText(); 
			log.info("Message Received in the Queue: " + text);
			
			// TRANSFORMAR A MENSAGEM DE TEXTO NO FORMATO DO AUDIT
			
			Gson gson = new Gson();
			final AuditTO auditMessage = gson.fromJson(text,AuditTO.class);
			
			log.info("INFORMATION TO SAVE ON AUDIT_TABLE: " + auditMessage);
			auditBean.createNewAuditLog(auditMessage);
			}
			
		} catch (JMSException e) { 
			e.printStackTrace();
		}
	}
}
