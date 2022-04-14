package com.iuh.hsk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.iuh.hsk.connectDB.ConnectDB;
import com.iuh.hsk.entity.SinhVien;

public class SinhVienDAO {
    public List<SinhVien> getListStudentById(String idClass) throws Exception {
		List<SinhVien> list = new ArrayList<SinhVien>();
		String sql = "select * from SinhVien where maLop = ?";
		try(
				Connection conn = ConnectDB.openConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
                pstmt.setString(1, idClass);
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					String maSV = rs.getString("masv");
                    String hoSV = rs.getString("ho");
                    String tenSV = rs.getString("ten");
                    String phaiSV = rs.getString("phai");
                    String email = rs.getString("email");
                    String diaChi = rs.getString("diaChi");
                    String maLop = rs.getString("maLop");

                    SinhVien sv = new SinhVien(maSV, hoSV, tenSV, phaiSV, email, diaChi, maLop);
                    list.add(sv);
				}
				
				return list;
			}
		}
	}
}
