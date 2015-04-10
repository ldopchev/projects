/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lyubo
 */
public class Courses {
    private String number;
    private String name;
    private String info;
    private boolean credit = true;
    public Courses(){}
    
    public Courses(String number, String name, String info){
        this.number = number;
        this.name = name;
        this.info = info;
        if(number.contains("NC"))
            credit = false;
    }
    
    public String getNumber(){return number;}
    public String getName(){return name;}
    public String getInfo(){return info;}
    public boolean getCredit(){return credit;}
}
