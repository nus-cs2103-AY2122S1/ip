package back;

import java.util.Arrays;

/**
 * Parent class Task, contains doneness and task name
 */
public class Task {

    protected String taskName;
    protected boolean isDone;

    /**
     * Task constructor.
     *
     * @param taskName Name of task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String toString() {
        return ((isDone ? "[X] " : "[ ] ") + taskName);
    }

    /**
     * Checks if the taskName contains the keyword provided.
     *
     * @param query String to be queried.
     * @return Boolean true if found, false otherwise.
     */
    public boolean queryIfNameContains(String query) {
        //Splits the query into words
        String[] querySplit = query.split(" ");
        int queryLength = querySplit.length; //Length of query

        //Splits the taskName into individual words
        String[] nameSplit = taskName.split(" ");
        int taskNameLength = nameSplit.length; //Length of task name

        if (taskNameLength < queryLength) { //If query length is clearly too long
            return false;
        }
        if (taskNameLength == queryLength) {
            return taskName.equalsIgnoreCase(query);
        } else {
            String[] updatedNameArray = concatNWords(nameSplit, queryLength);
            return Arrays.stream(updatedNameArray).anyMatch(s -> s.equalsIgnoreCase(query));
        }
    }

    /**
     * Concats individual words into strings of n words.
     * @param sourceArray Array containing the original individual words as strings.
     * @param queryLength Desired length of strings.
     * @return Output array of generated strings.
     */
    public String[] concatNWords(String[] sourceArray, int queryLength) {
        int sourceArrayLength = sourceArray.length;
        int resultArrayLength = sourceArrayLength - queryLength + 1;
        String[] resultArray = new String[resultArrayLength];
        for (int i = 0; i < resultArrayLength; i++) {
            resultArray[i] = nextStringToAdd(sourceArray, i, queryLength);
        }
        return resultArray;
    }
    /**
     * Helper function for concatNWords method. Gives the next string to be added.
     * @param sourceArray Array containing the individual words as strings.
     * @param length Desired length of strings.
     * @return Next string of length "length".
     */
    //Each string is a new one with the (i)th to (i+queryLength) word
    public String nextStringToAdd(String[] sourceArray, int startIndex, int length) {
        StringBuilder resultString = new StringBuilder();
        resultString.append(sourceArray[startIndex]);
        for (int j = 1; j < length; j++) {
            resultString.append(" ").append(sourceArray[startIndex + j]);
        }
        return resultString.toString();
    }

    /**
     * Marks this task as done.
     */
    public void markDone() {
        isDone = !isDone;
    }
}
