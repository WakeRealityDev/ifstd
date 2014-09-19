package com.yrek.ifstd.t3;

import java.io.Serializable;
import java.util.LinkedList;

class Machine implements Serializable {
    private static final long serialVersionUID = 0L;

    LinkedList<T3Value> stack = new LinkedList<T3Value>();
    T3Value r0 = T3Value.NIL;
}