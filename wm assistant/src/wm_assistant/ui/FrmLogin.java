package wm_assistant.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import wm_assistant.contorl.GMManager;
import wm_assistant.contorl.userManager;
import wm_assistant.model.GM;
import wm_assistant.model.users;
import wm_assistant.util.BaseException;


public class FrmLogin extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel(new GridLayout(3,3,20,10));
	private JButton btngmLogin = new JButton("π‹¿Ì‘±µ«¬Ω");
	private JButton btnusersLogin = new JButton("”√ªßµ«¬Ω");
	private JButton btnCancel = new JButton("ÕÀ≥ˆ");
	private JButton btnRegister = new JButton("”√ªß◊¢≤·");
	
	private JLabel labelUser = new JLabel("”√ªß£∫");
	private JLabel labelPwd = new JLabel("√‹¬Î£∫");
	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);

	public FrmLogin(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnRegister);
		toolBar.add(btnusersLogin);
		toolBar.add(btngmLogin);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		labelUser.setHorizontalAlignment(SwingConstants.RIGHT);
		workPane.add(edtUserId);
		workPane.add(labelPwd);
		labelPwd.setHorizontalAlignment(SwingConstants.RIGHT);
		workPane.add(edtPwd);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(400, 180);
		// ∆¡ƒªæ”÷–œ‘ æ
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		btngmLogin.addActionListener(this);
		btnCancel.addActionListener(this);
		this.btnRegister.addActionListener(this);
		btnusersLogin.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btngmLogin) {
			GMManager a=new GMManager();
			String gmname=this.edtUserId.getText();
			String pwd=new String(this.edtPwd.getPassword());
			try {
				GM gm= a.login(gmname, pwd);
				if (pwd.equals(gm.getGm_password())) {
					GMManager.currentGM=gm;
					setVisible(false);
				}
				else{
					JOptionPane.showMessageDialog(null,  "√‹¬Î¥ÌŒÛ","√‹¬Î¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
				}
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
			}
			
			
		}else if(e.getSource()==this.btnusersLogin) {
			userManager a=new userManager();
			String userid=this.edtUserId.getText();
			String pwd=new String(this.edtPwd.getPassword());
			try {
				users user= a.login(userid, pwd);
				if (pwd.equals(user.getUser_password())) {
					userManager.currentuser=user;
					setVisible(false);
				}
				else{
					JOptionPane.showMessageDialog(null,  "√‹¬Î¥ÌŒÛ","√‹¬Î¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
				}
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
			}
			
		}else if (e.getSource() == this.btnCancel) {
			System.exit(0);
		} else if(e.getSource()==this.btnRegister){
			FrmRegister dlg=new FrmRegister(this,"◊¢≤·",true);
			dlg.setVisible(true);
		}
	}


}
