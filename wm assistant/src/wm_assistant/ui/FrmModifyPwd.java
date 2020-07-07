package wm_assistant.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import wm_assistant.contorl.GMManager;
import wm_assistant.contorl.userManager;
import wm_assistant.model.GM;
import wm_assistant.model.users;
import wm_assistant.util.BaseException;


public class FrmModifyPwd extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelPwdOld = new JLabel("原密码：");
	private JLabel labelPwd = new JLabel("新密码：");
	private JLabel labelPwd2 = new JLabel("新密码：");
	private JPasswordField edtPwdOld = new JPasswordField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);
	public FrmModifyPwd(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelPwdOld);
		workPane.add(edtPwdOld);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		workPane.add(labelPwd2);
		workPane.add(edtPwd2);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 180);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			GMManager A=new GMManager();
			userManager B=new userManager();
			GM a= GMManager.currentGM;
			users b= userManager.currentuser;
			try {
				if(a==null) {
					String user_name=b.getUser_name();
					String oldpwd=new String(this.edtPwdOld.getPassword());
					String newpwd1=new String(this.edtPwd.getPassword());
					String newpwd2=new String(this.edtPwd2.getPassword());
					if(newpwd1.equals(newpwd2)) {
						B.changeuserPwd(user_name, oldpwd, newpwd1);
						this.setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null,  "新密码不一致","新密码不一致",JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					
				}else {
					String gm_name=a.getGm_name();
					String oldpwd=new String(this.edtPwdOld.getPassword());
					String newpwd1=new String(this.edtPwd.getPassword());
					String newpwd2=new String(this.edtPwd2.getPassword());
					if(newpwd1.equals(newpwd2)) {
						A.changeGMPwd(gm_name, oldpwd, newpwd1);
						this.setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null,  "新密码不一致","新密码不一致",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}

}
