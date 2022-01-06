package com.masterclass.markerinterface;

interface Cash {
}

interface Cheque {
}

class PaymentOne implements Cash {
    public static void paymentByCash() {
        System.out.println("Payment is done by cash");
    }
}

class PaymentTwo implements Cheque {
    static void paymentByCheque() {
        System.out.println("Payment is done by cheque");
    }
}

public class MarkerInterfaceDemo {
    public static void main(String[] args) {
        PaymentOne.paymentByCash();
        PaymentTwo.paymentByCheque();
    }
}