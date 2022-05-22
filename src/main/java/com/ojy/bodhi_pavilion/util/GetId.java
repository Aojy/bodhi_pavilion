package com.ojy.bodhi_pavilion.util;

import java.util.Date;
import java.util.Random;

public class GetId {

    /**
     * 随机生成id
     * @return
     */
    public static String getId(){
        Date date = new Date();
        Integer code = new Random().nextInt(999999);//生成随机数，最大为999999
        if(code < 100000){
            code = code + 100000;//保证随机数为6位数字
        }
        return date.getTime() + code.toString();
    }
}
