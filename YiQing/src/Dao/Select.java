package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import utils.DBUtil;


public class Select {
	public boolean select(String time) {
		// TODO Auto-generated method stub
		Connection conn=DBUtil.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean b=false;
		String sql="select * from myinfo where date(Time) = ?";
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, time);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				b=true;
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
		return b;
	}
}
