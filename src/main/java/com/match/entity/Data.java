package com.match.entity;

import lombok.Getter;
import lombok.Setter;

public class Data<T> {

    @Setter @Getter
    T Rs;

    @Setter @Getter
    T note;

    public Data() {
    }

    public Data(T Rs, T note) {
        this.Rs = Rs;
        this.note = note;
    }
}
