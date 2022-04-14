package com.iuh.hsk.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.iuh.hsk.dao.LopHocDAO;
import com.iuh.hsk.entity.LopHoc;

public class GUI_LopHoc extends JFrame implements ActionListener, MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private JTable tblLopHoc;
	private List<LopHoc> list;
	private LopHocDAO lhDAO;
	private JButton btnFirst;
	private JButton btnPrev;
	private JButton btnNext;
	private JButton btnLast;
	private JTextField txtMaLop;
	private JTextField txtTenLop;
	private JTextField txtSiSo;
	private JButton btnAdd;
	private JButton btnSave;
	private JButton btnChange;
	private JButton btnDel;
	private JButton btnShowInfo;
	private JPanel pnMain;
	public GUI_LopHoc(String title) {
		this.setTitle(title);
		
	}
	
	private void doShow() throws SQLException, Exception {
		setSize(600, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		lhDAO = new LopHocDAO();
		addControl();
		
		setVisible(true);
	}
	 
	private void addControl() throws SQLException, Exception {
		pnMain = new JPanel();
		pnMain.add(new JLabel("THÔNG TIN LỚP HỌC"));
		
		//<<------------- TABLE ------------------------------------------------------
		JPanel pnTable = new JPanel();
		
		//------------- MODEL TABLE ------------------------------------------------
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new String[] {"Mã lớp", "Tên lớp", "Sĩ số"});

		//------------- READ DATA FROM DATABASE --------------------------------------
		list = new ArrayList<LopHoc>();
		list = lhDAO.getListClass();
		loadDataToTable(list, tableModel);

		//------------- CREATE TABLE AND ADD MODEL ------------------------------------
		tblLopHoc = new JTable();
		tblLopHoc.setModel(tableModel);

		//------------- RESIZE COLUMN IN TABLE ----------------------------------------
		tblLopHoc.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblLopHoc.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		pnTable.add(new JScrollPane(tblLopHoc), BorderLayout.CENTER);
		pnMain.add(pnTable);
		
		//---------------------------------------------------------------------------->>

		//------------- CONTROL FIRST - PREVIOUS - NEXT - LAST -------------------------
		JPanel pnControlOptions = new JPanel();
		btnFirst = new JButton("<<");
		btnPrev = new JButton("<");
		btnNext = new JButton(">");
		btnLast = new JButton(">>");

		//-------------- ADD BUTTON TO PANEL -------------------------------------------
		pnControlOptions.add(btnFirst);
		pnControlOptions.add(btnPrev);
		pnControlOptions.add(btnNext);
		pnControlOptions.add(btnLast);
		pnMain.add(pnControlOptions);
		//----------------------------------------------------------------------------->>

		//-------------- INPUT ---------------------------------------------------------
		//MaLop
		JPanel pnMaLop = new JPanel();
		pnMaLop.add(new JLabel("Mã lớp:   "));
		txtMaLop = new JTextField(50);
		pnMaLop.add(txtMaLop);
		pnMain.add(pnMaLop);

		//TenLop
		JPanel pnTenLop = new JPanel();
		pnTenLop.add(new JLabel("Tên lớp: "));
		txtTenLop = new JTextField(50);
		pnTenLop.add(txtTenLop);
		pnMain.add(pnTenLop);

		//SiSo
		JPanel pnSiSo = new JPanel();
		pnSiSo.add(new JLabel("Sỉ số:      "));
		txtSiSo = new JTextField(50);
		txtSiSo.setEditable(false);
		pnSiSo.add(txtSiSo);
		pnMain.add(pnSiSo);
		//----------------------------------------------------------------------------->>

		//---------------- CONTROL BUTTONS --------------------------------------------
		JPanel pnControlButtons = new JPanel();
		btnAdd = new JButton("Thêm");
		btnSave = new JButton("Lưu");
		btnSave.setEnabled(false);
		btnChange = new JButton("Sửa");
		btnDel = new JButton("Xóa");
		btnShowInfo = new JButton("Xem thông tin sinh viên");
		
		//----------------- ADD BUTTONS TO CONTROL ------------------------------------
		pnControlButtons.add(btnAdd);
		pnControlButtons.add(btnSave);
		pnControlButtons.add(btnChange);
		pnControlButtons.add(btnDel);
		pnControlButtons.add(btnShowInfo);
		pnMain.add(pnControlButtons);
		//----------------------------------------------------------------------------->>
		this.add(pnMain);

		//------------------ ADD EVENT LISTENER -----------------------------------------
		btnFirst.addActionListener(this);
		btnPrev.addActionListener(this);
		btnNext.addActionListener(this);
		btnLast.addActionListener(this);
		btnAdd.addActionListener(this);
		btnChange.addActionListener(this);
		btnDel.addActionListener(this);
		btnSave.addActionListener(this);
		btnShowInfo.addActionListener(this);
		tblLopHoc.addMouseListener(this);
		pnMain.addMouseListener(this);
	}
	
	public static void main(String[] args) throws SQLException, Exception {
		GUI_LopHoc gui = new GUI_LopHoc("Lớp học");
		gui.doShow();
	}
	
	private void loadDataToTable(List<LopHoc> list, DefaultTableModel model) throws SQLException, Exception {
		model.setRowCount(0);
		for(LopHoc lh : list) {
			model.addRow(new Object[] {lh.getMaLop(), lh.getTenLop(), lhDAO.getCountStudentInClass(lh.getMaLop())});
		}
	}

	private LopHoc createClass() {
		String maLop = txtMaLop.getText().trim();
		String tenLop = txtTenLop.getText().trim();
		LopHoc lh = new LopHoc(maLop, tenLop);

		return lh;
	}

	private void clearInput() {
		txtMaLop.setText("");
		txtTenLop.setText("");
		txtSiSo.setText("");
	}

	private void tableChangeIndex(int index) {
		txtMaLop.setText(tblLopHoc.getValueAt(index, 0).toString());
		txtTenLop.setText(tblLopHoc.getValueAt(index, 1).toString());
		txtSiSo.setText(tblLopHoc.getValueAt(index, 2).toString());
	}

	private boolean checkUserEnter() {
		if((txtMaLop.getText().trim().equals("") && txtTenLop.getText().trim().equals("")))
			return false;
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if(obj.equals(btnFirst)) {
			tblLopHoc.setRowSelectionInterval(0, 0);
			int index = tblLopHoc.getSelectedRow();
			tableChangeIndex(index);
		}
		
		if(obj.equals(btnPrev)) {
			int index = tblLopHoc.getSelectedRow();
			if(index == 0)
				tblLopHoc.setRowSelectionInterval(list.size() - 1, list.size() - 1);
			else
				tblLopHoc.setRowSelectionInterval(index - 1, index - 1);
			
			index = tblLopHoc.getSelectedRow();
			tableChangeIndex(index);
		}

		if(obj.equals(btnNext)) {
			int index = tblLopHoc.getSelectedRow();
			int listSize = list.size() -1;
			if(index == listSize)
				tblLopHoc.setRowSelectionInterval(0, 0);
			else 
				tblLopHoc.setRowSelectionInterval(index + 1, index + 1);

			index = tblLopHoc.getSelectedRow();
			tableChangeIndex(index);
		}

		if(obj.equals(btnLast)) {
			tblLopHoc.setRowSelectionInterval(list.size() - 1, list.size() - 1);
			int index = tblLopHoc.getSelectedRow();
			tableChangeIndex(index);
		}

		if(obj.equals(btnAdd)) {
			if(checkUserEnter()) {
				LopHoc lh = createClass();
				try {
					lhDAO.insertClass(lh);

					list = lhDAO.getListClass();

					loadDataToTable(list, tableModel);

					clearInput();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
				}
			}else {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
			}
		}

		if(obj.equals(btnChange)) {
			
			if(checkUserEnter()) {
				try {
					LopHoc lh = createClass();

					lhDAO.updateClass(lh);
					
					list = lhDAO.getListClass();

					loadDataToTable(list, tableModel);

					clearInput();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
				}
			}else {
				System.out.println("null");
			}
		}

		if(obj.equals(btnDel)) {
			if(checkUserEnter()) {
				try {
					LopHoc lh = createClass();

					if(lhDAO.getCountStudentInClass(lh.getMaLop()) > 0) {
						JOptionPane.showMessageDialog(this, "Lớp này còn sinh viên không thể xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
						return;
					} else {
						lhDAO.deleteClass(lh);
					
						list = lhDAO.getListClass();

						loadDataToTable(list, tableModel);

						clearInput();
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
				}
			}else {
				System.out.println("null");
			}
		
		}

		if(obj.equals(btnShowInfo)) {
			int index = tblLopHoc.getSelectedRow();
			String idClass = tblLopHoc.getValueAt(index, 0).toString();
			
			GUI_DS_SinhVien gui = new GUI_DS_SinhVien("Danh sách sinh viên", idClass);
			try {
				this.dispose();
				gui.doShow();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		if(obj.equals(tblLopHoc)) {
			txtMaLop.setEditable(false);
			int index = tblLopHoc.getSelectedRow();
			txtMaLop.setText(tblLopHoc.getValueAt(index, 0).toString());
			txtTenLop.setText(tblLopHoc.getValueAt(index, 1).toString());
			txtSiSo.setText(tblLopHoc.getValueAt(index, 2).toString());
		}

		if(obj.equals(pnMain)) {
			txtMaLop.setEditable(true);
			clearInput();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

}