package aplicacao;

import modelo.Pedido;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class TelaConsultarPedido extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel label;
	private JButton button;
	private JTextArea textArea;

	private String texto, telefone;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsultarPedido frame = new TelaConsultarPedido();
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
	public TelaConsultarPedido() {
		setResizable(false);
		setTitle("Consultar Pedido");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 417, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(106, 9, 105, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		label = new JLabel("N\u00BA. Telefone: ");
		label.setBounds(26, 11, 86, 14);
		contentPane.add(label);
		
		button = new JButton("Consultar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telefone = textField.getText();
				texto = "";
				ArrayList<Pedido> pedidos;
				
				if(telefone.equals("")) {
					JOptionPane.showMessageDialog(null, "Campo Vazio!");
				}
				try {
					  pedidos = Fachada.consultarPedido(telefone);
					  texto = "Listagem de pedidos do cliente: \n\n" ;
						  for(Pedido p: pedidos) {
							  if(p.isFechado()) {
							  texto += "Pedido: " + p.getId() +", Produtos: "+p.getProdutos()+ ", Status: Fechado "+ "\n";
						  }else {
							  texto += "Pedido: " + p.getId() +", Produtos: "+p.getProdutos()+ ", Status: Aberto "+ "\n";
						  }

					  textArea.setText(texto);
				 }} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(257, 8, 89, 23);
		contentPane.add(button);
		
		textArea = new JTextArea();
		textArea.setBounds(36, 52, 343, 144);
		contentPane.add(textArea);
	}

}
