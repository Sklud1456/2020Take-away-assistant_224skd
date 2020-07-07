package wm_assistant.contorl;

import java.sql.Connection;
import java.sql.SQLException;


import wm_assistant.model.GM;
import wm_assistant.util.BaseException;
import wm_assistant.util.BusinessException;
import wm_assistant.util.DBUtil;
import wm_assistant.util.DbException;


public class GMManager {
	public static GM currentGM=null;
	public GM login(String gm_name, String gm_password) throws BaseException {
		
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select gm_no,gm_name,gm_password from gm where gm_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,gm_name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("用户不存在");
			GM gm=new GM();
			gm.setGm_no(rs.getInt(1));
			gm.setGm_name( rs.getString(2));
			gm.setGm_password(rs.getString(3));
			rs.close();
			pst.close();
			return gm;
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
	
	public void changeGMPwd(String gm_name,String oldPwd,String newPwd)throws BaseException{
		if(oldPwd==null) throw new BusinessException("原密码为空");
		if(newPwd==null || "".equals(newPwd) || newPwd.length()>20) throw new BusinessException("新密码长度为1-20");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select gm_password from gm where gm_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,gm_name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("用户不存在");
			if(!oldPwd.equals(rs.getString(1))) throw new BusinessException("旧密码不一致");
			rs.close();
			pst.close();
			sql="update gm set gm_password=? where gm_name=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setString(2, gm_name);
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
