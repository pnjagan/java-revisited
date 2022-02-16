package javabook;

public class Ch7InnerClass {

    //normal inner class
    class A {
        public void m() {System.out.println("A");}
    }

    //static inner class
    static class SA {
        public void m() {System.out.println("SA");}
    }

    private void run(int args,String ... values) {

        if (values.length != args) {
            System.out.println("Invalid arguments");
        } else {
            System.out.println("Valid arguments");
        }
        new A().m();

        class B {
            public void m() {System.out.println("B");}
        }

        //classes in local scope as well
        new B().m();
    }


    //Demonstration of var usage
    public static void main(String[] a) {
        var cic = new Ch7InnerClass();
        cic.run(2, "Hare" , "Krishna" , "Jai");
        new SA().m();


    }
}
