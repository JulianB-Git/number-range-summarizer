package com.impact;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class NumberRangeSummarizerImpl implements NumberRangeSummarizer{
    @Override
    public Collection<Integer> collect(String input) {
        ArrayList<Integer> inputList = new ArrayList<>();

        if(!isInputValid(input)) {
            return null;
        }

        for (String number: input.split(",")){
            try {
                inputList.add(Integer.parseInt(number.trim()));
            } catch (NumberFormatException e){
                consoleLogger("NumberFormatException - Error parsing input to list. Try again..");
                return null;
            }
        }

        //Sorts the list ascending order before returning inputList
        Collections.sort(inputList);

        //Removes duplicate values from the list before returning the list
        return inputList.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        ArrayList<String> ranges = new ArrayList<>();

        Integer[] inputArray = input.toArray(new Integer[0]);

        for (int leftPointer = 0, rightPointer = 0; rightPointer < inputArray.length; rightPointer++){
            //If inputArray[rightPointer] + 1 is not equal to inputArray[rightPointer + 1]
            //then the code will enter the if statement and create a range with the values at the leftPointer and rightPointer index
            if (rightPointer == inputArray.length - 1 || inputArray[rightPointer] + 1 != inputArray[rightPointer + 1]) {

                if (leftPointer == rightPointer) {
                    ranges.add(String.valueOf(inputArray[rightPointer]));
                }
                else {
                    ranges.add(inputArray[leftPointer] + "-" + inputArray[rightPointer]);
                }

                leftPointer = rightPointer + 1;
            }
        }

        //Removes the square brackets of the array and delimits the output with commas
        return String.join(", ", ranges);
    }

    public boolean isInputValid(String input){

        //Checks if the input is empty
        if (input.isEmpty()) {
            consoleLogger("Input cannot be empty");
            return false;
        }

        //Creating a regular expression to check if the input contains only digits and commas
        //does not allow the first character to be a comma
        Pattern regexPattern = Pattern.compile("^[^,][0-9, ]+");
        Matcher matcher = regexPattern.matcher(input.trim());

        if (!matcher.matches()){
            consoleLogger("Input should only be numbers delimited by a comma");
            return false;
        }

        return true;
    }

    private void consoleLogger(String message){
        System.out.println("ERROR: "+message);
    }
}
