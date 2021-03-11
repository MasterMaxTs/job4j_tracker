package ru.job4j.tracker.collection;

import java.util.HashSet;

public class UniqueText {
    public static boolean isEquals(String originText, String duplicateText) {
        boolean rsl = true;
        String[] origin = originText.split(" ");
        String[] text = duplicateText.split(" ");
        HashSet<String> check = new HashSet<>();
        /*HashSet<String> check = new HashSet<>(Arrays.asList(origin));*/
        for (String value
                : origin) {
            check.add(value);
        }
        for (String value
                : text) {
            if (!check.contains(value)) {
                return false;
            }
        }
        return rsl;
    }
}
