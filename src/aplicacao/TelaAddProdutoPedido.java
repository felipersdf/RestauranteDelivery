package aplicacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Produto;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class TelaAddProdutoPedido extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel label;
	private JLabel label_1;
	private JButton button;

	private String telefone, nomeProduto;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAddProdutoPedido frame = new TelaAddProdutoPedido();
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
	public TelaAddProdutoPedido() {
		setTitle("Adicionar Produto em Pedido");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 359, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(134, 35, 137, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(134, 83, 137, 28);
		contentPane.add(textField_1);
		
		label = new JLabel("Produto: ");
		label.setBounds(48, 87, 128, 21);
		contentPane.add(label);
		
		label_1 = new JLabel("Telefone: ");
		label_1.setBounds(48, 42, 128, 21);
		contentPane.add(label_1);
		
		button = new JButton("Adicionar Produto");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telefone = textField.getText();
				nomeProduto = textField_1.getText();
				ArrayList<Produto> produtos;
				
				if(telefone.equals("") || nomeProduto.equals(""))
					JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
				
				produtos = Fachada.listarProdutos(nomeProduto);
				
				for(Produto p: produtos) {
					if(p.getNome().equals(nomeProduto)) {
						try {
							Fachada.adicionarProdutoPedido(telefone, p.getId());
							JOptionPane.showMessageDialog(null, "Produto Adicionado!");
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				textField.setText("");
				textField_1.setText("");
				
			}
		});
		button.setBounds(80, 140, 168, 34);
		contentPane.add(button);
	}

}
