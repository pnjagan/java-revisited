package org.javarevise.user;

public class KrishnaLilaRV1 {

    public static void main(String a[]){
        BasicVaishnava vaishnava = new BasicVaishnavaBuilder()
                .setDearNameOfVishnu("Vishnu")
                .setDevoteeName("Jagannathan")
                .build();
        System.out.println("v :"+vaishnava);
    }
}
