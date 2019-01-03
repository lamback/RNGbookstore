package test;


import VO.user;
import dao.seriesDBOperation;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Alpha_Lin
 * Date: 2018/11/28
 * Time: 18:46
 */
public class test {
    public static void main(String[] args){

        ArrayList<user> list=new ArrayList<>();

        list=seriesDBOperation.queryAllUsers();
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).getUsername()+"  "+list.get(i).getPassword());
        }

    }
}
