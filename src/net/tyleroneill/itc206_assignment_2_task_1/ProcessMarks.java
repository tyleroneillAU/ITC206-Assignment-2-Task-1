package net.tyleroneill.itc206_assignment_2_task_1;

import java.util.*;

/**
 * Calculate various attributes from a set of marks
 *
 * @author Tyler O'Neill
 */
public class ProcessMarks {

    public static void main(String[] args) {
        // Calculate and display marks
        System.out.print("Marks:");
        int[] marks = Marks.getMarks();
        for (int i = 0; i < marks.length; i++) {
            if (i % 30 == 0) System.out.print("\n");
            System.out.print(marks[i] + " ");
        }

        // Run methods to calculate attributes and display returns
        int min = min(marks);
        System.out.print("\n\nMinimum: " + min);
        int max = max(marks);
        System.out.print("\nMaximum: " + max);
        System.out.print("\nRange: " + range(min, max));
        System.out.print("\nMean: " + mean(marks));
        System.out.print("\nMedian: " + median(marks));
        System.out.print("\nMode(s): " + mode(marks).toString().replace("[", "").replace("]", ""));
        System.out.print("\n\n");
        System.out.print("Grades:");

        // Run method to convert to grade letters and display them, along with the count of each
        char[] grades = grades(marks);
        for (int j = 0; j < grades.length; j++) {
            if (j % 30 == 0) System.out.print("\n");
            System.out.print(grades[j] + " ");
        }
        System.out.print("\n\nGrade distribution:");
        int[] gradeDistn = gradeDistn(grades);
        System.out.print("\nA: " + gradeDistn[0]);
        System.out.print("\nB: " + gradeDistn[1]);
        System.out.print("\nC: " + gradeDistn[2]);
        System.out.print("\nD: " + gradeDistn[3]);
        System.out.print("\nE: " + gradeDistn[4]);
        System.out.print("\nF: " + gradeDistn[5]);
    }

    // Calculate and return minimum of array
    public static int min(int[] marks) {
        return Arrays.stream(marks).min().getAsInt();
    }

    // Calculate and return maximum of array
    public static int max(int[] marks) {
        return Arrays.stream(marks).max().getAsInt();
    }

    // Calculate and return range of array
    public static int range(int min, int max) {
        return (max - min);
    }

    // Calculate and return mean of array
    public static double mean(int[] marks) {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total / marks.length;
    }

    // Calculate and return medium of array
    public static double median(int[] marks) {
        // Clone and sort the array from min to max
        int[] sortedMarks = marks.clone();
        Arrays.sort(sortedMarks);

        double median;
        // Check if odd or even length and calculate median using the appropriate formula
        if (sortedMarks.length % 2 == 0) {
            median = (sortedMarks[sortedMarks.length / 2] + sortedMarks[(sortedMarks.length / 2) - 1]) / 2;
        } else {
            median = sortedMarks[(sortedMarks.length - 1) / 2];
        }
        return median;
    }

    // Calculate and return mode of array
    public static List<Integer> mode(int[] marks) {
        // Create list for storing the modes, and a hashmap for storing all counts
        Map<Integer, Integer> numbers = new HashMap<>();
        List<Integer> modes = new ArrayList<>();
        int max = -1;

        for (int mark : marks) {
            int number;
            // Check if number is already in hashmap, adding one to the current count if so, otherwise setting it to one
            if (numbers.containsKey(mark)) {
                number = numbers.get(mark) + 1;
            } else {
                number = 1;
            }

            // Update the hashmap with the new count
            numbers.put(mark, number);


            // Update the current max count if it's higher
            if (number > max) {
                max = number;
            }
        }

        // Check hashmap for numbers that match the max count and add them to the mode list before returning it
        for (Map.Entry<Integer, Integer> tuple : numbers.entrySet()) {
            if (tuple.getValue() == max) {
                modes.add(tuple.getKey());
            }
        }
        return modes;
    }

    public static char[] grades(int[] marks) {
        // Create array to store letters and an array to check marks against
        char[] grades = new char[marks.length];
        int[] gradeLimits = {45, 50, 60, 75, 90};

        // Check which grade the marks fall under and set the correct letters in the grades array
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] < gradeLimits[0]) {
                grades[i] = 'F';
            } else if (marks[i] < gradeLimits[1]) {
                grades[i] = 'E';
            } else if (marks[i] < gradeLimits[2]) {
                grades[i] = 'D';
            } else if (marks[i] < gradeLimits[3]) {
                grades[i] = 'C';
            } else if (marks[i] < gradeLimits[4]) {
                grades[i] = 'B';
            } else {
                grades[i] = 'A';
            }
        }
        return grades;
    }

    public static int[] gradeDistn(char[] grades) {
        // Create array for count of each letter starting at zero
        int[] counts = {0, 0, 0, 0, 0, 0};

        // Check each letter and add one to the relevant count for each case
        for (char grade : grades) {
            switch (grade) {
                case 'A':
                    counts[0]++;
                    break;
                case 'B':
                    counts[1]++;
                    break;
                case 'C':
                    counts[2]++;
                    break;
                case 'D':
                    counts[3]++;
                    break;
                case 'E':
                    counts[4]++;
                    break;
                case 'F':
                    counts[5]++;
                    break;
            }
        }
        return counts;
    }
}