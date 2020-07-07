package wm_assistant.contorl;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import wm_assistant.model.GM;
import wm_assistant.model.merchat;
import wm_assistant.model.productsort;
import wm_assistant.util.BaseException;
import wm_assistant.util.BusinessException;
import wm_assistant.util.DBUtil;
import wm_assistant.util.DbException;


public class productsortManager {
	public static productsort currentproductsort=null;
	public void addsort(merchat m, String name) throws BaseException {
		if("".equals(name)||name==null) throw new BusinessException("种类名不可为空");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from productsort where merchat_no=? and productsort_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,m.getMerchat_no());
			pst.setString(2,name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				rs.close();
				pst.close();
				throw new BusinessException("种类已存在");
			}
			sql="insert into productsort(merchat_no,productsort_name) values(?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,m.getMerchat_no());
			pst.setString(2, name);
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
	public List<productsort> loadproductsort(merchat m) throws BaseException {
		List<productsort> result=new ArrayList<productsort>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from productsort where merchat_no=? order by productsort_no asc";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, m.getMerchat_no());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				productsort ps=new productsort();
				ps.setProductsort_no(rs.getInt(1));
				ps.setMerchat_no(rs.getInt(2));
				ps.setProductsort_name(rs.getString(3));
				ps.setProductsort_number(rs.getInt(4));
				result.add(ps);
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
	public void changesortname(productsort ps,String name)throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from productsort where productsort_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, ps.getProductsort_no());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("该种类不存在");
			else {
				rs.close();
				pst.close();
				sql="update productsort set productsort_name=? where productsort_no=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, name);
				pst.setInt(2, ps.getProductsort_no());
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
	
	public void changesortnumber(productsort ps,int number)throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from productsort where productsort_no=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, ps.getProductsort_no());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("该种类不存在");
			else {
				rs.close();
				pst.close();
				sql="update productsort set productsort_number=? where productsort_no=?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, number);
				pst.setInt(2, ps.getProductsort_no());
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
	
	public void deletesort(productsort ps) throws BaseException {
		Connection conn=null;
		try {
	
			conn=DBUtil.getConnection();
			String sql="select * from productsort where productsort_no=? ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,ps.getProductsort_no());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) {
				throw new BusinessException("该种类不存在");
			}
			rs.close();
			sql="delete from productsort where productsort_no=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,ps.getProductsort_no());
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
		productsortManager a=new productsortManager();
		List<productsort> b=new ArrayList<productsort>();
		try {
			merchat m=new merchat();
			m.setMerchat_no(1);
			b=a.loadproductsort(m);
			a.changesortname(b.get(1), "meat");


//			a.addsort(m, "chihick");
//			a.addsort(m,"juice");
//			a.addsort(m, "meat");

//			System.out.println(b.get(0).getMerchat_no()+" "+b.get(0).getProductsort_no()+" "+b.get(0).getProductsort_name());
//			System.out.println(b.get(1).getMerchat_no()+" "+b.get(1).getProductsort_no()+" "+b.get(1).getProductsort_name());
//			System.out.println(b.get(2).getMerchat_no()+" "+b.get(2).getProductsort_no()+" "+b.get(2).getProductsort_name());
//			a.deletesort(b.get(1));
//			b=a.loadproductsort();
//			System.out.println(b.get(0).getMerchat_no()+" "+b.get(0).getProductsort_no()+" "+b.get(0).getProductsort_name());
//			System.out.println(b.get(1).getMerchat_no()+" "+b.get(1).getProductsort_no()+" "+b.get(1).getProductsort_name());
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
