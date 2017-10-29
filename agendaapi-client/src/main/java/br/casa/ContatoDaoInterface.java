package br.casa;

import java.util.List;

import com.mashape.unirest.http.exceptions.UnirestException;

public interface ContatoDaoInterface {

	List<Contato> getTodos() throws Exception;

	Contato insere(Contato c) throws Exception;

	Contato atualiza(int id, Contato c)throws Exception;

	void exclui(int id) throws UnirestException, Exception;

}