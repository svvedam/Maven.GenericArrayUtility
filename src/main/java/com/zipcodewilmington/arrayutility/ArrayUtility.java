package com.zipcodewilmington.arrayutility;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility<E extends Object> {
    E[] inputArray;

    ArrayUtility(E[] inputArray) {
        this.inputArray = inputArray;
    }

    public <E> Integer countDuplicatesInMerge(E[] arrayToMerge, E valueToEvaluate) {
        Stream<E> stream1 = (Stream<E>) Stream.of(inputArray);
        Stream<E> stream2 = (Stream<E>) Stream.of(arrayToMerge);

        Stream<E> mergedStream = (Stream<E>) Stream.concat(stream1, stream2);

        Integer size = mergedStream.filter(value -> value == valueToEvaluate)
                .collect(Collectors.toList()).size();
        return size;

    }

    public <E> Integer getNumberOfOccurrences(E valueToEvaluate) {
        Stream<E> stream1 = (Stream<E>) Stream.of(inputArray);

        Integer size = stream1.filter(value -> value == valueToEvaluate)
                .collect(Collectors.toList()).size();
        return size;

    }

    public <E> E getMostCommonFromMerge(E[] arrayToMerge) {
        List first = Arrays.asList(inputArray);
        List second = Arrays.asList(arrayToMerge);
        List merger = new ArrayList<E>();

        merger.addAll(first);
        merger.addAll(second);

        // List<Integer> theList = Arrays.asList(1, 3, 4, 3, 4, 3, 2, 3, 3, 3, 3, 3);
        Object maxOccurredElement = merger.stream()
                .reduce(BinaryOperator.maxBy((o1, o2) -> Collections.frequency(first, o1) -
                        Collections.frequency(first, o2))).orElse(null);
        return (E) maxOccurredElement;
    }

    public E[] removeValue(E valueToRemove) {
        List<E> first = Arrays.asList(inputArray);
        List<E> merger = new ArrayList<>();

        merger.addAll(first);

        merger.removeIf(p -> p.equals(valueToRemove));

        return merger.toArray((E[])Array
                .newInstance(merger.get(0)
                        .getClass(), merger.size()));
    }
}
