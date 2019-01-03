package test;

import dao.seriesDBOperation;
import VO.books;

import java.util.ArrayList;

public class testInputBook {
    public static void main(String[] args){

         books book=new books("数据库", "文学类","白夜行.PNG",
                         "东野圭吾","《白夜行》是日本作家东野圭吾创作的长篇小说，也是其代表作。该小说于1997年1月至1999年1月间连载于期刊，单行本1999年8月在日本发行。",
                        (float)51.23);
         seriesDBOperation.entry(book);

        ArrayList<books> list=new ArrayList<>();
        list=seriesDBOperation.queryBooksByCategory("文学类");
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).getBookId());
            System.out.println(list.get(i).getBookName());
            System.out.println(list.get(i).getAuthor());
            System.out.println(list.get(i).getPrice());
            System.out.println(list.get(i).getInformation());
            System.out.println(list.get(i).getPhotoPath());
        }
    }
}
