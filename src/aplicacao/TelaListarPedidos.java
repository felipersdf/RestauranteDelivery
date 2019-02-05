package aplicacao;

import java.util.*;
import modelo.Pedido;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaListarPedidos extends JFrame {

	private JPanel contentPane;
	private JButton button;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	
	private String texto="";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListarPedidos frame = new TelaListarPedidos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaListarPedidos() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		button = new JButton("Listar Pedidos");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Pedido> pedidos = Fachada.listarPedidos();
				
				for(Pedido p: pedidos){
					texto += "Nº Pedido: " + p.getId() + ", Cliente: " + p.getCliente().getNome() +", Qtd de Produtos: "+p.getProdutos().size()+ ", Produtos: " + p.getProdutos()+"\n";
				}
				textArea.setText(texto);
				
			}
		});
		button.setBounds(156, 11, 122, 23);
		contentPane.add(button);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 50, 403, 178);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}

}
