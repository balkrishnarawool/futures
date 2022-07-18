package com.balarawool;

public interface PentaFunction<A, B, C, D, E, T> {
    T apply(A a, B b, C c, D d, E e);
}