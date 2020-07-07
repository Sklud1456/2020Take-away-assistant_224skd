package wm_assistant.contorl;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import wm_assistant.begin.wm_assistantUtil;
import wm_assistant.model.GM;
import wm_assistant.model.merchat;
import wm_assistant.model.rider;
import wm_assistant.util.BaseException;
import wm_assistant.util.BusinessException;
import wm_assistant.util.DBUtil;
import wm_assistant.util.DbException;

public class merchatManager {
	public static merchat currentmerchat=null;
	public merchat addMerchat(String name) throws BaseException {
		if("".equals(name)||name==null) throw new BusinessException("商家名不可为空");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from merchat where merchat_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("已有该商家名");
			rs.close();
			pst.close();
			sql="INSERT into merchat(merchat_name)\r\n" + 
					"VALUES(?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1,name);
			pst.executeUpdate();
			pst.close();
			sql="select *  from merchat where merchat_name=? ";
			pst=conn.prepareStatement(sql);
			pst.setString(1, name);
			rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("新建失败");
			merchat m=new merchat();
			m.setMerchat_no(rs.getInt(1));
			m.setMerchat_name(rs.getString(2));
			m.setMerchat_star(rs.getInt(3));
			m.setMerchat_conmus(rs.getInt(4));
			m.setMerchat_sell(rs.getInt(5));
			rs.close();
			pst.close();
			return m;
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
	
	public static List<merchat> loadMerchat() throws BaseException {
		List<merchat> result=new ArrayList<merchat>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from merchat";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				merchat m=new merchat();
				m.setMerchat_no(rs.getInt(1));
				m.setMerchat_name(rs.getString(2));
				m.setMerchat_star(rs.getInt(3));
				m.setMerchat_conmus(rs.getInt(4));
				m.setMerchat_sell(rs.getInt(5));
				result.add(m);
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
	public void changeMerchatstar(merchat m,int star)throws BaseException{
		Connection conn=null;
		try {
			if(star>5||star<1) {
				throw  new BusinessException("只能输1-5 ");
			}
			conn=DBUtil.getConnection();
			String sql="select * from merchat where merchat_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,m.getMerchat_no());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("该商家不存在");
			else {
				rs.close();
				pst.close();
				sql="update merchat set merchat_star=? where merchat_no=?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, star);
				pst.setInt(2, m.getMerchat_no());
				pst.executeUpdate();
				pst.close();
			}
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
	
	public void changeMerchatname(merchat m,String name)throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from merchat where merchat_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,m.getMerchat_no());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("该商家不存在");
			else {
				rs.close();
				pst.close();
				sql="update merchat set merchat_name=? where merchat_no=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, name);
				pst.setInt(2, m.getMerchat_no());
				pst.executeUpdate();
				pst.close();
			}
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
	
	public void deleteMerchat(merchat m) throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from merchat where merchat_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,m.getMerchat_name());
			java.sql.ResultSet rs=pst.executeQuery();
			
			if(!rs.next()) {
				pst.close();
				rs.close();
				throw new  BusinessException("该商家不存在");
			}
			else {
				pst.close();
				rs.close();
				sql="delete from merchat where merchat_name=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, m.getMerchat_name());
				pst.executeUpdate();
				pst.close();
				rs.close();
			}
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
		merchatManager a=new merchatManager();
		List<merchat> b=new ArrayList<merchat>();
		try {
//			a.addMerchat("pizza");
//			a.addMerchat("nekoqiao");
			b=a.loadMerchat();
//			System.out.println(b.get(0).getMetchat_no()+" "+b.get(0).getMerchat_name());
//			System.out.println(b.get(1).getMetchat_no()+" "+b.get(1).getMerchat_name());
//			System.out.println(b.get(2).getMetchat_no()+" "+b.get(2).getMerchat_name());
//			a.deleteMerchat("nekoqiao");
//			
//			b=a.loadMerchat();
//			System.out.println(b.get(0).getMetchat_no()+" "+b.get(0).getMerchat_name());
//			System.out.println(b.get(1).getMetchat_no()+" "+b.get(1).getMerchat_name());
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
}
