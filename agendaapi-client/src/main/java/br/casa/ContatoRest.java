package br.casa;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;








public class ContatoRest implements ContatoDaoInterface{
	
	private static final String URL = "http://localhost:8000/api/agenda";

	public ContatoRest() throws Exception {
		
		configura();

	}
	private void configura() {
		
		Unirest.setObjectMapper(new ObjectMapper() {
			
			private com.fasterxml.jackson.databind.ObjectMapper mapper = 
					new com.fasterxml.jackson.databind.ObjectMapper();

			public <T> T readValue(String value, Class<T> valueType) {
				try {
					return mapper.readValue(value, valueType);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}

			public String writeValue(Object value) {
				try {
					return mapper.writeValueAsString(value);
				} catch (JsonProcessingException e) {
					throw new RuntimeException(e);
				}
			}
		});

	}
	
	@Override
	public List<Contato> getTodos() throws Exception {
	
		HttpResponse<Contato[]> resp = Unirest
				.get(URL)
				.asObject(Contato[].class);
		
		Contato[] alunos = resp.getBody();
		return Arrays.asList(alunos);
	}

	@Override	
	public Contato insere(Contato c) throws Exception {	
		HttpResponse<Contato> resp = Unirest
				.post(URL)
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.body(c)
				.asObject(Contato.class);

		int status = resp.getStatus();
		System.out.println("status: " + status);
		
		Contato alunoCriado = resp.getBody();
		return alunoCriado;

	
	
		
	}

	@Override	
	public Contato atualiza(int id,Contato c) throws Exception {	
		HttpResponse<Contato> resp = Unirest
				.put(URL + "/{id}")
				.routeParam("id", String.valueOf(c.getId()))
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.body(c)
				.asObject(Contato.class);

		int status = resp.getStatus();
		
		System.out.println("status: " + status);
		
		Contato contatoAtualizado = resp.getBody();
		return contatoAtualizado;
		
	}

	@Override	
	public void exclui(int id) throws Exception {	
		HttpResponse<String> resp = Unirest
				.delete(URL + "/{id}")
				.routeParam("id", String.valueOf(id))
				.asString();

		System.out.println("status:" + resp.getStatus());
		
	}

}
