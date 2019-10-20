package com.match.entity;

import lombok.Getter;
import lombok.Setter;

public class Api {
    @Setter @Getter
    private int code;

    @Setter @Getter
    private String msg;

    @Setter @Getter
    private Data data;

    public Api() {
    }

    public Api(int code, String msg, Data data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
