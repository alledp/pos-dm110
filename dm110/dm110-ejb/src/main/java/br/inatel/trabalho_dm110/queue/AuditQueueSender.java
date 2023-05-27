package br.inatel.trabalho_dm110.queue;


import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;


@Stateless
public class AuditQueueSender {

	
	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;
	
	@Resource(lookup = "java:/jms/queue/trabalho_dm110queue")
	private Queue queue;
	
	public void sendTextMessage(String text) {
		try {
			
			Connection conn = connectionFactory.createConnection();
			Session session = conn.createSession();
			MessageProducer msgProducer = session.createProducer(queue);
			TextMessage txtMsg = session.createTextMessage(text);
			msgProducer.send(txtMsg);
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
