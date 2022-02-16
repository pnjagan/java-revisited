package javabook;

import static java.lang.System.out;


class MyClass {
    MyClass() {
        out.println("No Args constructor");
    }

    MyClass(String a) {
        out.println("Arg :"+ a);
    }
}

interface MyClassInt {
    MyClass cons();
}

public class Ch15Lambda {

    private String prefix = "Sri";

    public static void main(String a[]){
        //AddNumbers addNum = (Number n1, Number n2) ->  (n1.intValue() + n2.intValue());
        Ch15Lambda cl = new Ch15Lambda();

        NumOperations addNum =  (n) ->  (n[0].intValue() + n[1].intValue());

        out.println(addNum.oper(1,2));
        out.println(addNum.oper(7,2));
        out.println(addNum.oper(11,12));

        UniParamTransformFunction<String> sf = cl::stringReverse;

        UniParamTransformFunction<Number> numReverse = n-> (n==null || n.doubleValue() == 0)?  0:  (1/n.doubleValue());

        out.println(sf.transform("narayana"));

        out.println(numReverse.transform(null));

        MyClassInt mci = MyClass::new;

        out.println(mci.cons());
        out.println("*****************************************************");
        SomeOtherClass soc = new SomeOtherClass();
        soc.setString("VISHNU");
        out.println("String reverse : "+ soc.doTransform( cl::stringReverse) );
        soc.setString("Govinda");
        out.println("String reverse : "+ soc.doTransform( cl::stringReverse) );

    }

    private  String stringReverse(String s) {

        out.println("Stack:"+Thread.currentThread().getStackTrace()[0]);
        out.println("THIS :"+this);
        prefix += "OM";
        StringBuffer sb1=new StringBuffer(); for(char c : (prefix + s).toCharArray()) {sb1.insert(0,c);}; return sb1.toString();
    }
}


class SomeOtherClass {

    private String store;
    public void setString(String s){store =s;}

    public String doTransform(UniParamTransformFunction<String> utff) {
         return utff.transform(store);
    }
}

interface NumOperations {
    Number oper(Number ...n1);
}

interface UniParamTransformFunction<T> {
    T transform(T t);
}