package test;

import dao.seriesDBOperation;
import VO.user;

import java.util.ArrayList;

/**
 * @auther Alpha_Lin
 * @date 2018/11/28 16:41
 */
public class testQueryUser {
    public static void main(String[] args){
        ArrayList<user> list=new ArrayList<>();
        list= seriesDBOperation.queryAllUsers();
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).getUsername());
            System.out.println(list.get(i).getGender());
            System.out.println(list.get(i).getTelephone());
            System.out.println(list.get(i).getAddress());
            System.out.println(list.get(i).getEmail());
            System.out.println("----------------------");
        }
    }
}
