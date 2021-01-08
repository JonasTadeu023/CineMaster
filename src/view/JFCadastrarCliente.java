package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import model.bean.Cliente;
import model.dao.ClienteDAO;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class JFCadastrarCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_clienteNome;
	private JTextField txt_clienteCPF;
	private JTextField txt_clienteEmail;
	private JTextField txt_cleinteEndereco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFCadastrarCliente frame = new JFCadastrarCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public JFCadastrarCliente() throws ParseException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastrar cliente ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 10, 199, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 58, 74, 21);
		contentPane.add(lblNewLabel_1);
		
		txt_clienteNome = new JTextField();
		txt_clienteNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_clienteNome.setBounds(10, 89, 425, 19);
		contentPane.add(txt_clienteNome);
		txt_clienteNome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("CPF");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(445, 58, 123, 21);
		contentPane.add(lblNewLabel_2);
		
		txt_clienteCPF = new JTextField();
		txt_clienteCPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_clienteCPF.setBounds(445, 89, 221, 19);
		contentPane.add(txt_clienteCPF);
		txt_clienteCPF.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(10, 178, 74, 21);
		contentPane.add(lblNewLabel_1_1);
		
		MaskFormatter mf1 = new MaskFormatter("(##) #####-####");
	    mf1.setPlaceholderCharacter('_');
		JFormattedTextField txt_clienteTelefone = new JFormattedTextField();
		txt_clienteTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_clienteTelefone.setToolTipText("");
		txt_clienteTelefone.setBounds(445, 209, 221, 19);
		contentPane.add(txt_clienteTelefone);
		
		
		txt_clienteEmail = new JTextField();
		txt_clienteEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_clienteEmail.setColumns(10);
		txt_clienteEmail.setBounds(10, 209, 425, 19);
		contentPane.add(txt_clienteEmail);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Endere\u00E7o");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(10, 118, 80, 21);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Telefone");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_2.setBounds(445, 178, 74, 21);
		contentPane.add(lblNewLabel_1_1_2);
		
		txt_cleinteEndereco = new JTextField();
		txt_cleinteEndereco.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_cleinteEndereco.setBounds(10, 149, 656, 19);
		contentPane.add(txt_cleinteEndereco);
		txt_cleinteEndereco.setColumns(10);
		
		JButton btnSubmit = new JButton("Cadastrar");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cliente c = new Cliente();
				ClienteDAO cDAO = new ClienteDAO();
				c.setClienteNome(txt_clienteNome.getText());
				c.setClienteCPF(txt_clienteCPF.getText());
				c.setClienteEmail(txt_clienteEmail.getText());
				c.setClienteTelefone(txt_clienteTelefone.getText());
				c.setClienteEndereco(txt_cleinteEndereco.getText());
				cDAO.create(c);
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSubmit.setBounds(10, 340, 111, 33);
		contentPane.add(btnSubmit);
		
		JButton btnClear = new JButton("Limpar");
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClear.setBounds(131, 340, 111, 33);
		contentPane.add(btnClear);
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBounds(252, 340, 111, 33);
		contentPane.add(btnCancel);
	}
}
