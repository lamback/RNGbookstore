package test;

import dao.seriesDBOperation;
import VO.shoppingCart;

import java.util.ArrayList;

public class testShoppingCart {
    public static void main(String[] args){

        ArrayList<shoppingCart> list=new ArrayList<>();
        list=seriesDBOperation.queryShoppingCart("user");
        for(int i=0;i<list.size();i++){
            System.out.println("书名："+list.get(i).getBookName());
            System.out.println("书籍Id："+list.get(i).getBookId());
            System.out.println("价格："+list.get(i).getPrice());
            System.out.println("数量："+list.get(i).getNumber());
            System.out.println("图片地址："+list.get(i).getPhoto());
        }


       // seriesDBOperation.add2Shoppingcart("Charles",121);
       // seriesDBOperation.increaseBook("Jack",122,true);
    }
}
