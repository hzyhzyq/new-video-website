package com.yunqi.video.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static boolean isEmail( String str ) {
        String regex = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}" ;
        return match( regex ,str );
    }
    private static boolean match( String regex ,String str ){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher( str );
        return matcher.matches();

    }
    public static String changeToUrl(String url){
        String substring = url.substring(10);
        return "http://192.168.177.129:80/vod"+substring;
    }
    public static String changeToPictureUrl(String url){
        return "http://192.168.177.129:8888/"+url;
    }

}
