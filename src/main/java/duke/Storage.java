package duke;

import duke.ui.UserInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {
    public static final String SAVE_SUCCESSFUL_PREFIX = "File successfully saved to ";
    public static final String SAVE_FAILURE_MESSAGE = "Error: Could not save file";
    public static final String LOAD_FAILURE_MESSAGE = "File could not be found";
    public static final String INVALID_TASK_MESSAGE = "Invalid task found";
    private File file;
    public Storage(Path filepath) {
        file = filepath.toFile();
    }

    public TaskList load(UserInterface ui){
        TaskList taskList = new TaskList();
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNext()) {
                String row = fileReader.nextLine();
                Task task = parseTask(row);
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            ui.displayError(LOAD_FAILURE_MESSAGE);
        }
        return taskList;
    }

    private Task parseTask(String taskStr) {
        String[] tokens = taskStr.split(",");
        String type = tokens[0];
        String[] otherTokens = Arrays.copyOfRange(tokens, 1, tokens.length);
        if (type.equals(ToDo.TODO_NAME)) {
            return parseToDo(otherTokens);
        } else if (type.equals(Event.EVENT_NAME)) {
            return parseEvent(otherTokens);
        } else if (type.equals(Deadline.DEADLINE_NAME)) {
            return parseDeadline(otherTokens);
        } else {
            throw new IllegalArgumentException(INVALID_TASK_MESSAGE);
        }
    }

    private Task parseToDo(String[] tokens) {
        return new ToDo(tokens[0], Boolean.parseBoolean(tokens[1]));
    }

    private Task parseEvent(String[] tokens) {
        return new Event(tokens[0], tokens[1], Boolean.parseBoolean(tokens[2]));
    }

    private Task parseDeadline(String[] tokens) {
        return new Deadline(tokens[0], tokens[1], Boolean.parseBoolean(tokens[2]));
    }

    public void save(UserInterface ui, TaskList taskList) {
        try {
            PrintWriter dukeWriter = new PrintWriter(file);
            dukeWriter.print(taskList.toCsvString());
            dukeWriter.close();
            ui.print(SAVE_SUCCESSFUL_PREFIX + file.getPath());
        } catch (FileNotFoundException e) {
            ui.displayError(SAVE_FAILURE_MESSAGE);
        } finally {
            ui.displayFarewell();
        }
    }
}
