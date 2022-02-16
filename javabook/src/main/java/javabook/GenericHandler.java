package javabook;

public class GenericHandler <T extends Comparable>{
    T t1,t2;

    public GenericHandler(T t1, T t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    public T bigger () {
        if( t1.compareTo(t2) > 0) {
            return t1;
        }else {
            return t2;
        }

    }

    public <S> S someM(S s) {
        return s;
    }

    public static void main(String arg[]) {
        GenericHandler<String> ghs  = new GenericHandler<>("A", "B");
        System.out.println("Bigger :" + ghs.bigger());

        GenericHandler<Integer> ghi  = new GenericHandler<>(100, 20);
        System.out.println("Bigger :" + ghi.bigger());

        GenericHandler<Comparable> ghn = null;

        GD<A> gda = new GD<A>();
        GD<B> gdb = new GD<B>();

        GD<B> gdb2 = new GD();// TYpe is inferred from Return Assignment.

        A a = null;
        B b = new B();

        a = b;

        System.out.println( "Type inferred :"+ ghs.<String>someM("Hello World" ).getClass()); //Type inferred to be a String



//        gda = gdb; // WIll not work



    }

}


class A {

}

class B extends A {

}

class GD <T extends A> {

}