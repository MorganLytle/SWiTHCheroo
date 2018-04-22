package com.rick.swithcheroo;

public class Channel_obj {
    int state;
    int num;
    String name;

    public Channel_obj(int num_ch){
        state = -1;
        num = num_ch;
    }

    public void setName(String n){
        name = n;
    }

    public void clearState(){
        state = -1;
    }

    public void setState(int i){
        state = i;
    }
}
