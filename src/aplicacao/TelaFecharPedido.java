package aplicacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaFecharPedido extends JFrame {

	private JPanel contentPane;
	private JLabel label;
	private JLabel label_1;
	private JTextField textField;
	private JTextField textField_1;
	private JButton button;

	private String entregador, telefone;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFecharPedido frame = new TelaFecharPedido();
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
	public TelaFecharPedido() {
		setTitle("Fechar Pedido");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 248, 182);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("Telefone: ");
		label.setBounds(21, 26, 84, 14);
		contentPane.add(label);
		
		label_1 = new JLabel("Entregador: ");
		label_1.setBounds(10, 51, 105, 14);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(83, 23, 112, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(83, 48, 112, 20);
		contentPane.add(textField_1);
		
		button = new JButton("Fechar Pedido");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telefone = textField.getText();
				entregador = textField_1.getText();
				
				if(telefone.equals("") || entregador.equals("")) {
					JOptionPane.showMessageDialog(null,"Preencha todos os campos!");
				}
				try {
					Fachada.fecharPedido(telefone, entregador);
					JOptionPane.showMessageDialog(null, "Pedido fechado com sucesso!");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textField.setText("");
				textField_1.setText("");
				
			}
		});
		button.setBounds(50, 84, 121, 35);
		contentPane.add(button);
	}

}
