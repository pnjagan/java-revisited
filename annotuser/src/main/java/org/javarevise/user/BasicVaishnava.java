package org.javarevise.user;

public class BasicVaishnava {

    private String dearNameOfVishnu = "";
    private String devoteeName = "";
    private int age = 18;


    public String getDearNameOfVishnu() {
        return dearNameOfVishnu;
    }

    @BuilderProperty
    public void setDearNameOfVishnu(String dearNameOfVishnu) {
        this.dearNameOfVishnu = dearNameOfVishnu;
    }

    public String getDevoteeName() {
        return devoteeName;
    }

    @BuilderProperty
    public void setDevoteeName(String devoteeName) {
        this.devoteeName = devoteeName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString(){
        return dearNameOfVishnu + " dasa , " + devoteeName ;
    }
}
