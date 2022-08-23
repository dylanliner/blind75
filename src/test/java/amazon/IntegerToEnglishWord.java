package amazon;

import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

public class IntegerToEnglishWord {
    public String numberToWords(int num) {

        if (num == 0) {
            return "Zero";
        }

        var numberWords = new HashMap<String, String>();
        numberWords.put("0", "");
        numberWords.put("1", "One");
        numberWords.put("2", "Two");
        numberWords.put("3", "Three");
        numberWords.put("4", "Four");
        numberWords.put("5", "Five");
        numberWords.put("6", "Six");
        numberWords.put("7", "Seven");
        numberWords.put("8", "Eight");
        numberWords.put("9", "Nine");
        numberWords.put("10", "Ten");
        numberWords.put("11", "Eleven");
        numberWords.put("12", "Twelve");
        numberWords.put("13", "Thirteen");
        numberWords.put("14", "Fourteen");
        numberWords.put("15", "Fifteen");
        numberWords.put("16", "Sixteen");
        numberWords.put("17", "Seventeen");
        numberWords.put("18", "Eighteen");
        numberWords.put("19", "Nineteen");

        var tensMultiples = new HashMap<Character, String>();
        tensMultiples.put('0', "");
        tensMultiples.put('2', "Twenty");
        tensMultiples.put('3', "Thirty");
        tensMultiples.put('4', "Forty");
        tensMultiples.put('5', "Fifty");
        tensMultiples.put('6', "Sixty");
        tensMultiples.put('7', "Seventy");
        tensMultiples.put('8', "Eighty");
        tensMultiples.put('9', "Ninety");

        var numArray = String.valueOf(num).chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        //segement into group of three
        var segmentsOfThree = new Stack<List<Character>>();
        int i = numArray.size();
        while (i > 0) {
            var startIndex = i >= 3 ? i - 3 : 0;
            segmentsOfThree.add(new ArrayList<>(numArray.subList(startIndex, i)));
            i = i - 3;

        }
        //start with first segment
        var sb = new StringBuilder();

        var decimalWord = "";
        var isFirst = true;

        while (!segmentsOfThree.isEmpty()) {
            if (segmentsOfThree.size() == 4) {
                decimalWord = "Billion";
            } else if (segmentsOfThree.size() == 3) {
                decimalWord = "Million";
            } else if (segmentsOfThree.size() == 2) {
                decimalWord = "Thousand";
            }

            var cur = segmentsOfThree.pop();
            var addDecimal = true;

            if (cur.size() == 3) {

                //1 number of segment always hundred
                if (cur.get(0) != '0' || isFirst) {
                    sb.append(" ").append(numberWords.get(cur.get(0).toString()));
                    sb.append(" ").append("Hundred");
                }

                //2nd twenty, thirty... except below 20
                //3rd always one, two, three... except below 20;
                if (cur.get(1) >= '2') {
                    sb.append(" ").append(tensMultiples.get(cur.get(1)));
                    sb.append(" ").append(numberWords.get(cur.get(2).toString()));
                } else if (cur.get(1) != '0') {
                    var str = numberWords.get(cur.get(1).toString() + cur.get(2).toString());
                    sb.append(" ").append(str);
                } else if (cur.get(2) != '0') {
                    var str = numberWords.get(cur.get(2).toString());
                    sb.append(" ").append(str);
                }

                if (cur.stream().allMatch(s -> s == '0')) {
                    addDecimal = false;
                }
            }

            if (cur.size() == 2) {
                //2nd twenty, thirty... except below 20
                //3rd always one, two, three... except below 20;
                if (cur.get(0) >= '2') {
                    sb.append(" ").append(tensMultiples.get(cur.get(0)));
                    sb.append(" ").append(numberWords.get(cur.get(1).toString()));
                } else if (cur.get(0) != '0') {
                    var str = numberWords.get(cur.get(0).toString() + cur.get(1).toString());
                    sb.append(" ").append(str);
                } else if (cur.get(1) != '0') {
                    var str = numberWords.get(cur.get(1).toString());
                    sb.append(" ").append(str);
                }
            }

            if (cur.size() == 1) {
                sb.append(" ").append(numberWords.get(cur.get(0).toString()));
            }

            //add thousand, million, billion after all groups of three.
            if (isFirst || (segmentsOfThree.size() != 0 && addDecimal)) {
                sb.append(" ").append(decimalWord);
            }
            isFirst = false;
        }
        return sb.toString().trim().replaceAll(" +", " ");

    }

    @Test
    public void test() {
        System.out.println(numberToWords(3200348));
        System.out.println(numberToWords(2001123123));
        System.out.println(numberToWords(2000000000));
        System.out.println(numberToWords(200000000));
        System.out.println(numberToWords(20000000));
        System.out.println(numberToWords(2000000));
        System.out.println(numberToWords(200000));
        System.out.println(numberToWords(20000));
        System.out.println(numberToWords(2000));
        System.out.println(numberToWords(200));
        System.out.println(numberToWords(20));
        System.out.println(numberToWords(2));
        System.out.println(numberToWords(22));
        System.out.println(numberToWords(202));
        System.out.println(numberToWords(2002));
        System.out.println(numberToWords(202202));
        System.out.println(numberToWords(880088));
        System.out.println(numberToWords(1));

    }
}
