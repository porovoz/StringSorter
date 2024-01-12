package com.bestapp.com.actions;

import com.bestapp.com.io.Input;
import com.bestapp.com.io.Output;

public final class GetHelpAction implements UserAction {

    private final Output out;

    public GetHelpAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Get help";
    }

    @Override
    public boolean execute(Input input) {
        out.println("Brief instruction...");
        return true;
    }
}