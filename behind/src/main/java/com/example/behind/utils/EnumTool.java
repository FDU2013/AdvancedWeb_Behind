package com.example.behind.utils;

import com.example.behind.common.domain_attributes.CDKeyState;

public class EnumTool {
    public static String CDKeyState2Str(CDKeyState cdKeyState){
        if(cdKeyState == null) return null;
        switch (cdKeyState){
            case Unused: return "未使用";
            case Used: return "已兑现";
        }
        System.out.println("please check error!!!!");
        return null;
    }


}
