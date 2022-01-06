package com.masterclass.immutable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class User {
    private final String firstName;
    private final String lastName;
    //    private final Address address;
    private final List<Address> addressList;

    public User(String firstName, String lastName, List<Address> addressList) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressList = addressList;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    //Singleton -  work
//    public Address getAddress() throws CloneNotSupportedException {
//        return (Address) address.clone();
//    }

    //Not Singleton - wiil not work - clone of list returns shallow copy of arrayList instance. Solution - use copy constructor = new ArrayList<>(originalList)
//    public ArrayList<Address> getAddress(){
//        return (ArrayList)addressList.clone();
//    }

    //Singleton - use Unmodifiable collections - add(), remove() of Unmodifiable collections throws UnsupportedOperationException;
    public ArrayList<Address> getAddressList() {
        return (ArrayList) Collections.unmodifiableCollection(addressList);
    }

}
