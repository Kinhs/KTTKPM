package group.nhom16.PhongTro.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class HopDong {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date ngayBatDau;
    private Date ngayKetThuc;
    private float tienCoc;
    private float tienSoNuoc;
    private float tienSoDien;

    @OneToOne
    @JoinColumn(name = "khachId")
    private Khach khach;

    @ManyToOne
    @JoinColumn(name = "phongId")
    private Phong phong;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public float getTienCoc() {
        return tienCoc;
    }

    public void setTienCoc(float tienCoc) {
        this.tienCoc = tienCoc;
    }

    public float getTienSoNuoc() {
        return tienSoNuoc;
    }

    public void setTienSoNuoc(float tienSoNuoc) {
        this.tienSoNuoc = tienSoNuoc;
    }

    public float getTienSoDien() {
        return tienSoDien;
    }

    public void setTienSoDien(float tienSoDien) {
        this.tienSoDien = tienSoDien;
    }

    public Khach getKhach() {
        return khach;
    }

    public void setKhach(Khach khach) {
        this.khach = khach;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }
}
