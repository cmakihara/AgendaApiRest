package br.casa;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;



public class TelaPrincipal extends TelaPrincipalBase{
	Connection con = ConexaoDB.getInstance().getConnection();
	private ContatoModel modelo;
	private Contato contatoSelecionado;
	
	public TelaPrincipal() throws Exception {
	
	super();
	limparCampos();
	configuraBotao();
	configuraTabela();
	
	
	
	}
	
	public void carregarLinha(int idx){
		Contato c = this.modelo.getContato(idx);
		
		this.contatoSelecionado =c;
		this.labelAlerta.setText(Carregando);
		super.txfId.setText(String.valueOf(c.getId()));
		super.txfNome.setText(c.getNome());
		super.txfTelefone.setText(c.getTelefone());
		super.txfEndereco.setText(c.getEndereco());
		super.txfEmail.setText(c.getEmail());
		super.txfCelular.setText(c.getCelular());
		
		super.btnExcluir.setEnabled(true);		
	}
	
	private void configuraTabela() throws Exception {
		
		//ContatoDao dao = new ContatoDao();
		ContatoRest dao = new ContatoRest();
		
		List<Contato> lista = dao.getTodos();
		
		this.modelo = new ContatoModel(lista);
		super.table.setModel(modelo);
		super.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					int index = table.getSelectedRow();
					if(index < 0){
						System.out.println("Noa");
					}else{
						System.out.println("linha "+index);
						carregarLinha(index);
						
						
					}
				}
			}
		});
		
		
		
		
	}
	private void configuraBotao() {
		super.btnNovo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				novo();
				
			}
		});
		super.btnSalvar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					salvar();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		super.btnExcluir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					excluir();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		super.btnAtualiza.addActionListener(new ActionListener() {
						
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					atualiza();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
	protected void atualiza() throws Exception{
		ContatoRest dao = new ContatoRest();
		Contato c = new Contato();
		
		c.setNome(txfNome.getText());
		c.setTelefone(txfTelefone.getText().trim());
		c.setEndereco(txfEndereco.getText().trim());
		c.setEmail(txfEmail.getText().trim());	
		c.setCelular(txfCelular.getText().trim());
		dao.atualiza(contatoSelecionado.getId(),c);
		configuraTabela();
		limparCampos();
		
	}

	protected void excluir() throws Exception {
		ContatoRest dao = new ContatoRest();
		Contato c = new Contato();
		dao.exclui(contatoSelecionado.getId());
		configuraTabela();
		limparCampos();
		
	}
	protected void salvar() throws Exception {
		ContatoRest dao = new ContatoRest();
		//ContatoDao contatoDao = new ContatoDao();
		
		Contato c = new Contato();
		c.setNome(txfNome.getText());
		c.setTelefone(txfTelefone.getText().trim());
		c.setEndereco(txfEndereco.getText().trim());
		c.setEmail(txfEmail.getText().trim());	
		c.setCelular(txfCelular.getText().trim());	
				
		dao.insere(c);
		configuraTabela();
		
	}
	protected void novo() {
		this.contatoSelecionado = null;
		limparCampos();
		
	}
	private void limparCampos() {
		super.txfId.setText("");
		super.labelAlerta.setText("");
		super.txfNome.setText("");
		super.txfTelefone.setText("");
		super.txfEndereco.setText("");
		super.txfEmail.setText("");
		super.txfCelular.setText("");
		
		super.btnExcluir.setEnabled(false);
		
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
