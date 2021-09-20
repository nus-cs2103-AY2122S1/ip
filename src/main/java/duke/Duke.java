package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;


/**
 * A personal assistant chatbot that helps a person to keep track of various things.
 *
 * @author Samay Sagar
 * @version CS2103 AY21/22 Sem 1
 */
//Solution below adapted from https://github.com/jovyntls/ip
public class Duke implements Parser {


    private static final String FAREWELL_MESSAGE = "Pike Pika bbye!";
    private static final String FAREWELL_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String COMPLETE_TASK_COMMAND = "done";
    private static final String FIND_TASK_COMMAND = "find";
    private static final String DELETE_TASK_COMMAND = "delete";
    private static final String CREATE_TODO_COMMAND = "t";
    private static final String CREATE_EVENT_COMMAND = "e";
    private static final String CREATE_DEADLINE_COMMAND = "d";

    private TaskList taskList;
    private Storage storage;

    private Scanner sc;

    /**
     * A constructor for duke.Duke chatbot.
     */
    public Duke() {
        taskList = new TaskList();
        storage = new Storage("data/duke_storage.txt");
        loadData();
        sc = new Scanner(System.in);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return taskMode(input);
    }


    /**
     * Loads data that is saved in a given filename, and parses the data to load tasks.
     */
    public void loadData() {
        ArrayList<String> lines = storage.readLines();
        for (int i = 0; i < lines.size(); i++) {
            Task task = Task.parseTaskFromSavedText(lines.get(i));
            taskList.addTask(task);
        }
    }

    /**
     * Saves Chatbot data to a given filename.
     */
    public void saveData() {
        String content = taskList.toSaveData();
        storage.overwriteNewFile();
        storage.writeToFile(content);
    }

    /**
     * Stops Duke.
     */
    public void endDuke() {
        this.saveData();
    }

    /**
     * Handles the logic for managing a user's tasks.
     */
    public String taskMode(String msg) {
        if (msg.equals(FAREWELL_COMMAND)) {
            this.endDuke();
            return FAREWELL_MESSAGE;
        }
        try {
            TaskList tasks = taskList;
            if (msg.equals(LIST_COMMAND)) {
                return tasks.toString();
            } else if (msg.startsWith(COMPLETE_TASK_COMMAND)) {
                return tasks.completeTask(Parser.getIntFrom(COMPLETE_TASK_COMMAND, msg));
            } else if (msg.startsWith(FIND_TASK_COMMAND)) {
                return tasks.findTasks(Parser.getStringFrom(FIND_TASK_COMMAND, msg));
            } else if (msg.startsWith(DELETE_TASK_COMMAND)) {
                return tasks.deleteTask(Parser.getIntFrom(DELETE_TASK_COMMAND, msg));
            } else if (msg.startsWith(CREATE_TODO_COMMAND)) {
                return tasks.addNewTodo(Parser.getStringFrom(CREATE_TODO_COMMAND, msg));
            } else if (msg.startsWith(CREATE_EVENT_COMMAND)) {
                return tasks.addNewEvent(Parser.getStringFrom(CREATE_EVENT_COMMAND, msg));
            } else if (msg.startsWith(CREATE_DEADLINE_COMMAND)) {
                return tasks.addNewDeadline(Parser.getStringFrom(CREATE_DEADLINE_COMMAND, msg));
            } else {
                throw new DukeException("I don't know what that command means."
                        + "\nPlease input a valid command.");
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
