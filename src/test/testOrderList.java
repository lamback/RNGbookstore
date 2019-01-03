package test;

import VO.order;
import dao.seriesDBOperation;

/**
 * @auther Alpha_Lin
 * @date 2018/11/28 13:20
 */
public class testOrderList {
    public static void main(String[] args){
        /**
        ArrayList<order> list=new ArrayList<>();

        list= seriesDBOperation.queryOrderList();
        System.out.println("所有用户订单");
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).getBookId());
            System.out.println(list.get(i).getBookName());
            System.out.println(list.get(i).getNumber());
            System.out.println(list.get(i).getOrderId());
            System.out.println(list.get(i).getDate());
            System.out.println(list.get(i).getPrice());
            System.out.println(list.get(i).getPhoto());
            System.out.println("---------------------------");
        }

        seriesDBOperation.updateOrderState(128);


        System.out.println("Tom的订单列表：");
        list=seriesDBOperation.queryUserOrderList("Tom");
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).getBookId());
            System.out.println(list.get(i).getBookName());
            System.out.println(list.get(i).getNumber());
            System.out.println(list.get(i).getOrderId());
            System.out.println(list.get(i).getDate());
            System.out.println(list.get(i).getPrice());
            System.out.println(list.get(i).getPhoto());
            System.out.println("---------------------------");
        }

         **/

        order order=new order();
        order.setBookId(124);
        order.setUsername("Rose");
        order.setNumber(2);
        seriesDBOperation.add2OrderList("Rose",2);

    }
}
