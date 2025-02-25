package group.nhom16.PhongTro.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class HoaDonThang {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date ngayBatDau;
    private Date ngayKetThuc;
    private int soDienCu;
    private int soDienMoi;
    private int soNuocCu;
    private int soNuocMoi;
    private float tongTien;
    private boolean thanhToan;

    @ManyToOne
    @JoinColumn(name = "hopDongId")
    private HopDong hopDong;

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

    public int getSoDienCu() {
        return soDienCu;
    }

    public void setSoDienCu(int soDienCu) {
        this.soDienCu = soDienCu;
    }

    public int getSoDienMoi() {
        return soDienMoi;
    }

    public void setSoDienMoi(int soDienMoi) {
        this.soDienMoi = soDienMoi;
    }

    public int getSoNuocCu() {
        return soNuocCu;
    }

    public void setSoNuocCu(int soNuocCu) {
        this.soNuocCu = soNuocCu;
    }

    public int getSoNuocMoi() {
        return soNuocMoi;
    }

    public void setSoNuocMoi(int soNuocMoi) {
        this.soNuocMoi = soNuocMoi;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public boolean isThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(boolean thanhToan) {
        this.thanhToan = thanhToan;
    }

    public HopDong getHopDong() {
        return hopDong;
    }

    public void setHopDong(HopDong hopDong) {
        this.hopDong = hopDong;
    }
}
