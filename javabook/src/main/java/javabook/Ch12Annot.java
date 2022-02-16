package javabook;

import static javabook.Utils.*;

public class Ch12Annot {
    public static  void main(String [] a) {
        Ch12Annot ca = new Ch12Annot();
        ca.run();
    }


    public void run () {

        //Char
        Character charVar = 1;
        charVar = Character.valueOf('a');//Accepts only a char - both the valueOf and Character.

        //Boolean
        Boolean bool = true;
        bool = Boolean.valueOf("TRUE*"); // accepts both string and boolean
        bool = bool & true;
       log("bool :"+bool);

        //Numberics
        Integer intObj = 100; //Default literal is int, can be made long by apending a L or l
        Float floatObj = 10.0f; //default, a decimal literal is double.

        floatObj = Float.valueOf("9.8");//Numeric's take their type and String in args

        // All numerics have xxxValue method for all other numerics
        log("Byte value float variable: "+floatObj.byteValue());

        log("Float value arithmetic :" +floatObj*2);




    }
}
