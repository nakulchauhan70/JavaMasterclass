//package com.masterclass;
//
//
//import java.util.*;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//import java.util.stream.Stream;
//
//public class Test3 {
//    void w() throws InterruptedException {
//Object o = new Object();
//synchronized (Thread.currentThread()) {
//    o.wait();
//    o.notify();
//}
//    }
//    public static void main(String[] args) {
//        Stream.of(1,2,3,4,5,6,7).map((Integer i) -> return "Kitkat" + i;).skip(5).forEach(System.out::println);
//        ExecutorService ex = Executors.newFixedThreadPool(2);
//        Future r = ex.submit(() -> 1);
//        HashMap<String, String> props = new HashMap();
//        props.put("C","nfjkdnfe");
//
//        props.put("A","nfjkdnfe");
//
//
//        Set s = new TreeSet(props.keySet());
//        System.out.println(props);
//
//        Person p1 = new Person("FN","LN");
//        Person p2 = new Person("FN","LN");
//
//        List<Person> list = new ArrayList<>();
//        Set<Person> set =new HashSet<>();
//
//        list.add(p1);
//        list.add(p2);
//
//        set.add(p1);
//        set.add(p2);
//
//        System.out.println(list.size());
//        System.out.println(list.size());
//
//    }
//}
//
//
//class Person {
//    String f;
//    String l;
//
//    public Person(String f, String l) {
//        this.f = f;
//        this.l = l;
//    }
//
//    public String getF() {
//        return f;
//    }
//
//    public void setF(String f) {
//        this.f = f;
//    }
//
//    public String getL() {
//        return l;
//    }
//
//    public void setL(String l) {
//        this.l = l;
//    }
//}
//
//class  P{
//
//}
//
//class R extends P {
//
//}