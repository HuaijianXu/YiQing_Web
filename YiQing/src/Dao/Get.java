package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Bean.Info;
import utils.DBUtil;

public class Get {
	public List<Info> listAll(String date1,String date2) {
		ArrayList<Info> list = new ArrayList<>();
		Connection conn=DBUtil.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select * from myinfo where Kind ='1' and Time between ? and ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date1);
			pstmt.setString(2, date2);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Info yq = new Info();
				yq.setId(rs.getInt(1));
				yq.setDate(rs.getString(8));
				yq.setProvince(rs.getString(2));
				yq.setNewconfirmed_num(rs.getString(3));
				yq.setConfirmed_num(rs.getString(4));
				yq.setCured_num(rs.getString(6));
				yq.setDead_num(rs.getString(7));
				list.add(yq);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public List<Info> listAll2(String date1,String date2) {
        ArrayList<Info> list = new ArrayList<>();
        Connection conn=DBUtil.getConn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql="select * from myinfo where Kind ='2' and Time between ? and ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, date1);
            pstmt.setString(2, date2);
            System.out.println(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Info yq = new Info();
                yq.setId(rs.getInt(1));
                yq.setDate(rs.getString(8));
                yq.setProvince(rs.getString(2));
                yq.setNewconfirmed_num(rs.getString(3));
                yq.setConfirmed_num(rs.getString(4));
                yq.setCured_num(rs.getString(6));
                yq.setDead_num(rs.getString(7));
                list.add(yq);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
