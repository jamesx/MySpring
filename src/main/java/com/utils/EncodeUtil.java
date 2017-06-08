package com.utils;
import com.google.common.base.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.charset.Charset;

/**
 * Created by Administrator on 2017/2/28.
 */
public class EncodeUtil {
    private static final Logger log= LogManager.getLogger();
    /**
     * ISO_8859_1转utf-8
     */
    public static  String toUTF_8(String str){
        str=new String(str.getBytes(Charsets.ISO_8859_1),Charsets.UTF_8);
        return str;
    }
    /**
     * UTF_8转ISO_8859_1
     */
    public static String toISO_8859_1(String str){
        str=new String(str.getBytes(Charsets.UTF_8),Charsets.ISO_8859_1);
        return str;
    }
    /**
     * 字符串转码
     *
     */
    public static String Encode(String str, Charset from,Charset to){
        str=new String(str.getBytes(from),to);
        return str;
    }
//    /**
//     *  BCryptPasswordEncoder密码加密
//     */
//    public static  String BCrypt(String pass){
//        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
//        String hashPass = encode.encode(pass);
//        return hashPass;
//    }
}
