package aplicacao;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastroProduto extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel label;
	private JLabel label_1;
	private JButton button;

	private String nome;
	private double preco;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroProduto frame = new TelaCadastroProduto();
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
	public TelaCadastroProduto() {
		setTitle("Cadastrar Produto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 380, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(136, 49, 141, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(136, 94, 63, 26);
		contentPane.add(textField_1);
		
		label = new JLabel("Nome: ");
		label.setBounds(80, 55, 46, 14);
		contentPane.add(label);
		
		label_1 = new JLabel("Pre\u00E7o: ");
		label_1.setBounds(80, 100, 46, 14);
		contentPane.add(label_1);
		
		button = new JButton("Cadastrar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nome = textField.getText();
				preco = Double.parseDouble(textField_1.getText());
				
				if (nome.equals(""))  {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
				}else if(preco < 0) {
					JOptionPane.showMessageDialog(null, "Preço Inválido");
				}else {
					try {
						Fachada.cadastrarProduto(nome, preco);
						JOptionPane.showMessageDialog(null, "Cadastro Realizado!");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				textField.setText("");
				textField_1.setText("");
				}
			}
		});
		button.setBounds(110, 150, 141, 26);
		contentPane.add(button);
	}

}
