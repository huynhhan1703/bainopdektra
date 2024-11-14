/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class TaiKhoan {
    private String tendangnhap;
    private String matkhau;

    public TaiKhoan() {
    }

    public TaiKhoan(String tendangnhap, String matkhau) {
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" + "tendangnhap=" + tendangnhap + ", matkhau=" + matkhau + '}';
    }
    
}
