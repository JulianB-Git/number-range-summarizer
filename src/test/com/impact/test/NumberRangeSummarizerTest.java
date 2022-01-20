package com.impact.test;

import com.impact.NumberRangeSummarizerImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

class NumberRangeSummarizerTest {

    NumberRangeSummarizerImpl numberRangeSummarizer;

    @BeforeEach
    void setUp() {
        numberRangeSummarizer = new NumberRangeSummarizerImpl();
    }

    @AfterEach
    void tearDown() {
        numberRangeSummarizer = null;
    }

    //Assumed that once the input is collected, duplicate numbers should be removed e.g 1,1,2,2,3,3
    @Test
    public void testDuplicateNumbersInInput(){
        String input = "1,1,3,3,6,7,8,12,12,13,14,15,21,22,23,24,31,31";
        List<Integer> expectedList = Arrays.asList(1,3,6,7,8,12,13,14,15,21,22,23,24,31);
        Collection<Integer> actualList = numberRangeSummarizer.collect(input);
        assertEquals(expectedList, actualList);
    }

    //Assumed that once the input is collected, the list should be sorted in ascending order before being returned
    //e.g input: 2,3,1,5 List returned will be 1,2,3,5
    @Test
    public void testUnsortedNumbersInInput(){
        String input = "2,3,1,5";
        List<Integer> expectedList = Arrays.asList(1,2,3,5);
        Collection<Integer> actualList = numberRangeSummarizer.collect(input);
        assertEquals(expectedList, actualList);
    }

    @Test
    public void testCollectInputHappyPath() {
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        List<Integer> expectedList = Arrays.asList(1,3,6,7,8,12,13,14,15,21,22,23,24,31);

        Collection<Integer> actualList = numberRangeSummarizer.collect(input);

        assertEquals(expectedList, actualList);
    }

    @Test
    public void testSummarizeInputHappyPath() {
        String expectedOutput = "1, 3, 6-8, 12-15, 21-24, 31";
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";

        Collection<Integer> collectedInput = numberRangeSummarizer.collect(input);

        String actualOutput = numberRangeSummarizer.summarizeCollection(collectedInput);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testSummarizeCompleteFlowHappyPath() {
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        String expectedOutput = "1, 3, 6-8, 12-15, 21-24, 31";

        List<Integer> expectedList = Arrays.asList(1,3,6,7,8,12,13,14,15,21,22,23,24,31);
        Collection<Integer> collectedInput = numberRangeSummarizer.collect(input);
        assertEquals(expectedList, collectedInput);

        String actualOutput = numberRangeSummarizer.summarizeCollection(collectedInput);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testEmptyInputString() {
        String input = "";

        Collection<Integer> actualList = numberRangeSummarizer.collect(input);
        assertNull(actualList);
    }

    @Test
    public void testInputStringIsNotValid() {
        String input = "String not numbers @#$";

        Collection<Integer> actualList = numberRangeSummarizer.collect(input);
        assertNull(actualList);
    }

    @Test
    public void testNumberFormatException() {
        String input = "1,,3,4";

        Collection<Integer> actualList = numberRangeSummarizer.collect(input);
        assertNull(actualList);
    }
}