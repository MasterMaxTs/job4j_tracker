package ru.job4j.tracker.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatrixToList {
    public List<Integer> listFromMatrix(Integer[][] matrix) {
        return Stream.of(matrix).flatMap(Arrays::stream)
                                .collect(Collectors.toList());
    }
}

