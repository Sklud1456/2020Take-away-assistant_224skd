package wm_assistant.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import wm_assistant.contorl.userManager;
import wm_assistant.model.users;
import wm_assistant.util.BaseException;


public class FrmRegister  extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel(new GridLayout(2,2,20,10));
	private Button btnOk = new Button("注册");
	private Button btnCancel = new Button("取消");
	
	private JLabel blank2 = new JLabel("  ");
	private JLabel labelUsername = new JLabel("用户名：");
	private JLabel labelUsersex = new JLabel("性别：");
	private JLabel labelUserphone = new JLabel("手机号：");
	private JLabel labelUsermail = new JLabel("邮箱：");
	private JLabel labelUsercity = new JLabel("所住城市 ：");
	private JLabel labelPwd = new JLabel("密码：");
	private JLabel labelPwd2 = new JLabel("确认密码：");
	private JTextField edtUsername = new JTextField(20);
	private JTextField edtUsersex = new JTextField(20);
	private JTextField edtUserphone = new JTextField(20);
	private JTextField edtUsermail = new JTextField(20);
	private JTextField edtUsercity = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);
	public FrmRegister(Dialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUsername);
		labelUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		workPane.add(edtUsername);
		workPane.add(labelUsersex);
		labelUsersex.setHorizontalAlignment(SwingConstants.RIGHT);
		workPane.add(edtUsersex);
		workPane.add(labelUserphone);
		labelUserphone.setHorizontalAlignment(SwingConstants.RIGHT);
		workPane.add(edtUserphone);
		workPane.add(labelUsermail);
		labelUsermail.setHorizontalAlignment(SwingConstants.RIGHT);
		workPane.add(edtUsermail);
		workPane.add(blank2);
		blank2.setHorizontalAlignment(SwingConstants.RIGHT);
		workPane.add(labelUsercity);
		labelUsercity.setHorizontalAlignment(SwingConstants.RIGHT);
		workPane.add(edtUsercity);
		workPane.add(labelPwd);
		labelPwd.setHorizontalAlignment(SwingConstants.RIGHT);
		workPane.add(edtPwd);
		workPane.add(labelPwd2);
		labelPwd2.setHorizontalAlignment(SwingConstants.RIGHT);
		workPane.add(edtPwd2);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(700, 150);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2-30,
				(int) (height - this.getHeight()) / 2+200);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			userManager a=new userManager();
			String user_name=this.edtUsername.getText();
			String user_sex=this.edtUsersex.getText();
			String user_phone=this.edtUserphone.getText();
			String user_mail=this.edtUsermail.getText();
			String user_city=this.edtUsercity.getText();
			String pwd1=new String(this.edtPwd.getPassword());
			String pwd2=new String(this.edtPwd2.getPassword());
			if(!(pwd1.equals(pwd2))) {
				JOptionPane.showMessageDialog(null,  "密码不一致","密码不一致",JOptionPane.ERROR_MESSAGE);
				return;
			}
			else {
				try {
					users user=new users();
					user.setUser_name(user_name);
					user.setUser_sex(user_sex);
					user.setUser_phone(user_phone);
					user.setUser_mail(user_mail);
					user.setUser_city(user_city);
					user.setUser_password(pwd1);
					a.createUser(user);
					this.setVisible(false);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
		}
		
	}

}
