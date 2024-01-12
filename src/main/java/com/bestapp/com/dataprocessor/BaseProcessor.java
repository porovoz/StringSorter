package com.bestapp.com.dataprocessor;

import java.util.*;

public final class BaseProcessor implements Processor {

    public List<String> appendFreqInfoToStrings(List<String> list) {
        Map<String, Integer> freqMap = new HashMap<>(list.size() * 100 / 75);
        for (String string : list) {
            freqMap.merge(string, 1, Integer::sum);
        }
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) + " " + freqMap.get(list.get(i)));
        }
        return list;
    }

    public List<String> sortData(List<String> list, SortingTypes sortingTypes, int wordNumToSort) {
        return switch (sortingTypes) {
            case ALPHABET -> sortAlphabet(list);
            case SENTENCE_LENGTH -> sortByLength(list);
            case WORD_NUMBER -> sortByWord(list, wordNumToSort);
        };
    }

    private List<String> sortAlphabet(List<String> list) {
        Collections.sort(list);
        return list;
    }

    private List<String> sortByLength(List<String> list) {
        list.sort(Comparator.comparingInt(String::length));
        return list;
    }

    private List<String> sortByWord(List<String> list, int wordNum) {

        if (wordNum < 1) {
            throw new IllegalArgumentException("Number of word to sort must be more than 1");
        }

        int num = wordNum - 1;
        list.sort((String o1, String o2) -> {
            String[] array1 = o1.trim().split("\\s+");
            String[] array2 = o2.trim().split("\\s+");
            int result = 0;
            if (array1.length >= num && array2.length >= num) {
                result = array1[num].compareTo(array2[num]);
            } else if (array1.length >= num) {
                result = -1;
            } else if (array2.length < num) {
                result = 1;
            }
            return result;
        });
        return list;
    }
}