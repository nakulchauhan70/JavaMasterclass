package com.masterclass.immutable;

import java.util.ArrayList;
import java.util.List;

public final class Employee {
    private final int empId;
    private final String name;
    private final List<Address> addressList;

    public Employee(int empId, String name, List<Address> addressList) {
        List<Address> tmpList = new ArrayList<>();
        for (Address address : addressList) {
            tmpList.add(address);
        }

        this.empId = empId;
        this.name = name;
        this.addressList = tmpList;
    }

    public int getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public List<Address> getAddressList() {
//        return Collections.unmodifiableList(this.addressList);

        List<Address> tmpList = new ArrayList<>();
        for (Address address : this.addressList) {
            tmpList.add(address);
        }

        return tmpList;
    }
}

class DemoEmployee {
    public static void main(String[] args) {
        List<Address> addressList = new ArrayList<>();
        Address address1 = new Address("XXXX", "YYYY", "ZZZZ");
        Address address2 = new Address("AAAA", "BBBB", "CCCC");
        addressList.add(address1);
        addressList.add(address2);

        Employee employee = new Employee(1, "nakul", addressList);

        System.out.println(employee.getEmpId());
        System.out.println(employee.getName());
        System.out.println(employee.getAddressList());

        addressList.add(new Address("JJJJ", "KKKK", "LLLL"));
        System.out.println(employee.getAddressList());

        employee.getAddressList().add(new Address("PPPP", "QQQQ", "RRRR"));
        System.out.println(employee.getAddressList());

    }
}
