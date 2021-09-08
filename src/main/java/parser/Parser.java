package parser;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;

import model.Tag;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * The Parser middleware provides dependency injection for classes that require common methods.
 * @author  Hoon Darren
 * @version 1.0
 * @since   2021-08-21
 */
public class Parser {

    /**
     * Splits string into an array, removing spaces.
     *
     * @param input string to be segmented.
     * @return String array with no spaces.
     */
    public static String[] sanitizeInput(String input) {
        String trimmedInput = input.trim();
        String[] inputArr = trimmedInput.split(" ");
        return inputArr;
    }

    /**
     * Encodes an event description into base64 for storage.
     *
     * @param eventTask event to be stored in storage.
     * @return encoded description of event.
     */
    public static String encodeEvent(Event eventTask) {

        String encodedDescription = Parser.encodeDescription(eventTask);

        String encodedAt = Base64.getEncoder()
                .encodeToString(eventTask.getAt().getBytes());

        String encodedTags = Parser.encodeTags(eventTask);

        String dbEntry = "event " + eventTask.getIsDone() + " "
                + encodedAt + " " + encodedDescription + " " + encodedTags;

        return dbEntry;
    }

    public static Event decodeEvent(String[] dataArr) {
        Boolean isDone = Boolean.valueOf(dataArr[1]);
        String at = new String(Base64.getDecoder().decode(dataArr[2]));
        String description = Parser.decodeDescription(dataArr[3]);
        String[] tagsArr = Parser.sanitizeInput(new String(Base64.getDecoder().decode(dataArr[4])));

        Event eventTask = new Event(description, at);
        if (isDone) {
            eventTask.markDone();
        }

        if (!tagsArr[0].equals("None")) {
            for (int i = 0; i < tagsArr.length; i++) {
                String tag = tagsArr[i];
                eventTask.addTag(new Tag(tag));
            }
        }
        return eventTask;
    }

    /**
     * Encodes a deadline description into base64 for storage.
     *
     * @param deadlineTask event to be stored in storage.
     * @return encoded description of deadline.
     */
    public static String encodeDeadline(Deadline deadlineTask) {

        String encodedDescription = Parser.encodeDescription(deadlineTask);

        String encodedTags = Parser.encodeTags(deadlineTask);

        String dbEntry = "deadline " + deadlineTask.getIsDone() + " "
                + deadlineTask.getBy() + " " + encodedDescription + " " + encodedTags;

        return dbEntry;
    }

    public static Deadline decodeDeadline(String[] dataArr) {
        Boolean isDone = Boolean.valueOf(dataArr[1]);
        LocalDate by = LocalDate.parse(dataArr[2]);
        String description = Parser.decodeDescription(dataArr[3]);
        String[] tagsArr = Parser.sanitizeInput(new String(Base64.getDecoder().decode(dataArr[4])));

        Deadline deadlineTask = new Deadline(description, by);
        if (isDone) {
            deadlineTask.markDone();
        }

        if (!tagsArr[0].equals("None")) {
            for (int i = 0; i < tagsArr.length; i++) {
                String tag = tagsArr[i];
                deadlineTask.addTag(new Tag(tag));
            }
        }
        return deadlineTask;
    }

    /**
     * Encodes a Todo description into base64 for storage.
     *
     * @param todoTask event to be stored in storage.
     * @return encoded description of Todo.
     */
    public static String encodeTodo(Todo todoTask) {

        String encodedDescription = Parser.encodeDescription(todoTask);

        String encodedTags = Parser.encodeTags(todoTask);

        String dbEntry = "todo " + todoTask.getIsDone()
                + " " + encodedDescription + " " + encodedTags;

        return dbEntry;
    }

    public static Todo decodeTodo(String[] dataArr) {
        Boolean isDone = Boolean.valueOf(dataArr[1]);
        String description = new String(Base64.getDecoder().decode(dataArr[2]));
        Todo todoTask = new Todo(description);
        if (isDone) {
            todoTask.markDone();
        }

        String[] tagsArr = Parser.sanitizeInput(new String(Base64.getDecoder().decode(dataArr[3])));
        if (!tagsArr[0].equals("None")) {
            for (int i = 0; i < tagsArr.length; i++) {
                String tag = tagsArr[i];
                todoTask.addTag(new Tag(tag));
            }
        }
        return todoTask;
    }

    public static String encodeDescription(Task task) {
        return Base64.getEncoder()
                .encodeToString(task.getDescription().getBytes());
    }

    public static String decodeDescription(String dbString) {
        return new String(Base64.getDecoder().decode(dbString));
    }

    public static String encodeTags(Task task) {
        return Base64.getEncoder()
                .encodeToString(task.getTags().getBytes());
    }

    public static void addTag(Task task, String tag) {
        String tagContent = tag.substring(1);
        Tag currentTag = new Tag(tagContent);
        task.addTag(currentTag);
    }

    public static void addTags(Task task, String[] inputArr, int tagStart) {
        if (tagStart != -1) {
            for (int j = tagStart; j < inputArr.length; j++) {
                Parser.addTag(task, inputArr[j]);
            }
        }
    }

    public static int getTagsStart(String[] inputArr) {
        int tagStart = -1;
        for (int i = inputArr.length - 1; i >=0; i--) {
            String currentString = inputArr[i];
            if (currentString.charAt(0) == '#') {
                tagStart = i;
            }
        }
        return tagStart;
    }

    public static String getDescription(String[] inputArr, int commandIndex) {
        String[] descriptionArray = Arrays.copyOfRange(inputArr, 1, commandIndex);
        String description = String.join(" ", descriptionArray);
        return description;
    }

    public static int getCommandIndex(String[] inputArr, String command) {
        int commandIndex = -1;
        for (int i = 0; i < inputArr.length; i++) {
            String currentStr = inputArr[i];
            if (currentStr.equals(command)) {
                commandIndex = i;
                break;
            }
        }
        return commandIndex;
    }
}
