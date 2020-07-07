package wm_assistant.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import wm_assistant.begin.wm_assistantUtil;
import wm_assistant.contorl.GMManager;
import wm_assistant.contorl.merchatManager;
import wm_assistant.contorl.userManager;
import wm_assistant.model.merchat;
import wm_assistant.model.product;
import wm_assistant.model.productsort;
import wm_assistant.model.users;
import wm_assistant.util.BaseException;

public class FrmMerchatforuser extends JDialog implements ActionListener {
	
	private JMenuBar menubar=new JMenuBar(); ;
	private JMenu menu_merchat=new JMenu("商家信息");
    private JMenu menu_youhui=new JMenu("优惠信息");
    
    private JPanel statusBar = new JPanel();
    
    private Object merchatTitle[]=merchat.tableTitlesforuser;
	private Object merchatData[][];
   	DefaultTableModel merchatModel=new DefaultTableModel();
   	private JTable dataTablemerchat=new JTable(merchatModel);
   	
   	private Object tblproductsortTitle[]=productsort.tblproductsortTitleforuser;
   	private Object tblproductsort[][];
   	DefaultTableModel tabproductsortModel=new DefaultTableModel();
   	private JTable dataproductsort=new JTable(tabproductsortModel);

   	private Object tblproductTitle[]=product.tblproductTitleforuser;
   	private Object tblproduct[][];
   	DefaultTableModel tabproductModel=new DefaultTableModel();
   	private JTable dataproduct=new JTable(tabproductModel);
   	
   	private merchat curmerchat=null;
   	private productsort curproductsort=null;
	List<merchat> allmerchat=null;
	List<productsort> allproductsort=null;
	List<product> allproduct=null;
   	private void reloadmerchatTable(){//这是测试数据，需要用实际数替换
		try {
			allmerchat=merchatManager.loadMerchat();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		merchatData =  new Object[allmerchat.size()][merchat.tableTitlesforuser.length];
		for(int i=0;i<allmerchat.size();i++){
			for(int j=0;j<merchat.tableTitlesforuser.length;j++)
				merchatData[i][j]=allmerchat.get(i).getCelluser(j);
		}
		merchatModel.setDataVector(merchatData,merchatTitle);
		this.dataTablemerchat.validate();
		this.dataTablemerchat.repaint();
	}
   	private void reloadproductsortTabel(int merchatIdx){
		if(merchatIdx<0) return;
		curmerchat=allmerchat.get(merchatIdx);
		try {
			allproductsort=wm_assistantUtil.productsortmanager.loadproductsort(curmerchat);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblproductsort =new Object[allproductsort.size()][productsort.tblproductsortTitleforuser.length];
		for(int i=0;i<allproductsort.size();i++){
			for(int j=0;j<productsort.tblproductsortTitleforuser.length;j++)
				tblproductsort[i][j]=allproductsort.get(i).getCelluser(j);
		}
		
		tabproductsortModel.setDataVector(tblproductsort,tblproductsortTitle);
		this.dataproductsort.validate();
		this.dataproductsort.repaint();
	}
	private void reloadproductTabel(int psIdx){
		if(psIdx<0) return;
		curproductsort=allproductsort.get(psIdx);
		try {
			allproduct=wm_assistantUtil.productmanager.loadproduct(curproductsort);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblproduct =new Object[allproduct.size()][product.tblproductTitleforuser.length];
		for(int i=0;i<allproduct.size();i++){
			for(int j=0;j<product.tblproductTitleforuser.length;j++)
				tblproduct[i][j]=allproduct.get(i).getCelluser(j);
		}
		
		tabproductModel.setDataVector(tblproduct,tblproductTitle);
		this.dataproduct.validate();
		this.dataproduct.repaint();
	}
	public FrmMerchatforuser(Frame f, String s, boolean b) {
		this.setSize(1500, 800);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		menubar.add(menu_merchat);this.menu_merchat.addActionListener(this);
		
		menubar.add(menu_youhui);this.menu_youhui.addActionListener(this);
		this.setJMenuBar(menubar);
		
		this.getContentPane().add(new JScrollPane(this.dataTablemerchat), BorderLayout.WEST);
		this.reloadmerchatTable();
		this.dataTablemerchat.addMouseListener(new MouseAdapter (){
			public void mouseClicked(MouseEvent e) {
				int i=FrmMerchatforuser.this.dataTablemerchat.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmMerchatforuser.this.reloadproductsortTabel(i);
			}
	    	
	    });
		this.getContentPane().add(new JScrollPane(this.dataproductsort), BorderLayout.CENTER);
		this.dataproductsort.addMouseListener(new MouseAdapter (){
			public void mouseClicked(MouseEvent e) {
				int i=FrmMerchatforuser.this.dataproductsort.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmMerchatforuser.this.reloadproductTabel(i);
			}
	    	
	    });
		
	    this.getContentPane().add(new JScrollPane(this.dataproduct), BorderLayout.EAST);
	    
	    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    JLabel label=new JLabel("您好!"+userManager.currentuser.getUser_name());//修改成   您好！+登陆用户名
	    statusBar.add(label);
	    this.getContentPane().add(statusBar,BorderLayout.SOUTH);
		
		
	}
	public void actionPerformed(ActionEvent e) {
		
	}

}
