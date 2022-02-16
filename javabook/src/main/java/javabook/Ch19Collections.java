package javabook;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.LongAdder;

public class Ch19Collections {
    public static void main(String a[]) {
        HashMap<String, Integer> prices = new HashMap<>();

        // insert entries to the HashMap
        prices.put("Shoes", 200);
        prices.put("Bag", 300);
        prices.put("Pant", 150);
        System.out.println("HashMap: " + prices);

        // recompute the value of Shoes with 10% discount
        int newPrice = prices.compute("Shoes", (key, value) -> value - value * 10/100);
        System.out.println("Discounted Price of Shoes: " + newPrice);

        // print updated HashMap
        System.out.println("Updated HashMap: " + prices);
        System.out.println("--***************************--");

        mapPlay();


    }

    private static void mapPlay () {
// a ConcurrentHashMAp of string keys and Long values
        HashMap<String, Integer> map = new HashMap<>();
   map.put("apple", 3); map.put("mango", 4);
   System.out.println("map before calling compute: " + map);
   // in JDK 8 - you can also use compute() and lambda expression to
        // atomically update a value or mapping in ConcurrentHashMap
        //
   map.compute("apple", (key, value) -> value == null ? 1 : value + 1);
   System.out.println("map after calling compute on apple: " + map);

   // you can also use computeIfAbsent() or computeIfPresent() method
        // Constructor of LongAdder will be only called when a value for
        // given key is not exists
        //

        HashMap<String, Integer> map2 = new HashMap<>();

        System.out.println("************************MAP 2 STAGE 1 Start*******************");
        System.out.println(map2);

        map2.computeIfAbsent("apple", key -> Double.valueOf(key.length()*Math.random()*10).intValue());
        map2.computeIfAbsent("mango", key -> Double.valueOf(key.length()*Math.random()*10).intValue());
        map2.computeIfAbsent("goa", key -> Double.valueOf(key.length()*Math.random()*10).intValue());

        System.out.println(map2);
        System.out.println("************************MAP 2 STAGE 1 Complete*******************");
        map2.computeIfAbsent("goa", key -> Double.valueOf(key.length()*Math.random()*10).intValue());
        map2.computeIfAbsent("apple", key -> Double.valueOf(key.length()*Math.random()*10).intValue());
        map2.computeIfAbsent("mango", key -> Double.valueOf(key.length()*Math.random()*10).intValue());

        System.out.println(map2);
        System.out.println("************************MAP 2 STAGE 2 Complete*******************");
        map2.computeIfPresent("goa", (key,value) -> value<20?value*2:value);
        map2.computeIfPresent("apple", (key,value) -> value<20?value*2:value);
        map2.computeIfPresent("pinapple", (key,value) -> value<20?value*2:value);


        System.out.println(map2);
        System.out.println("************************MAP 2 STAGE 3 Complete*******************");
        int age = 14;
        assert age >= 18 : "Cannot Vote";
        //assert false : "make it false";
        System.out.println("The voter's age is " + age);

    }
}
