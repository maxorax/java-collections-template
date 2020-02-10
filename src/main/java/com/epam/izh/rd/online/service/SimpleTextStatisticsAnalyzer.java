package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import static java.util.Collections.*;

/**
 * Совет:
 * Начните с реализации метода {@link SimpleTextStatisticsAnalyzer#getWords(String)}.
 * Затем переиспользуйте данный метод при реализации других.
 * <p>
 * При необходимости, можно создать внутри данного класса дополнительные вспомогательные приватные методы.
 */
public class SimpleTextStatisticsAnalyzer implements TextStatisticsAnalyzer {

    /**
     * Необходимо реализовать функционал подсчета суммарной длины всех слов (пробелы, знаким препинания итд не считаются).
     * Например для текста "One, I - tWo!!" - данный метод должен вернуть 7.
     *
     * @param text текст
     */
    @Override
    public int countSumLengthOfWords(String text) {
        ArrayList<String> listOfWords= new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {
            if(!String.valueOf(text.charAt(i)).matches("\\.?,?\\s?\"?-?\\n?")) {
                listOfWords.add(String.valueOf(text.charAt(i)));
            }
        }

        return  listOfWords.size();
    }

    /**
     * Необходимо реализовать функционал подсчета количества слов в тексте.
     * Например для текста "One, two, three, three - one, tWo, tWo!!" - данный метод должен вернуть 7.
     *
     * @param text текст
     */
    @Override
    public int countNumberOfWords(String text) {
        String[] arrayOfWords=splitTextIntoWords(text);
        return arrayOfWords.length;
    }

    /**
     * Необходимо реализовать функционал подсчета количества уникальных слов в тексте (с учетом регистра).
     * Например для текста "One, two, three, three - one, tWo, tWo!!" - данный метод должен вернуть 5.
     * param text текст
     */
    @Override
    public int countNumberOfUniqueWords(String text) {
        HashSet list= new HashSet();
        String[] arrayOfWords=splitTextIntoWords(text);
        for (int i = 0; i <arrayOfWords.length; i++) {
            list.add(arrayOfWords[i]);
        }
        return list.size();
    }

    /**
     * Необходимо реализовать функционал получения списка слов из текста.
     * Пробелы, запятые, точки, кавычки и другие знаки препинания являются разделителями слов.
     * Например для текста "One, two, three, three - one, tWo, tWo!!" должен вернуться список :
     * {"One", "two", "three", "three", "one", "tWo", "tWo"}
     *
     * @param text текст
     */
    @Override
    public List<String> getWords(String text) {
        ArrayList<String> listOfWords= new ArrayList<>();
        String[] arrayOfWords=splitTextIntoWords(text);
        for (int i = 0; i < arrayOfWords.length; i++) {
                listOfWords.add(arrayOfWords[i]);
        }
        return listOfWords;
    }

    /**
     * Необходимо реализовать функционал получения списка уникальных слов из текста.
     * Пробелы, запятые, точки, кавычки и другие знаки препинания являются разделителями слов.
     * Например для текста "One, two, three, three - one, tWo, tWo!!" должен вернуться список :
     * {"One", "two", "three", "one", "tWo"}
     *
     * @param text текст
     */
    @Override
    public Set<String> getUniqueWords(String text) {
        HashSet<String> listOfUniqueWords=new HashSet<>();
        String[] arrayOfWords=splitTextIntoWords(text);
        for (int i = 0; i < arrayOfWords.length; i++) {
            listOfUniqueWords.add(arrayOfWords[i]);
        }
        return listOfUniqueWords;
    }

    /**
     * Необходимо реализовать функционал подсчета количества повторений слов.
     * Например для текста "One, two, three, three - one, tWo, tWo!!" должны вернуться результаты :
     * {"One" : 1, "two" : 1, "three" : 2, "one" : 1, "tWo" : 2}
     *
     * @param text текст
     */
    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        Map<String, Integer> listCountNumberOfWordsRepetitions= new HashMap<>();
        String[] arrayOfWords=splitTextIntoWords(text);
        for (int i = 0; i < arrayOfWords.length; i++) {
            if(listCountNumberOfWordsRepetitions.containsKey(arrayOfWords[i])){
                listCountNumberOfWordsRepetitions.replace(arrayOfWords[i],
                        listCountNumberOfWordsRepetitions.get(arrayOfWords[i]),
                        listCountNumberOfWordsRepetitions.get(arrayOfWords[i])+1);
            }else{
                listCountNumberOfWordsRepetitions.put(arrayOfWords[i],1);
            }
        }
        return listCountNumberOfWordsRepetitions;
    }

    /**
     * Необходимо реализовать функционал вывода слов из текста в отсортированном виде (по длине) в зависимости от параметра direction.
     * Например для текста "Hello, Hi, mother, father - good, cat, c!!" должны вернуться результаты :
     * ASC : {"mother", "father", "Hello", "good", "cat", "Hi", "c"}
     * DESC : {"c", "Hi", "cat", "good", "Hello", "father", "mother"}
     *
     * @param text текст
     */
    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        ArrayList<String> listOfWords= new ArrayList<>();
        String[] arrayOfWords=splitTextIntoWords(text);
        for (int i = 0; i < arrayOfWords.length; i++) {
            listOfWords.add(arrayOfWords[i]);
        }
        sort(listOfWords, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1.length() - o2.length());
            }
        });
        if(direction==Direction.ASC) {
            return listOfWords;
        }
        else{

            Collections.reverse(listOfWords);
            return listOfWords;
        }

    }

    public String[] splitTextIntoWords(String text){
        text=text.replaceAll(" -","");
        text=text.replaceAll("\\.","");
        text=text.replaceAll(",","");
        text=text.replaceAll("\"","");
        text=text.replaceAll("\n"," ");
        return text.split(" ");
    }
}
