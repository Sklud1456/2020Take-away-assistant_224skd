package wm_assistant.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtil2 {
	private static final String jdbcUrl="jdbc:mysql://localhost:3306/2020¶ÌÑ§ÆÚ";
	private static final String dbUser="root";
	private static final String dbPwd="";
	List<Connection> cs=new ArrayList<Connection>();
	int size;
	
	public DBUtil2(int size)
	{
		this.size=size;
		init();
	}
	
	public void init()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			for(int i=0;i<size;i++)
			{
				Connection c=DriverManager.getConnection(jdbcUrl,dbUser,dbPwd);
				cs.add(c);
			}
		}catch(ClassNotFoundException e) {
				e.printStackTrace();
		}catch(SQLException e) {
				e.printStackTrace();
		}
	}
	
	public synchronized Connection getConnection() {
		while(cs.isEmpty()) {
			try {
				this.wait();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		Connection c=cs.remove(0);
		return c;
	}
	
	public synchronized void returnConnection(Connection c) {
		cs.add(c);
		this.notifyAll();
	}
}
