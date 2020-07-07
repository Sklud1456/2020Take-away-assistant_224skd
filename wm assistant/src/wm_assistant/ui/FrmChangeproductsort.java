package wm_assistant.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import wm_assistant.begin.wm_assistantUtil;
import wm_assistant.contorl.merchatManager;
import wm_assistant.contorl.productsortManager;
import wm_assistant.model.merchat;
import wm_assistant.model.productsort;
import wm_assistant.util.BaseException;
import wm_assistant.util.BusinessException;

public class FrmChangeproductsort extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelName = new JLabel("名称：");
	
	private JTextField edtName = new JTextField(20);
	private JTextField edtNumber = new JTextField(20);
	
	public FrmChangeproductsort (FrmMerchatforGM frmMerchatforGM, String s, boolean b) {
		super(frmMerchatforGM, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelName);
		workPane.add(edtName);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 180);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			return;
		}
		else if(e.getSource()==this.btnOk){
			String name=this.edtName.getText();
			productsort ps  =new productsort();
			try {
				if(productsortManager.currentproductsort==null) throw new BusinessException("未指定种类");
				else ps =productsortManager.currentproductsort;
				if(name!=null) {
					wm_assistantUtil.productsortmanager.changesortname(ps, name);
				}
				else {
					throw new BusinessException("未输入");
				}
				productsortManager.currentproductsort=null;
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
	}

}
