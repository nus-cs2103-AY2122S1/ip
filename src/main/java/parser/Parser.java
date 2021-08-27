package main.java.parser;

import main.java.task.Deadline;
import main.java.task.Event;
import main.java.task.Todo;

import java.util.Base64;

/**
 * The Parser middleware provides dependency injection for classes that require common methods.
 * @author  Hoon Darren
 * @version 1.0
 * @since   2021-08-21
 */
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

    /**
     * Encodes an event description into base64 for storage.
     * @param eventTask event to be stored in storage.
     * @return encoded description of event.
     */
    public static String encodeEvent(Event eventTask) {
        String encodedDescription = Base64.getEncoder().encodeToString(eventTask.getDescription().getBytes());
        String encodedAt = Base64.getEncoder().encodeToString(eventTask.getAt().getBytes());
        String dbEntry = "event " + eventTask.getIsDone() + " " + encodedAt + " " + encodedDescription;
        return dbEntry;
    }

    /**
     * Encodes a deadline description into base64 for storage.
     * @param deadlineTask event to be stored in storage.
     * @return encoded description of deadline.
     */
    public static String encodeDeadline(Deadline deadlineTask) {
        String encodedString = Base64.getEncoder().encodeToString(deadlineTask.getDescription().getBytes());
        String dbEntry = "deadline " + deadlineTask.getIsDone() + " " + deadlineTask.getBy() + " " + encodedString;
        return dbEntry;
    }

    /**
     * Encodes a Todo description into base64 for storage.
     * @param todoTask event to be stored in storage.
     * @return encoded description of Todo.
     */
    public static String encodeTodo(Todo todoTask) {
        String encodedString = Base64.getEncoder().encodeToString(todoTask.getDescription().getBytes());
        String dbEntry = "todo " + todoTask.getIsDone() + " " + encodedString;
        return dbEntry;
    }
}
