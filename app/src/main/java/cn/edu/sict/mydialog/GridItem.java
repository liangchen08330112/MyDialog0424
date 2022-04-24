package cn.edu.sict.mydialog;
//小格内容实体类
public class GridItem {
    int pictures;
    String texts;

    //构造函数：创建GridItem对象的同时，为成员变量赋初值。
    public GridItem(int pictures, String texts) {
        this.pictures = pictures;
        this.texts = texts;
    }

    //Getter和Setter：获取和设置值。
    public int getPictures() {
        return pictures;
    }

    public void setPictures(int pictures) {
        this.pictures = pictures;
    }

    public String getTexts() {
        return texts;
    }

    public void setTexts(String texts) {
        this.texts = texts;
    }
}
