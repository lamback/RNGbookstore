package test;

import dao.seriesDBOperation;
import VO.user;

public class testLoginAndRegistered {
    public static void main(String[] args){
        user userLogin=new user("Charles","123456");
        System.out.println(seriesDBOperation.login(userLogin));

        user register=new user("Apha_Lin","123456","男","12598756","南昌大学前湖校区","1547956658@163.com");
        System.out.println(seriesDBOperation.registered(register));

    }
}
