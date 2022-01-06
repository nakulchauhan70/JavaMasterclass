package com.masterclass.immutable;

import java.util.HashMap;
import java.util.Map;

final public class Student {
    private final String name;
    private final int regNo;
    private final Map<String, String> metadata;

    public Student(String name, int regNo, Map<String, String> metadata) {

        Map<String, String> tempMap = new HashMap<>();

        for (Map.Entry<String, String> stringStringEntry : metadata.entrySet()) {
            tempMap.put(stringStringEntry.getKey(), stringStringEntry.getValue());
        }

        this.name = name;
        this.regNo = regNo;
        this.metadata = tempMap;
    }

    public String getName() {
        return name;
    }

    public int getRegNo() {
        return regNo;
    }

    public Map<String, String> getMetadata() {
        Map<String, String> tempMap = new HashMap<>();

        for (Map.Entry<String, String> stringStringEntry : this.metadata.entrySet()) {
            tempMap.put(stringStringEntry.getKey(), stringStringEntry.getValue());
        }

        return tempMap;
    }
}

class DemoStudent {
    public static void main(String[] args) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("XXXX", "YYYY");

        Student student = new Student("Nakul", 1, metadata);

        System.out.println(student.getName());
        System.out.println(student.getRegNo());
        System.out.println(student.getMetadata());

        metadata.put("AAAA", "BBBB");
        System.out.println(student.getMetadata());

        student.getMetadata().put("CCCC", "DDDD");
        System.out.println(student.getMetadata());
    }
}