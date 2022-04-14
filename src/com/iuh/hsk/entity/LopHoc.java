package com.iuh.hsk.entity;

public class LopHoc {
	private String maLop;
	private String tenLop;

	public LopHoc(String maLop, String tenLop) {
		super();
		this.maLop = maLop;
		this.tenLop = tenLop;
	}

	public LopHoc() {
		super();
	}

	public String getMaLop() {
		return maLop;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	public String getTenLop() {
		return tenLop;
	}

	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}

	@Override
	public String toString() {
		return "LopHoc [maLop=" + maLop + ", tenLop=" + tenLop + "]";
	}

}
