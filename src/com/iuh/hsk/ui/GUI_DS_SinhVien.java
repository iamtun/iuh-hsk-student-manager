package com.iuh.hsk.ui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.iuh.hsk.dao.SinhVienDAO;
import com.iuh.hsk.entity.SinhVien;

public class GUI_DS_SinhVien extends JFrame{
    private String idClass;
    private DefaultTableModel tableModel;
    private SinhVienDAO sinhVienDAO;
    private List<SinhVien> list;
    private JTable tblDSSV;

    public GUI_DS_SinhVien(String title, String idClass) {
        this.setTitle(title);
        this.idClass = idClass;
    }

    public void doShow() throws Exception {
        setSize(600, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
        sinhVienDAO = new SinhVienDAO();
		addControl();
		setVisible(true);
    }

    private void addControl() throws Exception {
        JPanel pnMain = new JPanel();
		pnMain.add(new JLabel("THÔNG TIN LỚP HỌC"));
		
		//<<------------- TABLE ------------------------------------------------------
		JPanel pnTable = new JPanel();
		
		//------------- MODEL TABLE ------------------------------------------------
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new String[] {"Mã SV", "Họ", "Tên", "Phái", "Email", "Địa chỉ"});

		//------------- READ DATA FROM DATABASE --------------------------------------
		list = new ArrayList<SinhVien>();
		list = sinhVienDAO.getListStudentById(idClass);
		loadDataToTable(list, tableModel);

		//------------- CREATE TABLE AND ADD MODEL ------------------------------------
		tblDSSV = new JTable();
		tblDSSV.setModel(tableModel);

		//------------- RESIZE COLUMN IN TABLE ----------------------------------------
		
		pnTable.add(new JScrollPane(tblDSSV), BorderLayout.CENTER);
		pnMain.add(pnTable);
        this.add(pnMain);
    }

    private void loadDataToTable(List<SinhVien> list, DefaultTableModel model) throws Exception {
		model.setRowCount(0);
		for(SinhVien sv : list) {
			model.addRow(new Object[] {sv.getMaSV(), sv.getHoSV(), sv.getTenSV(), sv.getPhaiSV(), sv.getEmail(), sv.getDiaChi()});
		}
	}
}
