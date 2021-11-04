package com.example.subproject_ma_14att_n7_baohaihaotutuong;

public class Sender {

    private String name;
    private String lbtxtview;
    private int img;

    public Sender(String name, String lbtxtview, int img) {
        this.name = name;
        this.lbtxtview = lbtxtview;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLbtxtview() {
        return lbtxtview;
    }

    public void setLbtxtview(String lbtxtview) {
        this.lbtxtview = lbtxtview;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
