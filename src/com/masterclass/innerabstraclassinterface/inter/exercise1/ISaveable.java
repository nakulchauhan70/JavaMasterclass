package com.masterclass.innerabstraclassinterface.inter.exercise1;

import java.util.List;

public interface ISaveable {
    List<String> write();

    void read(List<String> savedValues);

}
