package parser;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

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

    public static final int DESCRIPTION_START = 1;
    public static final int INVALID = -1;
    public static final int IS_DONE_DATABASE_INDEX = 1;
    public static final int AT_BY_DATABASE_INDEX = 2;
    public static final int DESCRIPTION_EVENT_DEADLINE_DATABASE_INDEX = 3;
    public static final int DESCRIPTION_TODO_DATABASE_INDEX = 2;
    public static final int TAGS_EVENT_DEADLINE_DATABASE_INDEX = 4;
    public static final int TAGS_TODO_DATABASE_INDEX = 3;

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

    /**
     * Decodes an event object stored as base64 from storage.
     *
     * @param dataArr string array retrieved from storage.
     * @return Event object that the stored data represents.
     */
    public static Event decodeEvent(String[] dataArr) {
        Boolean isDone = Boolean.valueOf(dataArr[IS_DONE_DATABASE_INDEX]);
        String at = new String(Base64.getDecoder().decode(dataArr[AT_BY_DATABASE_INDEX]));
        String description = Parser.decodeDescription(dataArr[DESCRIPTION_EVENT_DEADLINE_DATABASE_INDEX]);
        String[] tagsArr = Parser
                .sanitizeInput(new String(Base64.getDecoder().decode(dataArr[TAGS_EVENT_DEADLINE_DATABASE_INDEX])));

        Event eventTask = new Event(description, at);
        if (isDone) {
            eventTask.markDone();
        }

        if (!tagsArr[0].equals("None")) {
            List<Tag> tagsList = Parser.decodeTags(tagsArr);
            Parser.addTags(tagsList, eventTask);
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

    /**
     * Decodes a deadline object stored as base64 from storage.
     *
     * @param dataArr string array retrieved from storage.
     * @return Deadline object that the stored data represents.
     */
    public static Deadline decodeDeadline(String[] dataArr) {
        Boolean isDone = Boolean.valueOf(dataArr[IS_DONE_DATABASE_INDEX]);
        LocalDate by = LocalDate.parse(dataArr[AT_BY_DATABASE_INDEX]);
        String description = Parser.decodeDescription(dataArr[DESCRIPTION_EVENT_DEADLINE_DATABASE_INDEX]);
        String[] tagsArr = Parser
                .sanitizeInput(new String(Base64.getDecoder().decode(dataArr[TAGS_EVENT_DEADLINE_DATABASE_INDEX])));

        Deadline deadlineTask = new Deadline(description, by);
        if (isDone) {
            deadlineTask.markDone();
        }

        if (!tagsArr[0].equals("None")) {
            List<Tag> tagsList = Parser.decodeTags(tagsArr);
            Parser.addTags(tagsList, deadlineTask);
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

    /**
     * Decodes a Todo object stored as base64 from storage.
     *
     * @param dataArr string array retrieved from storage.
     * @return Todo object that the stored data represents.
     */
    public static Todo decodeTodo(String[] dataArr) {
        Boolean isDone = Boolean.valueOf(dataArr[IS_DONE_DATABASE_INDEX]);
        String description = new String(Base64.getDecoder().decode(dataArr[DESCRIPTION_TODO_DATABASE_INDEX]));
        Todo todoTask = new Todo(description);
        if (isDone) {
            todoTask.markDone();
        }

        String[] tagsArr = Parser
                .sanitizeInput(new String(Base64.getDecoder().decode(dataArr[TAGS_TODO_DATABASE_INDEX])));
        if (!tagsArr[0].equals("None")) {

            List<Tag> tagsList = Parser.decodeTags(tagsArr);
            Parser.addTags(tagsList, todoTask);
        }
        return todoTask;
    }

    /**
     * Encodes the description of a Task object to base64 for storage.
     *
     * @param task Task whose description is to be stored as base64.
     * @return base64 string for storage.
     */
    public static String encodeDescription(Task task) {
        return Base64.getEncoder()
                .encodeToString(task.getDescription().getBytes());
    }

    /**
     * Decodes a Task object's description stored as base64 from storage.
     *
     * @param dbString base64 string stored in storage.
     * @return string description of the Task object.
     */
    public static String decodeDescription(String dbString) {
        return new String(Base64.getDecoder().decode(dbString));
    }

    /**
     * Encodes the tags of a Task object to base64 for storage.
     *
     * @param task Task whose tags are to be stored as base64.
     * @return base64 string for storage.
     */
    public static String encodeTags(Task task) {
        return Base64.getEncoder()
                .encodeToString(task.getTags().getBytes());
    }

    /**
     * Decodes a Task object's tags stored as base64 from storage.
     *
     * @param tagsArr base64 string stored in storage.
     * @return string tags of the Task object.
     */
    public static List<Tag> decodeTags(String[] tagsArr) {
        List<Tag> tagsList = Arrays.stream(tagsArr).map(tagStr -> {
            return new Tag(tagStr);
        }).collect(Collectors.toList());
        return tagsList;
    }

    /**
     * Adds a tag to a Task object.
     *
     * @param task Task object to have a tag added to.
     * @param tag string tag to add to Task object.
     */
    public static void addTag(Task task, String tag) {
        String tagContent = tag.substring(1);
        Tag currentTag = new Tag(tagContent);
        task.addTag(currentTag);
    }

    /**
     * Adds tags to a task object.
     *
     * @param task Task object to have tags added to.
     * @param inputArr array of string tags to be added to Task object.
     * @param tagStart starting index of the tags section.
     */
    public static void addTags(Task task, String[] inputArr, int tagStart) {
        if (tagStart != INVALID) {
            for (int j = tagStart; j < inputArr.length; j++) {
                Parser.addTag(task, inputArr[j]);
            }
        }
    }

    /**
     * Adds tags to a task object.
     *
     * @param tagsList List of tags to be added to Task object.
     * @param task Task object to have tags added to.
     */
    public static void addTags(List<Tag> tagsList, Task task) {
        for (Tag tag: tagsList) {
            task.addTag(tag);
        }
    }

    /**
     * Retrieves the starting index of the tags from the user's input.
     *
     * @param inputArr array containing user input.
     * @return index of the first tag in the user input. -1 if no tags are found.
     */
    public static int getTagsStart(String[] inputArr) {
        int tagStart = INVALID;
        for (int i = inputArr.length - 1; i >= 0; i--) {
            String currentString = inputArr[i];
            if (currentString.charAt(0) == '#') {
                tagStart = i;
            }
        }
        return tagStart;
    }

    /**
     * Retrieves the Task's description from the user's input.
     *
     * @param inputArr array containing user input.
     * @param commandIndex index of the end of the description.
     * @return string description of the Task object.
     */
    public static String getDescription(String[] inputArr, int commandIndex) {
        String[] descriptionArray = Arrays.copyOfRange(inputArr, DESCRIPTION_START, commandIndex);
        String description = String.join(" ", descriptionArray);
        return description;
    }

    /**
     * Retrieves the index of the command.
     *
     * @param inputArr array containing user input.
     * @param command command to be searched for.
     * @return Integer index of the command.
     */
    public static int getCommandIndex(String[] inputArr, String command) {
        int commandIndex = INVALID;
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
