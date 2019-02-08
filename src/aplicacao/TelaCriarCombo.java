package aplicacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Combo;
import modelo.Produto;

import javax.swing.JLabel;
import java.awt.TextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCriarCombo extends JFrame {

	private JPanel contentPane;
	private JLabel label;
	private TextArea textArea;
	private JButton button;
	private JLabel label_1;
	private JTextField textField;
	private JLabel label_2;
	private JTextField textField_1;
	private JLabel label_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCriarCombo frame = new TelaCriarCombo();
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
	public TelaCriarCombo() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				ArrayList<Produto> prods = Fachada.listarProdutos();
				String iddisp = "";
				
				for(Produto p: prods) {
					iddisp += p.getNome() + ", " + p.getId() + "\n";
				}
				textArea.setText(iddisp);	
 			}
		});
		setTitle("Criar Combo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 343, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("Produtos Dispon\u00EDveis");
		label.setBounds(100, 11, 127, 14);
		contentPane.add(label);
		
		textArea = new TextArea();
		textArea.setBounds(20, 31, 279, 51);
		contentPane.add(textArea);
		
		button = new JButton("Criar Combo");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ids = "";
				String nome = "";
				ArrayList<Integer> lista = new ArrayList<>();
				Combo c;
				ids = textField.getText();
				nome = textField_1.getText();
				
				if(ids.equals("") || nome.equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
				}else {
					
					String idArray[] = ids.split(",");
					for(String i: idArray) {
						lista.add(Integer.parseInt(i));
					}
					c = Fachada.criarCombo(nome, lista);
					JOptionPane.showMessageDialog(null,"Combo criado com sucesso!");
					
				}
				
			}
		});
		button.setBounds(100, 161, 127, 32);
		contentPane.add(button);
		
		label_1 = new JLabel("Informe os Ids dos produtos dispon\u00EDveis  (Ex.: 1,2)");
		label_1.setBounds(20, 83, 323, 23);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(192, 117, 72, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		label_2 = new JLabel("Nome: ");
		label_2.setBounds(10, 117, 56, 14);
		contentPane.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(48, 117, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		label_3 = new JLabel("Id's: ");
		label_3.setBounds(161, 117, 30, 14);
		contentPane.add(label_3);
	}
}
