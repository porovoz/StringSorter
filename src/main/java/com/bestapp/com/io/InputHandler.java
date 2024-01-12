package com.bestapp.com.io;

import java.util.List;

public class InputHandler {

    private InputHandler() {
    }

    public static int handleSelectItems(Input input,
                                        Output out,
                                        String caption,
                                        List<String> selectItems,
                                        String question) {
        int select;
        while (true) {

            if (!caption.isEmpty()) {
                out.println(caption);
            }

            for (int i = 0; i < selectItems.size(); i++) {
                out.println(String.format("%d %s", i + 1, selectItems.get(i)));
            }
            select = input.askInt(question);
            if (select >= 1 && select <= selectItems.size()) {
                break;
            }
            out.println(String.format("Wrong input, you can select: 1...%d", selectItems.size()));
        }
        return select;
    }

    public static int handleSingleIntInput(Input input,
                                           Output out,
                                           String question,
                                           int minValue,
                                           int maxValue) {
        int select;

        while (true) {
            select = input.askInt(question);
            if (select >= minValue && select <= maxValue) {
                break;
            }
            out.println(String.format("Wrong input, you can select: %d...%d", minValue, maxValue));
        }
        return select;
    }
}