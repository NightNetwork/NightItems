package me.ashydev.nightitems.util;

import java.text.DecimalFormat;
import java.util.TreeMap;

public class NumberFormatter {

    public static String format(double num, double upperChange) {
        if (num < upperChange)
            return format(num);
        else
            return formatSuffix(num);
    }
    public static String format(double number) {
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        return formatter.format(number);
    }

    public static TreeMap<Double, String> suffixes = new TreeMap<Double, String>()
    {
        {
            put(1_000.0, "k");
            put(1_000_000.0, "M");
            put(1_000_000_000.0, "B");
            put(1_000_000_000_000.0, "T");
            put(1_000_000_000_000_000.0, "Q");
            put(1_000_000_000_000_000_000.0, "QQ");
            put(1_000_000_000_000_000_000_000.0, "S");
            put(1_000_000_000_000_000_000_000_000.0, "SS");
            put(1_000_000_000_000_000_000_000_000_000.0, "O");
            put(1_000_000_000_000_000_000_000_000_000_000.0, "N");
            put(1_000_000_000_000_000_000_000_000_000_000_000.0, "D");
            put(1_000_000_000_000_000_000_000_000_000_000_000_000.0, "UD");
            put(1_000_000_000_000_000_000_000_000_000_000_000_000_000.0, "DD");
            put(1_000_000_000_000_000_000_000_000_000_000_000_000_000_000.0, "TD");
            put(1_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000.0, "QD");
            put(1_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000.0, "QQD");
            put(1_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000.0, "SD");
        }
    };



    public static String formatSuffix(double value) {
        if (value == Double.MIN_VALUE) return format(Double.MIN_VALUE);
        if (value < 0) return "-" + formatSuffix(-value);
        if (value < 1000) return format(value);
        double e = suffixes.floorKey(value);
        double divideBy = value / e;
        return format(divideBy) + suffixes.get(e);
    }
}
