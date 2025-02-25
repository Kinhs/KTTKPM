package group.nhom16.PhongTro.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class TaiSan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String ten;
    private String tinhTrang;

    @ManyToOne
    @JoinColumn(name = "phongId")
    @Cascade(CascadeType.ALL)
    private Phong phong;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }
}
