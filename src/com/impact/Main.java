package com.impact;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        NumberRangeSummarizerImpl numberRangeSummarizer = new NumberRangeSummarizerImpl();
        //"1,3,6,7,8,12,13,14,15,21,22,23,24,31, p"

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a comma delimited list of number: ");

        Collection<Integer> collectedInput = numberRangeSummarizer.collect(input.next());

        System.out.println(numberRangeSummarizer.summarizeCollection(collectedInput));
    }

}
