package com.impact;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberRangeSummarizerImpl implements NumberRangeSummarizer{
    @Override
    public Collection<Integer> collect(String input) {
        ArrayList<Integer> inputList = new ArrayList<>();

        for (String number: input.split(",")){
            try {
                inputList.add(Integer.parseInt(number.trim()));
            } catch (NumberFormatException e){
                System.out.println(e.getLocalizedMessage());
                return null;
            }
        }

        return inputList;
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        var ranges = new ArrayList<String>();

        var inputArray = input.toArray(new Integer[0]);

        for (int left = 0, right = 0; right < inputArray.length; right++){
            if (right == inputArray.length - 1 || inputArray[right] + 1 != inputArray[right + 1]) {

                if (left == right)
                    ranges.add(String.valueOf(inputArray[right]));
                else
                    ranges.add(inputArray[left] + "-" + inputArray[right]);

                left = right + 1;
            }
        }

        return String.join(", ", ranges);
    }

    public boolean isInputValid(String input){
        if (input.trim().isEmpty()) {
            consoleLogger("ERROR: Input cannot be empty");
            return false;
        }

        Pattern regexPattern = Pattern.compile("[0-9,]+");
        Matcher matcher = regexPattern.matcher(input.trim());

        if (!matcher.matches()){
            consoleLogger("Input should only be numbers");
            return false;
        }

        return true;
    }

    private void consoleLogger(String message){
        System.out.println("ERROR: "+message);
    }
}
