package VO;

import dao.ImageUtil;

import java.io.InputStream;
import java.util.Calendar;

public class order {
    private String bookName;
    private int bookId;
    private String username;//该订单属于哪个用户
    private InputStream photo;
    private int orderId;
    private String orderState;
    private float price;
    private int number;
    private String date;

    public order(){
        setDate();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getPhoto() {
        String targetPath="D:\\IDEA\\IdeaProjects\\RNGmarket\\web\\photo\\"+bookName+".jpg";
        ImageUtil.readBin2Image(photo,targetPath);
        return bookName+".jpg";
    }

    public void setPhoto(InputStream photo) {
        this.photo = photo;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date=date;
    }

    public void setDate(){
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        String date=""+year+"-"+month+"-"+day;
        this.date=date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
