package com.blog.ln.utils;

public class TestOneBeam {

    public static int count = 0;

    private static TestOneBeam oneBeam;

    public static TestOneBeam getOneBeam(){
        if(oneBeam ==null){
            oneBeam = new TestOneBeam();
        }
        return  oneBeam;
    }
}
