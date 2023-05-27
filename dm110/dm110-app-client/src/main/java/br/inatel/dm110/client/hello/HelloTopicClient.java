package br.inatel.dm110.client.hello;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class HelloTopicClient {
	private static final Logger log = Logger.getLogger(HelloTopicClient.class.getName());

	private static final String INITIAL_CONTEXT_FACTORY = "org.wildfly.naming.client.WildFlyInitialContextFactory";
	private static final String PROVIDER_URL = "http-remoting://127.0.0.1:8080";
	private static final String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String DESTINATION = "jms/topic/dm110topic";
	private static final String USERNAME = "jmsuser";
	private static final String PASSWORD = "senhajms";

	public static void main(String[] args) {
		sendMessage("Olá de Topic Client.");
	}

	public static void sendMessage(String message) {

		Context initialContext = null;

		try {
			initialContext = getContext();

			// Faz lookup da factory
			ConnectionFactory connectionFactory = (ConnectionFactory) initialContext.lookup(CONNECTION_FACTORY);
			System.out.println("connectionFactory ok");

			// Faz lookup do tópico
			Destination destination = (Destination) initialContext.lookup(DESTINATION);
			log.info("destination ok");

			try (JMSContext context = connectionFactory.createContext(USERNAME, PASSWORD)) {
				log.info("context ok");
				
				JMSProducer producer = context.createProducer();
				log.info("producer ok");

				log.info("Enviando mensagem: " + message);
				producer.send(destination, message);
				System.out.println("Enviou mensagem com sucesso ao tópico.");

//				// Create the JMS consumer
//				JMSConsumer consumer = context.createConsumer(DESTINATION);
//				// Receive the message that were sent
//				String text = consumer.receiveBody(String.class, 5000);
//				log.info("Received message with content " + text);
			}
		} catch (NamingException e) {
			log.log(Level.SEVERE, "Erro enviando mensagem ao tópico.", e);
		} finally {
			if (initialContext != null) {
				try {
					initialContext.close();
				} catch (NamingException e) {
					log.severe(e.getMessage());
				}
			}
		}
	}

	private static Context getContext() throws NamingException {
		Context initialContext = null;

		// Set up the namingContext for the JNDI lookup
		final Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
		env.put(Context.PROVIDER_URL, PROVIDER_URL);
		env.put(Context.SECURITY_PRINCIPAL, USERNAME);
		env.put(Context.SECURITY_CREDENTIALS, PASSWORD);
		
		initialContext = new InitialContext(env);
		return initialContext;
	}
}