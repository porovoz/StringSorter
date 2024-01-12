package com.bestapp.com;

import com.bestapp.com.actions.GetHelpAction;
import com.bestapp.com.actions.ProcessFileAction;
import com.bestapp.com.dataprocessor.BaseProcessor;
import com.bestapp.com.dataprocessor.Processor;
import com.bestapp.com.dataprovider.DataProvider;
import com.bestapp.com.dataprovider.FileDataProvider;
import com.bestapp.com.io.*;
import com.bestapp.com.actions.ExitConsoleAction;
import com.bestapp.com.actions.UserAction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private final Output output;

    public Main(Output output) {
        this.output = output;
    }

    public void init(Input input, List<UserAction> actions) throws Exception {

        boolean run = true;
        List<String> actionsStrings = actions.stream().
                map(UserAction::name).
                collect(Collectors.toList());

        while (run) {
            UserAction action = actions.get(InputHandler.handleSelectItems(
                    input,
                    output,
                    System.lineSeparator() + "<<< Menu >>> ",
                    actionsStrings,
                    "Select action: ") - 1);
            run = action.execute(input);
        }
    }

    public static void main(String[] args) throws Exception {
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput());
        DataProvider dataProvider = new FileDataProvider();
        Processor fileProcessor = new BaseProcessor();
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new ProcessFileAction(output, dataProvider, fileProcessor));
        actions.add(new GetHelpAction(output));
        actions.add(new ExitConsoleAction(output));
        new Main(output).init(input, actions);
    }
}