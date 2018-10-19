package client;

import java.util.HashMap;

public class Demo {

    public static String getStr(){
        String str="haha";
        return str;
    }
    public  static void main(String[]args){
        //参照量
        long ll=2000000000000L;
            //假如数据库的值为
            long l=1539513324000L;
            //当前时间为
            long l1=System.currentTimeMillis();
        //测试10次更改。。
        for (int i = 0; i < 10; i++) {
                if(l<ll){
                    //将不like状态改为like，移位增大一倍；
                    l=l<<1;
                }
                else {
                    //此时like的当前状态为like，将其改为不like，改为当前时间
                    l=System.currentTimeMillis();
                }
            String str= l>ll?"状态为like":"状态为nolike";
                System.out.println("状态为："+str);
                long time= l>ll? l>>1:l;
                System.out.println("收藏time为:"+time);
        }
        System.out.println("变换前:"+l);
        System.out.println("变换后:"+ll);
    }
}
