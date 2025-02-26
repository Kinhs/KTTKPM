package group.nhom16.PhongTro.model;

import jakarta.persistence.*;

@Entity
public class ThanhVien {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String tenDangNhap;
    private String matKhau;
    private String vaiTro;

    @OneToOne
    @JoinColumn(name = "khachId")
    private Khach khach;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public Khach getKhach() {
        return khach;
    }

    public void setKhach(Khach khach) {
        this.khach = khach;
    }
}
