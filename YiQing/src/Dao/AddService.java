package Dao;

import java.sql.Connection;
import java.sql.Statement;

import utils.DBUtil;

public class AddService {
	public void add(String table,String sheng,String xinzeng,String leiji,String zhiyu,String dead,String time,char kind) {
		String sql = "insert into "+table+" (Province,Newconfirmed_num ,Confirmed_num,Cured_num,Dead_num,Time,Kind) values('" + sheng + "','" + xinzeng +"','" + leiji +"','" + zhiyu + "','" + dead+ "','" + time+ "','" + kind+ "')";
		System.out.println(sql);
		Connection conn = DBUtil.getConn();
		Statement state = null;
		int a = 0;
		try {
			state = conn.createStatement();
			a=state.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(state, conn);
		}		
	}
}
