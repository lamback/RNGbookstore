package VO;

import dao.ImageUtil;
import java.io.*;

/**
 * 在书籍信息录入时，书籍图片存放地址的修改在setPhotoPath()
 * 中同时getFileInputStream()也要设置
 */
public class books {
    private int bookId;
    private String bookName;
    private String category;
    private FileInputStream fileInputStream;
    private InputStream inputStream;
    private String photoPath;
    private String author;
    private String information;
    private double price;

    public books(){

    }

    //初始化书籍信息
    public books(String name,String category,String photoPath,
                 String author,String information,float price){
        this.bookName=name;
        this.category=category;
        this.author=author;
        this.information=information;
        this.price=price;
        this.photoPath=photoPath;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public FileInputStream getFileInputStream(){
        try {
            String path="photo\\"+photoPath;
            this.fileInputStream = ImageUtil.readImage(path);
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return this.fileInputStream;
    }

    public void setFileInputStream(){
        String targetPath="photo\\"+photoPath;
        try {
            this.fileInputStream = new FileInputStream(new File(targetPath));
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
    }

    public void setInputStream(InputStream inputStream){
        this.inputStream=inputStream;
    }

    public String getPhotoPath() {
        String targetPath="photo\\"+bookName+".jpg";
        ImageUtil.readBin2Image(inputStream,targetPath);
        return bookName+".jpg";
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
