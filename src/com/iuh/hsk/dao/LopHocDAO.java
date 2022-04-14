package com.iuh.hsk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iuh.hsk.connectDB.ConnectDB;
import com.iuh.hsk.entity.LopHoc;

public class LopHocDAO {
	public List<LopHoc> getListClass() throws SQLException, Exception {
		List<LopHoc> list = new ArrayList<LopHoc>();
		String sql = "select * from LopHoc";
		try(
				Connection conn = ConnectDB.openConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					String maLop = rs.getString("maLop");
					String tenLop = rs.getString("tenLop");
					LopHoc lh = new LopHoc(maLop, tenLop);
					list.add(lh);
				}
				
				return list;
			}
		}
	}
	
	public int getCountStudentInClass(String idClass) throws SQLException, Exception {
		String sql = "select count = count(masv) from SinhVien where maLop = ?";
		int count = 0;
		try(
				Connection conn = ConnectDB.openConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			pstmt.setString(1, idClass);
			
			try(ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
					count = rs.getInt("count");
				}
			}
		}
		return count;
	}

	public boolean insertClass(LopHoc lh) throws SQLException, Exception {
		String sql = "insert into LopHoc values(?, ?)";
		try(
			Connection conn = ConnectDB.openConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setString(1, lh.getMaLop());
			pstmt.setString(2, lh.getTenLop());

			return pstmt.executeUpdate() > 0;
		}
	}

	public boolean updateClass(LopHoc lh) throws Exception {
		String sql = "update LopHoc set tenLop = ? where maLop = ?";
		try(
			Connection conn = ConnectDB.openConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setString(1, lh.getTenLop());
			pstmt.setString(2, lh.getMaLop());

			return pstmt.executeUpdate() > 0;
		}
	}

	public boolean deleteClass(LopHoc lh) throws SQLException, Exception {
		String sql = "delete from LopHoc where maLop = ?";
		try(
			Connection conn = ConnectDB.openConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setString(1, lh.getMaLop());
			return pstmt.executeUpdate() > 0;
		}
	}

	public LopHoc getClass(String idClass) throws SQLException, Exception{
		String sql = "select * from LopHoc where maLop = ?";
		try(
			Connection conn = ConnectDB.openConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setString(1, idClass);
			try(ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
					String maLop = rs.getString("maLop");
					String tenLop = rs.getString("tenLop");

					LopHoc lh = new LopHoc(maLop, tenLop);
					return lh;
				}
			}
		}

		return null;
	}
}
