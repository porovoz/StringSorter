package com.bestapp.com.dataprocessor;

public enum SortingTypes {

    ALPHABET("Сортировка по алфавиту"),
    SENTENCE_LENGTH("Сортировка по количеству символов в строке"),
    WORD_NUMBER("Сортировка по слову в строке заданному "
            + "аргументом программы в виде порядкового номера");

    private String title;

    SortingTypes(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}