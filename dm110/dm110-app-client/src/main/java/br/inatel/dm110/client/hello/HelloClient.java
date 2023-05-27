package br.inatel.dm110.client.hello;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.inatel.dm110.api.example.MessageTO;

public class HelloClient {

	private static final String REST_URI_GET = "http://localhost:8080/dm110-web/api/hello/message";
	private static final String REST_URI_GET_ALL = "http://localhost:8080/dm110-web/api/hello/messages";
	private static final String REST_URI_POST = "http://localhost:8080/dm110-web/api/hello/message";

	private static Client client = ClientBuilder.newClient();

	public static void main(String[] args) {
		System.out.println("Message service:");
		
		MessageTO msg = new MessageTO();
		msg.setFirstName("Jonas");
		msg.setLastName("Oliveira");
		
		System.out.println("Result from POST: " + createMessage(msg));

		System.out.println("Result from GET 0: " + getMessage(0));
		System.out.println("Result from GET 1: " + getMessage(1));
		System.out.println("Result from GET 2: " + getMessage(2));
		
		System.out.println("Result of all messages: " + getAllMessages());
	}

	public static MessageTO getMessage(Integer id) {
		return client
				.target(REST_URI_GET)
				.path(String.valueOf(id))
				.request(MediaType.APPLICATION_JSON)
				.get(MessageTO.class);
	}
	
	public static Response createMessage(MessageTO msg) {
	    return client
	      .target(REST_URI_POST)
	      .request(MediaType.APPLICATION_JSON)
	      .post(Entity.entity(msg, MediaType.APPLICATION_JSON));
	}
	
	public static List<MessageTO> getAllMessages() {
		List<MessageTO> list = client
                .target(REST_URI_GET_ALL)
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class)
                .readEntity(new GenericType<List<MessageTO>>() {});
		return list;
	}
}
