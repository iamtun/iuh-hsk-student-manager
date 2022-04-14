create database QL_SINHVIEN

use QL_SINHVIEN

create table LopHoc(
	maLop varchar(20) primary key,
	tenLop nvarchar(100) not null,
)

create table SinhVien(
	masv char(10) primary key,
	ho nvarchar(30) not null,
	ten nvarchar(30) not null,
	phai nvarchar(10) not null,
	email varchar(50) not null,
	diaChi nvarchar(100) not null,
	maLop varchar(20) not null
)

alter table SinhVien
add constraint fk_sinhvien_lophoc foreign key (maLop) references LopHoc(maLop)

insert into LopHoc
values('DHKTPM15A', N'Đại học kỹ thuật phần mềm 15A')

insert into SinhVien
values('SV001', N'Lê', N'Tuấn', N'Nam', 'tuan@gmail.com', N'275 Quang Trung', 'DHKTPM15A')

select count(masv)
from SinhVien
where maLop = 'DHKTPM15A'