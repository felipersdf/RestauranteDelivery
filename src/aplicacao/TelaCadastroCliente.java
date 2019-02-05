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

public class TelaCadastroCliente extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JButton button;

	private String nome, telefone, email, endereco;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroCliente frame = new TelaCadastroCliente();
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
	public TelaCadastroCliente() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Cadastrar Cliente");
		setBounds(100, 100, 350, 223);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(123, 22, 139, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(123, 53, 139, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(123, 84, 139, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(123, 115, 139, 20);
		contentPane.add(textField_3);
		
		label = new JLabel("Nome: ");
		label.setBounds(67, 25, 46, 14);
		contentPane.add(label);
		
		label_1 = new JLabel("Telefone: ");
		label_1.setBounds(62, 55, 63, 17);
		contentPane.add(label_1);
		
		label_2 = new JLabel("Email: ");
		label_2.setBounds(67, 87, 46, 14);
		contentPane.add(label_2);
		
		label_3 = new JLabel("Endere\u00E7o: ");
		label_3.setBounds(51, 118, 82, 14);
		contentPane.add(label_3);
		
		button = new JButton("Cadastrar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  nome = textField.getText();
				  telefone = textField_1.getText();
				  email = textField_2.getText();
				  endereco = textField_3.getText();
				  
				  if(nome.equals("") || telefone.equals("") || email.equals("") || endereco.equals("")) {
						JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
					}else {	
						
						try {
							Fachada.cadastrarCliente(nome, telefone, email, endereco);
							JOptionPane.showMessageDialog(null, "Cadastro Realizado!");
							} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						textField.setText(null);
						textField_1.setText(null);
						textField_2.setText(null);
						textField_3.setText(null);
						
					}
			}
		});
		button.setBounds(105, 146, 112, 27);
		contentPane.add(button);
		
		
	}

}
