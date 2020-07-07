package wm_assistant.contorl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wm_assistant.model.address;
import wm_assistant.model.merchat;
import wm_assistant.model.product;
import wm_assistant.model.productsort;
import wm_assistant.model.users;
import wm_assistant.util.BaseException;
import wm_assistant.util.BusinessException;
import wm_assistant.util.DBUtil;
import wm_assistant.util.DbException;

public class addressManager {
	public void addaddress(users us, String pri,String city,String qu,String add,String man,String phone) throws BaseException {
		if("".equals(pri)||pri==null||"".equals(qu)||qu==null||"".equals(city)||city==null||"".equals(add)||add==null
			||"".equals(man)||man==null||phone==null||"".equals(phone)	) throw new BusinessException("所有信息不可为空");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from address where user_no=? and address_phone=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,us.getUser_no());
			pst.setString(2,phone);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				rs.close();
				pst.close();
				throw new BusinessException("该信息已存在");
			}
			sql="insert into address(user_no,address_pri,address_city,address_qu,address_add,address_man,"
				+ "address_phone) values(?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,us.getUser_no());
			pst.setString(2,pri);
			pst.setString(3, city);
			pst.setString(4, qu);
			pst.setString(5, add);
			pst.setString(6, man);
			pst.setString(7, phone);
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
				e.printStackTrace();
				throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}		
	}
	
	public List<address> loadaddress(users us) throws BaseException {
		List<address> result=new ArrayList<address>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from address where user_no=? order by address_no asc";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, us.getUser_no());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				address add=new address();
				add.setAddress_no(rs.getInt(1));
				add.setUser_no(rs.getInt(2));
				add.setAddress_pri(rs.getString(3));
				add.setAddress_city(rs.getString(4));
				add.setAddress_qu(rs.getString(5));
				add.setAddress_add(rs.getString(6));
				add.setAddress_man(rs.getString(7));
				add.setAddress_phone(rs.getString(8));				
				result.add(add);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public void updateaddress(address Add, String pri,String city,String qu,String add,String man,String phone) throws BaseException {
		if("".equals(pri)||pri==null||"".equals(qu)||qu==null||"".equals(city)||city==null||"".equals(add)||add==null
			||"".equals(man)||man==null||phone==null||"".equals(phone)	) throw new BusinessException("所有信息不可为空");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from address where address_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Add.getAddress_no());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				rs.close();
				pst.close();
				throw new BusinessException("该信息已存在");
			}
			sql="update address set address_pri=?,set address_city=?,set address_qu=?,set address_add=?,"
					+ "set address_man=?,set address_phone=? where address_no=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1,pri);
			pst.setString(2, city);
			pst.setString(3, qu);
			pst.setString(4, add);
			pst.setString(5, man);
			pst.setString(6, phone);
			pst.setInt(7, Add.getAddress_no());
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
				e.printStackTrace();
				throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}		
	}
	
	public void deleteaddress(address add) throws BaseException {
		Connection conn=null;
		try {
	
			conn=DBUtil.getConnection();
			String sql="select * from address where address_no=? ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,add.getAddress_no());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) {
				throw new BusinessException("该地址不存在");
			}
			rs.close();
			sql="delete from address where address_no=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,add.getAddress_no());
			pst.executeUpdate();
			pst.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public static void main(String[] args) {
		addressManager a=new addressManager();
		List<address> b=new ArrayList<address>();
		try {
			users us=new users();
			us.setUser_no(1);
			a.addaddress(us, "浙江", "杭州", "拱墅", "浙江省杭州市拱墅区湖州街", "skd", "1568715467");
			a.addaddress(us,"浙江","杭州","拱墅","顺德区万恶去","qwe","7861189445");
			a.addaddress(us,"浙江","杭州","拱墅","顺德区万恶去","qwqwe","74567889445");
			b=a.loadaddress(us);
			for(int i=0;i<b.size();i++) {
				System.out.println(b.get(i).getAddress_no()+" "+b.get(i).getAddress_add()+" "+b.get(i).getAddress_man());
			}
			a.deleteaddress(b.get(1));
			b=a.loadaddress(us);
			for(int i=0;i<b.size();i++) {
				System.out.println(b.get(i).getAddress_no()+" "+b.get(i).getAddress_add()+" "+b.get(i).getAddress_man());
			}
			
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
