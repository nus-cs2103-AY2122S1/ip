package main.java.parser;

import main.java.task.Deadline;
import main.java.task.Event;
import main.java.task.Todo;

import java.util.Base64;

public class Parser {

    /**
     * Method to split a string into an array, removing spaces.
     * @param input string to be segmented.
     * @return String array with no spaces.
     */
    public static String[] sanitizeInput(String input) {
        String[] inputArr = input.split(" ");
        return inputArr;
    }


    public static String encodeEvent(Event eventTask) {
        String encodedDescription = Base64.getEncoder().encodeToString(eventTask.getDescription().getBytes());
        String encodedAt = Base64.getEncoder().encodeToString(eventTask.getAt().getBytes());
        String dbEntry = "event " + eventTask.getIsDone() + " " + encodedAt + " " + encodedDescription;
        return dbEntry;
    }

    public static String encodeDeadline(Deadline deadlineTask) {
        String encodedString = Base64.getEncoder().encodeToString(deadlineTask.getDescription().getBytes());
        String dbEntry = "deadline " + deadlineTask.getIsDone() + " " + deadlineTask.getBy() + " " + encodedString;
        return dbEntry;
    }

    public static String encodeTodo(Todo todoTask) {
        String encodedString = Base64.getEncoder().encodeToString(todoTask.getDescription().getBytes());
        String dbEntry = "todo " + todoTask.getIsDone() + " " + encodedString;
        return dbEntry;
    }
}
