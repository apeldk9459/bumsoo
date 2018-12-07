package com.alarmy.konyang.alarmy;

public class MyItem {
    private String idx;
    private String title;
    private String name;
    private String time;

    public String getIdx(){
        return idx;
    }

    public void setIdx(String idx){
        this.idx=idx;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

   public String getName(){
        return name;
   }

   public void setName(String name){
        this.name = name;
   }

    public String getTime(){
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public MyItem(String idx, String title, String name, String time){
        this.idx=idx;
        this.title=title;
        this.name=name;
        this.time=time;
    }
}
