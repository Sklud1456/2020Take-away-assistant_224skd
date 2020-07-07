package wm_assistant.contorl;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import wm_assistant.model.merchat;
import wm_assistant.model.product;
import wm_assistant.model.productsort;
import wm_assistant.model.quan;
import wm_assistant.util.BaseException;
import wm_assistant.util.BusinessException;
import wm_assistant.util.DBUtil;
import wm_assistant.util.DbException;

public class quanManager {
	public static quan curQuan=null;
	
	public void addquan(merchat m,String name,double youhui,int jidan,Date startdate,Date enddate) throws BaseException {
		if("".equals(name)||name==null) throw new BusinessException("商品名不可为空");
		if(youhui<0||jidan<0) throw new BusinessException("不可为负数");
		if(startdate==null||enddate==null) throw new BusinessException("日期为空");
		if(startdate.after(enddate))  throw new BusinessException("开始日期比结束日期迟");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from quan where merchat_no=? and quan_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,m.getMerchat_no());
			pst.setString(2,name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				rs.close();
				pst.close();
				throw new BusinessException("优惠券名已存在");
			}
			
			sql="insert into quan(merchat_no,quan_name,quan_youhui,quan_jdyq,quan_startdate,quan_enddate) values(?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,m.getMerchat_no());
			pst.setString(2, name);
			pst.setDouble(3, youhui);
			pst.setInt(4, jidan);
			pst.setDate(5, new java.sql.Date(startdate.getTime()));
			pst.setDate(6,new java.sql.Date(enddate.getTime()));
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
	
	public List<quan> loadquan(merchat m) throws BaseException {
		List<quan> result=new ArrayList<quan>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from quan where merchat_no=? order by quan_no asc";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, m.getMerchat_no());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				quan q=new quan();
				q.setQuan_no(rs.getInt(1));
				q.setMerchat_no(rs.getInt(2));
				q.setQuan_name(rs.getString(3));
				q.setQuan_youhui(rs.getDouble(4));
				q.setQuan_jdyq(rs.getInt(5));
				q.setQuan_startDate(rs.getDate(6));
				q.setQuan_enddate(rs.getDate(7));
				result.add(q);
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
	
	public void deletequan(quan q) throws BaseException {
		Connection conn=null;
		try {
	
			conn=DBUtil.getConnection();
			String sql="select * from quan where quan_no=? ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,q.getQuan_no());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) {
				throw new BusinessException("该优惠券不存在");
			}
			rs.close();
			sql="delete from quan where quan_no=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,q.getQuan_no());
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
		quanManager a=new quanManager();
		List<quan> b=new ArrayList<quan>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d1=new Date();
		Date d2=new Date();
		Date d3=new Date();
		Date d4=new Date();
		Date d5=new Date();
		Date d6=new Date();
		try {
			d1=sdf.parse("2020-01-02");
			d2=sdf.parse("2020-02-02");
			d3=sdf.parse("2020-04-02");
			d4=sdf.parse("2020-05-05");
			d5=sdf.parse("2020-02-02");
			d6=sdf.parse("2020-03-04");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		merchat m =new merchat();
		m.setMerchat_no(4);
		try {
			a.addquan(m, "q1", 5.0, 10,d5,d6 );
			
			//a.addquan(m, "qw", 3.0, 6,d3,d4 );
			b=a.loadquan(m);
			for(int i=0;i<b.size();i++) {
				System.out.println(b.get(i).getQuan_name()+" "+b.get(i).getQuan_enddate()+" "+b.get(i).getQuan_youhui());
			}
			
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
	
	
	

}
