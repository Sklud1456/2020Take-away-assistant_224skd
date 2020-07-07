package wm_assistant.contorl;

import java.sql.Connection;
import java.sql.SQLException;

import wm_assistant.model.GM;
import wm_assistant.model.users;
import wm_assistant.util.BaseException;
import wm_assistant.util.BusinessException;
import wm_assistant.util.DBUtil;
import wm_assistant.util.DbException;

public class userManager {
	public static users currentuser=null;
	public users login(String user_name, String user_password) throws BaseException {
		
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from users where user_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,user_name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("用户不存在");
			users user=new users();
			user.setUser_no(rs.getInt(1));
			user.setUser_name(rs.getString(2));
			user.setUser_sex(rs.getString(3));
			user.setUser_password(rs.getString(4));
			user.setUser_phone(rs.getString(5));
			user.setUser_mail(rs.getString(6));
			user.setUser_city(rs.getString(7));
			user.setUser_vip(rs.getString(9));
			user.setUser_ipvenddate(rs.getDate(10));
			System.out.println(user.getUser_name());
			rs.close();
			pst.close();
			return user;
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
	
	public void createUser(users user)throws BaseException{
		if(user.getUser_name()==null || "".equals(user.getUser_name()) || user.getUser_name().length()>20){
			throw new BusinessException("用户id不可不存在且不能超过20长度");
		}
		if(user.getUser_password()==null || "".equals(user.getUser_password())){
			throw new BusinessException("请设置用户密码");
		}
		
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select gm_name,user_name from gm,users where gm_name=? || user_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,user.getUser_name());
			pst.setString(2,user.getUser_name());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("用户名已存在");
			rs.close();
			pst.close();
			sql="insert into users(user_name,user_sex,user_password,user_phone,user_mail,user_city,user_vip,user_vipenddate) values(?,?,?,?,?,?,'n','1900-01-01')";
			pst=conn.prepareStatement(sql);
			pst.setString(1, user.getUser_name());
			pst.setString(2, user.getUser_sex());
			pst.setString(3, user.getUser_password());
			pst.setString(4, user.getUser_phone());
			pst.setString(5, user.getUser_mail());
			pst.setString(6, user.getUser_city());
			pst.execute();
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
	
	public void changeuserPwd(String user_name,String oldPwd,String newPwd)throws BaseException{
		if(oldPwd==null) throw new BusinessException("原密码为空");
		if(newPwd==null || "".equals(newPwd) || newPwd.length()>20) throw new BusinessException("新密码长度为1-20");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_password from users where user_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,user_name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("用户不存在");
			if(!oldPwd.equals(rs.getString(1))) throw new BusinessException("旧密码不一致");
			rs.close();
			pst.close();
			sql="update users set user_password=? where user_name=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setString(2, user_name);
			pst.execute();
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
	

}
