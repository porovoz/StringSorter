package com.bestapp.com.actions;

import com.bestapp.com.io.Input;

public interface UserAction {
    String name();

    boolean execute(Input input) throws Exception;
}