package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer extends SimpleTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        return (int)Arrays.stream(text.split(""))
                .filter(s -> !s.matches("\\.?,?\\s?\"?-?\\n?"))
                .count();
    }

    @Override
    public int countNumberOfWords(String text) {
        return (int)Arrays.stream(splitTextIntoWords(text))
                .count();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {

        return (int)Arrays.stream(splitTextIntoWords(text))
                .distinct()
                .count();
    }

    @Override
    public List<String> getWords(String text) {
        return Arrays.stream(splitTextIntoWords(text))
                .collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {

        return Arrays.stream(splitTextIntoWords(text))
                .collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        String[] arrayOfWords=splitTextIntoWords(text);
        Map<String, Integer> listCountNumberOfWordsRepetitions= new HashMap<>();
        for (int i = 0; i < arrayOfWords.length; i++) {
            String temp=arrayOfWords[i];
            listCountNumberOfWordsRepetitions.put(arrayOfWords[i], (int)Arrays.stream(arrayOfWords).filter(s->s.equals(temp)).count());
        }
        return listCountNumberOfWordsRepetitions;
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {

        return Arrays.stream(splitTextIntoWords(text)).sorted(new Comparator<String>() {
            public int compare(String o1, String o2) {
                if (direction == Direction.ASC) {
                    return (o1.length() - o2.length());
                } else {
                    return (o2.length() - o1.length());
                }

            }

        }).collect(Collectors.toList());
    }

}
