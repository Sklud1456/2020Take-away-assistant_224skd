package wm_assistant.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import wm_assistant.contorl.GMManager;
import wm_assistant.contorl.userManager;




public class FrmMain extends JFrame implements ActionListener  {
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar(); ;
    private JMenu menu_merchat=new JMenu("商家信息");
    private JMenu menu_user=new JMenu("用户信息");
    private JMenu menu_rider=new JMenu("骑手信息");
    private JMenu menu_more=new JMenu("更多");
    
    private JMenuItem  menuItem_productmessage=new JMenuItem("商品信息");
    private JMenuItem  menuItem_quan=new JMenuItem("优惠券");
    private JMenuItem  menuItem_manjian=new JMenuItem("满减活动");
    private JMenuItem  menuItem_usermessage=new JMenuItem("用户信息");
    private JMenuItem  menuItem_youhuiquan=new JMenuItem("优惠券");
    private JMenuItem  menuItem_address=new JMenuItem("地址");
    private JMenuItem  menuItem_order=new JMenuItem("订单信息");
    private JMenuItem  menuItem_ridermessage=new JMenuItem("骑手信息");
    private JMenuItem  menuItem_ridermoney=new JMenuItem("骑手账单");
    
    
    private JPanel toolBar = new JPanel();
    GridLayout layout = new GridLayout(3, 1);
    JPanel panel = new JPanel(layout);
    
    private JButton btnsj = new JButton("商家");
    private JButton btnyh = new JButton("用户");
    private JButton btnqs = new JButton("骑手");
    private JButton btngm = new JButton("管理员");

    
    
    private JMenuItem  menuItem_modifyPwd=new JMenuItem("密码修改");
    
    private JMenuItem  menuItem_static1=new JMenuItem("统计1");
    
    private FrmLogin dlgLogin=null;
   	private JPanel statusBar = new JPanel();
   	

   	private Object tblmerchat[][];
   	DefaultTableModel tabmerchatModel=new DefaultTableModel();
   	private JTable datamerchat=new JTable(tabmerchatModel);
   	

   	private Object tblproductsort[][];
   	DefaultTableModel tabproductsortModel=new DefaultTableModel();
   	private JTable dataproductsort=new JTable(tabproductsortModel);

   	private Object tblproduct[][];
   	DefaultTableModel tabproductModel=new DefaultTableModel();
   	private JTable dataproduct=new JTable(tabproductModel);

    public FrmMain(){
		
		//this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("外卖助手");
		dlgLogin=new FrmLogin(this,"登陆",true);
		dlgLogin.setVisible(true);
//		if(GMManager.currentGM==null) {
//			System.out.println(userManager.currentuser.getUser_no()+" "+userManager.currentuser.getUser_name());
//		}else {
//			System.out.println(GMManager.currentGM.getGm_no()+" "+GMManager.currentGM.getGm_name());
//		}
		
	    //菜单
//	    this.menu_merchat.add(this.menuItem_productmessage); this.menuItem_productmessage.addActionListener(this);
//	    this.menu_merchat.add(this.menuItem_youhuiquan); this.menuItem_youhuiquan.addActionListener(this);
//	    this.menu_merchat.add(this.menuItem_manjian); this.menuItem_manjian.addActionListener(this);
//	    this.menu_user.add(this.menuItem_usermessage); this.menuItem_usermessage.addActionListener(this);
//	    this.menu_user.add(this.menuItem_youhuiquan); this.menuItem_youhuiquan.addActionListener(this);
//	    this.menu_user.add(this.menuItem_address); this.menuItem_address.addActionListener(this);
//	    this.menu_user.add(this.menuItem_order); this.menuItem_order.addActionListener(this);
//	    this.menu_rider.add(this.menuItem_ridermessage); this.menuItem_ridermessage.addActionListener(this);
//	    this.menu_rider.add(this.menuItem_ridermoney); this.menuItem_ridermoney.addActionListener(this);
//	    this.menu_more.add(this.menuItem_static1); this.menuItem_static1.addActionListener(this);
//	    this.menu_more.add(this.menuItem_modifyPwd); this.menuItem_modifyPwd.addActionListener(this);
//	    
//	    menubar.add(menu_merchat);
//	    menubar.add(menu_user);
//	    menubar.add(menu_rider);
//	    menubar.add(menu_more);
//	    this.setJMenuBar(menubar);
	    
	    this.setSize(300, 400);
	    
	    double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
	    
//	    this.getContentPane().add(new JScrollPane(this.datamerchat), BorderLayout.WEST);
//	    this.datamerchat.addMouseListener(new MouseAdapter (){
//
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				int i=FrmMain.this.datamerchat.getSelectedRow();
//				if(i<0) {
//					return;
//				}
//				
//			}
//	    	
//	    });
//	    this.getContentPane().add(new JScrollPane(this.dataproductsort), BorderLayout.CENTER);
//	    this.getContentPane().add(new JScrollPane(this.dataproduct), BorderLayout.EAST);
	    
		panel.add(btnsj);this.btnsj.addActionListener(this);
		panel.add(btnyh);this.btnyh.addActionListener(this);
		panel.add(btnqs);this.btnqs.addActionListener(this);
		this.getContentPane().add(panel, BorderLayout.CENTER);
	    
//	    this.getContentPane().add(btnsj);
//	    this.getContentPane().add(btnyh);
//	    this.getContentPane().add(btnqs);
	    
	    
	    
	    //状态栏
	    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    JLabel label=new JLabel("您好!");//修改成   您好！+登陆用户名
	    statusBar.add(label);
	    this.getContentPane().add(statusBar,BorderLayout.SOUTH);
	    this.addWindowListener(new WindowAdapter(){   
	    	public void windowClosing(WindowEvent e){ 
	    		System.exit(0);
             }
        });
	   
	    this.setVisible(true);
	}
    public void actionPerformed(ActionEvent e) {
    	 if(e.getSource()==this.btnsj){
    		 if(GMManager.currentGM==null) {
    			 FrmMerchatforuser fmM=new FrmMerchatforuser(this,"商家信息",true);
    			 fmM.setVisible(true);
    		 }
    		 else {
    			 FrmMerchatforGM fmM=new FrmMerchatforGM(this,"商家信息",true);
    			 fmM.setVisible(true);
    		 }
    		
 		}
    	
    }
}
