package wm_assistant.contorl;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import wm_assistant.model.rider;
import wm_assistant.model.users;
import wm_assistant.ui.FrmMain;
import wm_assistant.util.BaseException;
import wm_assistant.util.BusinessException;
import wm_assistant.util.DBUtil;
import wm_assistant.util.DbException;

public class riderManager {
	public rider addRider(String name) throws BaseException {
		if("".equals(name)||name==null) throw new BusinessException("骑手名不可为空");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			Date date=new Date();
			SimpleDateFormat dft=new SimpleDateFormat("yyyy-MM-dd");
			String sql="select * from rider where rider_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("已有该骑手名");
			rs.close();
			pst.close();
			sql="INSERT into rider(rider_name,rider_date,rider_ID)\r\n" + 
					"VALUES(?,?,'新人')";
			pst=conn.prepareStatement(sql);
			pst.setString(1,name);
			pst.setString(2, dft.format(date));
			pst.executeUpdate();
			pst.close();
			sql="select *  from rider where rider_name=? ";
			pst=conn.prepareStatement(sql);
			pst.setString(1, name);
			rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("新建失败");
			rider rd=new rider();
			rd.setRider_no(rs.getInt(1));
			rd.setRider_name(rs.getString(2));
			rd.setRider_date(rs.getDate(3));
			rd.setRider_ID(rs.getString(4));
			rs.close();
			pst.close();
			return rd;
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
	
	public List<rider> loadriders() throws BaseException {
		List<rider> result=new ArrayList<rider>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from rider";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				rider rd=new rider();
				rd.setRider_no(rs.getInt(1));
				rd.setRider_name(rs.getString(2));
				rd.setRider_date(rs.getDate(3));
				rd.setRider_ID(rs.getString(4));
				result.add(rd);
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
	
	public void deleterider(String name) throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from rider where rider_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, name);
			java.sql.ResultSet rs=pst.executeQuery();
			
			if(!rs.next()) {
				pst.close();
				rs.close();
				throw new  BusinessException("该骑手不存在");
			}
			else {
				pst.close();
				rs.close();
				sql="delete from rider where rider_name=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, name);
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
		riderManager a=new riderManager();
		List<rider> b=new ArrayList<rider>();
		try {
			a.addRider("asdq");
			b=a.loadriders();
			System.out.println(b.get(0).getRider_name());
			System.out.println(b.get(1).getRider_name());
			System.out.println(b.get(2).getRider_name());
			a.deleterider("abab");
			b=a.loadriders();
			System.out.println(b.get(0).getRider_name());
			System.out.println(b.get(1).getRider_name());
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
