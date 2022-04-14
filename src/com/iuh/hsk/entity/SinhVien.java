package com.iuh.hsk.entity;

public class SinhVien {
	private String maSV;
	private String hoSV;
	private String tenSV;
	private String phaiSV;
	private String email;
	private String diaChi;
	private String maLop;

	public SinhVien(String maSV, String hoSV, String tenSV, String phaiSV, String email, String diaChi, String maLop) {
		super();
		this.maSV = maSV;
		this.hoSV = hoSV;
		this.tenSV = tenSV;
		this.phaiSV = phaiSV;
		this.email = email;
		this.diaChi = diaChi;
		this.maLop = maLop;
	}

	public SinhVien() {
		super();
	}

	public String getMaSV() {
		return maSV;
	}

	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}

	public String getHoSV() {
		return hoSV;
	}

	public void setHoSV(String hoSV) {
		this.hoSV = hoSV;
	}

	public String getTenSV() {
		return tenSV;
	}

	public void setTenSV(String tenSV) {
		this.tenSV = tenSV;
	}

	public String getPhaiSV() {
		return phaiSV;
	}

	public void setPhaiSV(String phaiSV) {
		this.phaiSV = phaiSV;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getMaLop() {
		return maLop;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	@Override
	public String toString() {
		return "SinhVien [maSV=" + maSV + ", hoSV=" + hoSV + ", tenSV=" + tenSV + ", phaiSV=" + phaiSV + ", email="
				+ email + ", diaChi=" + diaChi + ", maLop=" + maLop + "]";
	}

}
