package com.masterclass.designpattern.builderdesign;

interface A {
    static void s() {
        System.out.println("static");
    }

    default void a() {
        System.out.println("default");
    }
}

interface ICorporateAction {
    CorporateActionOutput apply(CorporateActionInput input);
}

public class ApplyCorporateAction {
    public static void main(String[] args) {
        A.s();
        C c = new C();
        c.a();
//        CorporateActionFactory factory = new CorporateActionFactory();
//        CorporateAction<? extends ICorporateAction> sd = factory.createCorporateAction("SPECIAL_DIVIDEND");
//        System.out.println(sd.toString());
//        CorporateAction<? extends ICorporateAction> cd = factory.createCorporateAction("CASH_DIVIDEND");
//        System.out.println(cd.toString());
    }
}

class C implements A {

}

class CorporateActionOutput {
}

class CorporateActionInput {
}

class SpecialDividend implements ICorporateAction {
    @Override
    public CorporateActionOutput apply(CorporateActionInput input) {
        return null;
    }

    @Override
    public String toString() {
        return "SpecialDividend{}";
    }

}

class CashDividend implements ICorporateAction {
    @Override
    public CorporateActionOutput apply(CorporateActionInput input) {
        return null;
    }

    @Override
    public String toString() {
        return "CashDividend{}";
    }
}

class CorporateAction<T> implements ICorporateAction {
    T instance;

    public CorporateAction() {
    }

    @Override
    public String toString() {
        return "CorporateAction{" +
                "instance=" + instance +
                '}';
    }

    @Override
    public CorporateActionOutput apply(CorporateActionInput input) {
        return null;
    }
}

class CorporateActionFactory {
    public CorporateAction<? extends ICorporateAction> createCorporateAction(String ca) {
        if (ca == null || ca.isEmpty()) {
            return null;
        }

        if ("SPECIAL_DIVIDEND".equalsIgnoreCase(ca)) {
            return new CorporateAction<SpecialDividend>();
        }

        if ("CASH_DIVIDEND".equalsIgnoreCase(ca)) {
            return new CorporateAction<CashDividend>();
        }

        return null;
    }
}
//class CorporateAction {
//    public ICorporateAction getCorporateAction(String ca) {
//        ICorporateAction corporateAction;
//        if (ca == null || ca.isEmpty()) {
//            return null;
//        }
//
//        if ("SPECIAL_DIVIDEND".equalsIgnoreCase(ca)) {
//            corporateAction =//        }
//    }
//}

//Config.java

//class SpecialDividend implements ICorporateAction {
//
//}


