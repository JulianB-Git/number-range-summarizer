package com.impact;


import java.util.Collection;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        NumberRangeSummarizerImpl numberRangeSummarizer = new NumberRangeSummarizerImpl();
        Scanner input = new Scanner(System.in);
        Collection<Integer> collectedInput = null;

        while (collectedInput == null) {
            System.out.println("\nEnter a comma delimited list of numbers (e.g) 1,3,4,5,8: ");
            collectedInput = numberRangeSummarizer.collect(input.nextLine());
        }

        System.out.println("Range Summarized: "+numberRangeSummarizer.summarizeCollection(collectedInput));
    }

}
