package br.casa;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ContatoModel extends AbstractTableModel{
	
	private List<Contato> lista;
	
	
	public ContatoModel(List<Contato> lista2) {
		if(lista2 == null){
			this.lista = new ArrayList<Contato>();
		}else{
			this.lista=lista2;
		}
	}

	public int getColumnCount() {
		
		return 6;
	}

	public int getRowCount() {
		
		return lista.size();
	}

	
	
	@Override
	public String getColumnName(int column) {
		
		switch(column){
		case 0:
			return "Id";
		case 1:
			return "Nome";
		case 2:
			return "Telefone";
		case 3:
			return "endereco";
		case 4:
			return "email";
		case 5:
			return "celular";
		
		}
		
		
		return super.getColumnName(column);
	}

	public Object getValueAt(int row, int column) {
		Contato c = this.lista.get(row);
		switch (column){
		case 0:
			return c.getId();
		case 1:
			return c.getNome();
		case 2:
			return c.getTelefone();
		case 3:
			return c.getEndereco();
		case 4:
			return c.getEmail();
		case 5:
			return c.getCelular();
		
		}		
		
		return null;
	}
	public void adicionar(Contato c){
		
		this.lista.add(c);
		super.fireTableDataChanged();
		
	}
	public Contato getContato(int index){
		
		
		return lista.get(index);
		
	}
	public void remover(Contato contatoSelecionado){
		this.lista.remove(contatoSelecionado);
		super.fireTableDataChanged();
	}

}
