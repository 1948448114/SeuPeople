package Frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import SQLoperation.mysqlOpeartion;

public class RegistFrame {
	private JFrame registFrame;
	private JTextField textField_Name;
	private JTextField textField_R;
	private JPasswordField passwordFieldR;

	// ����ע�ᴰ��
	public RegistFrame() {
		registFrame = new JFrame();
		registFrame.getContentPane().setBackground(Color.CYAN);
		registFrame.getContentPane().setFont(new Font("��������", Font.PLAIN, 44));
		registFrame.setBounds(100, 100, 450, 409);
		registFrame.getContentPane().setLayout(null);
		registFrame.setLocation(200, 200);
		registFrame.setTitle("SEU People ע��");
		registFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		textField_Name = new JTextField();
		textField_Name.setBounds(160, 101, 122, 21);
		registFrame.getContentPane().add(textField_Name);
		textField_Name.setColumns(10);

		textField_R = new JTextField();
		textField_R.setBounds(160, 162, 122, 21);
		registFrame.getContentPane().add(textField_R);
		textField_R.setColumns(10);

		JLabel labelName = new JLabel("��  ��");
		labelName.setBounds(96, 104, 54, 15);
		registFrame.getContentPane().add(labelName);

		JLabel labelID = new JLabel("�� ��");
		labelID.setBounds(96, 165, 54, 15);
		registFrame.getContentPane().add(labelID);

		JLabel label_key = new JLabel("����");
		label_key.setBounds(96, 226, 54, 15);
		registFrame.getContentPane().add(label_key);

		passwordFieldR = new JPasswordField();
		passwordFieldR.setBounds(160, 223, 122, 21);
		registFrame.getContentPane().add(passwordFieldR);

		JButton Registbutton_R = new JButton("ע��");
		Registbutton_R.setBounds(112, 296, 62, 23);
		registFrame.getContentPane().add(Registbutton_R);
		
//ע�ᰴť��Ӧ�¼�
		Registbutton_R.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Registbutton_RListener();
			}
		});

		JButton ExitButton = new JButton("�˳�");
		ExitButton.setBounds(220, 296, 62, 23);
		registFrame.getContentPane().add(ExitButton);
//�˳���ť��Ӧ�¼�
		ExitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				registFrame.dispose();
				// TODO �Զ����ɵķ������

			}

		});

		JLabel lblSeu_R = new JLabel("SEU People");
		lblSeu_R.setForeground(Color.RED);
		lblSeu_R.setBackground(Color.RED);
		lblSeu_R.setFont(new Font("��������", Font.PLAIN, 30));
		lblSeu_R.setBounds(74, 20, 200, 47);
		registFrame.getContentPane().add(lblSeu_R);

		JLabel lblSeu_R2 = new JLabel("Regist");
		lblSeu_R2.setForeground(Color.PINK);
		lblSeu_R2.setBackground(Color.PINK);
		lblSeu_R2.setFont(new Font("��������", Font.PLAIN, 25));
		lblSeu_R2.setBounds(260, 30, 100, 47);
		registFrame.getContentPane().add(lblSeu_R2);

		registFrame.setVisible(true);

	}

	//ע�ᰴť�¼�����
	public void Registbutton_RListener() {
		boolean regist;
		String name_R = textField_Name.getText();
		String password_R = passwordFieldR.getText();
		String ID_R = textField_R.getText();

		//�ж������Ƿ�Ϊ��
		if (name_R.equals(""))
			new Dialog("�ǳƲ���Ϊ��!");
		else if (ID_R.equals(""))
			new Dialog("�˺Ų���Ϊ��");
		else if (password_R.equals(""))
			new Dialog("���벻��Ϊ��");

		else {
			mysqlOpeartion operation = new mysqlOpeartion(LoginFrame.url,
					LoginFrame.user, LoginFrame.pwd);
			operation.Connect();
			
			//������ݿ���ID�Ƿ��Ѿ�����
			boolean judge = checkRegist(ID_R, operation);
			if (!judge)
			{
				new Dialog("�˺��Ѵ���,��������д!");
				operation.close();
			}

			//ע�������ʻ������ݿ���
			else {
				String registSql = String
						.format("insert into login (username,id,password) values ('%s','%s','%s')",
								name_R, password_R, ID_R);
				regist = operation.insert(registSql);
				if (regist)
					new Dialog("��ϲ��ע��ɹ����Ͻ���½�ɣ�");
				
				operation.close();
				registFrame.dispose();
			}

			
		}

	}

	//���ID�Ƿ��Ѿ����ڵĺ���
	public boolean checkRegist(String cID, mysqlOpeartion op) {
		String checkSql = "select id from login";
		ResultSet resultCheck = op.mysqlquery(checkSql);
		try {
			while (resultCheck.next()) {
				if (resultCheck.getString(1).equals(cID))
					return false;
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			return false;
		}
		return true;

	}

}
