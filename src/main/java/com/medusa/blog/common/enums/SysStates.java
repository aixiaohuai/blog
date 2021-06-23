package com.medusa.blog.common.enums;


public enum SysStates {

    ENABLE(0, "启用状态"),
    DISABLE(1, "禁用状态");

    private final int state;

    private final String msg;

    private SysStates(int state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public int value() {
        return this.state;
    }

    public String getMsg() {
        return this.msg;
    }
}
