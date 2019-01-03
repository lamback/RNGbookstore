package VO;

import dao.ImageUtil;
import java.io.InputStream;

public class shoppingCart {
    private String bookName;
    private double price;
    private int number;
    private int bookId;
    private InputStream photo;  //存放图片的IO流

    public shoppingCart(){

    }

    public shoppingCart(String u,double p,int n,int bid){
        this.bookId=bid;
        this.bookName=u;
        this.price=p;
        this.number=n;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setPhoto(InputStream inputStream){
        this.photo=inputStream;
    }

    //图片返回图片存放的地址
    public String getPhoto(){
        String targetPath="D:\\IDEA\\IdeaProjects\\RNGmarket\\web\\photo\\"+bookName+".jpg";
        ImageUtil.readBin2Image(photo,targetPath);
        return "photo/"+bookName+".jpg";
    }
}
