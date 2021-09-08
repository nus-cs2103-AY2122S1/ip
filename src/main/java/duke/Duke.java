package duke;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import duke.exception.DukeFileSystemException;
import duke.exception.InvalidDukeCommandException;
import duke.io.DukeParser;
import duke.io.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.type.DukeCommand;


/**
 * A class that provides functionalities for users to run a command-line-interface task tracker.
 */
public class Duke {
    // Constant declarations
    private final DukeParser parser;
    private Optional<Storage> storage;
    private TaskList taskList;

    /**
     * Constructs a new instance of Duke, the task manager. If the file path ./data/duke.txt, which is used to store
     * and load previously added tasks, is invalid, Duke will default to a new empty task list instead, and will not
     * cache the tasks added or deleted for that particular session.
     */
    public Duke() {
        String filePath = "data/duke.txt";
        this.parser = new DukeParser();
        try {
            this.storage = Optional.of(new Storage(filePath));
            this.taskList = this.storage.map(value -> new TaskList(value.load())).orElseGet(TaskList::new);
        } catch (IOException e) {
            this.storage = Optional.empty();
            this.taskList = new TaskList();
        } catch (DukeFileSystemException e) {
            this.taskList = new TaskList();
        }
    }


    private String todoHandler(String args) {
        if (args.equals("")) {
            return "The description of a todo cannot be empty.";
        }
        taskList.appendTask(new Todo(args));
        return String.format("Got it. I've added this task:\n%s\nNow you have %d task%s in the list.",
                args, taskList.size(), taskList.size() > 1 ? "s" : "");
    }

    private String eventHandler(String args) {
        if (args.equals("")) {
            return "The description of an event cannot be empty.";
        }
        String taskDescription = args.split(" /at ")[0];
        try {
            String eventTime = args.split(" /at ")[1];
            taskList.appendTask(new Event(taskDescription, eventTime));
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Incorrect description format. Description should follow this pattern: *description* /at *time*";
        } catch (InvalidDukeCommandException e) {
            return "Invalid time format. Time should follow pattern: yyyy-mm-dd.";
        }
        return String.format("Got it. I've added this task:\n%s\nNow you have %d task%s in the list.",
                taskDescription, taskList.size(), taskList.size() > 1 ? "s" : "");
    }

    private String deadlineHandler(String args) {
        if (args.equals("")) {
            return "The description of a deadline cannot be empty.";
        }
        String taskDescription = args.split(" /by ")[0];
        try {
            String finishDate = args.split(" /by ")[1];
            taskList.appendTask(new Deadline(taskDescription, finishDate));
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Incorrect description format. Description should follow this pattern: *description* /by *time*";
        } catch (InvalidDukeCommandException e) {
            return "Invalid time format. Time should follow pattern: yyyy-mm-dd.";
        }

        return String.format("Got it. I've added this task:\n%s\nNow you have %d task%s in the list.",
                taskDescription, taskList.size(), taskList.size() > 1 ? "s" : "");
    }

    private String listHandler() {
        return taskList.isEmpty() ? "No tasks added yet!" : taskList.toString();
    }

    private String doneHandler(String args) {
        assert (args != null);
        if (args.equals("")) {
            return "The done command expects an integer argument indicating the index of a task.";
        }
        TaskList completedTasks;
        try {
            if (parser.containsRangeSymbol(args)) {
                int[] rangeBoundaries = parser.getRangeBoundaries(args);
                completedTasks = taskList.doTasks(rangeBoundaries[0], rangeBoundaries[1]);
            } else {
                Task completedTask = taskList.doTasks(Integer.parseInt(args));
                completedTasks = new TaskList(Collections.singletonList(completedTask));
            }
        } catch (NumberFormatException e) {
            return "Invalid argument for done command. Argument should be an integer or a range of integers. "
                    + "Example usage: done 1 OR done 1...3";
        } catch (IndexOutOfBoundsException e) {
            return "Invalid integer(s). Integer(s) should match the indices of the tasks and be in ascending order"
                    + " for range of integers. Run list to look at the list of tasks and their corresponding indices.";
        }
        String acknowledgementMessage = "Nice! I've marked this task as done:\n";
        return acknowledgementMessage + completedTasks;
    }

    private String deleteHandler(String args) {
        assert (args != null);
        if (args.equals("")) {
            return "The delete command expects an integer argument indicating the index of a task.";
        }
        TaskList removedTasks;
        try {
            if (parser.containsRangeSymbol(args)) {
                int[] rangeBoundaries = parser.getRangeBoundaries(args);
                removedTasks = taskList.deleteTasks(rangeBoundaries[0], rangeBoundaries[1]);
            } else {
                Task removedTask = taskList.deleteTasks(Integer.parseInt(args));
                removedTasks = new TaskList(Collections.singletonList(removedTask));
            }
        } catch (NumberFormatException e) {
            return "Invalid argument for delete command. Argument should be an integer or a range of integers. "
                    + "Example usage: delete 1 OR delete 1...3";
        } catch (IndexOutOfBoundsException e) {
            return "Invalid integer(s). Integer(s) should match the indices of the tasks and be in ascending order"
                    + " for range of integers. Run list to look at the list of tasks and their corresponding indices.";
        }
        return String.format("Noted. I've removed these tasks:\n%s\nNow you have %d task%s in the list.",
                removedTasks, taskList.size(), taskList.size() > 1 ? "s" : "");
    }

    private String findHandler(String args) {
        assert (taskList != null);
        TaskList filteredList = this.taskList.findTasks(args);
        if (filteredList.size() > 0) {
            return "Here are the matching tasks in your list:\n" + filteredList;
        } else {
            return "We did not find any tasks that matched your query pattern.";
        }
    }

    private String byeHandler() {
        return "Bye. Hope to see you again soon!";
    }

    private String defaultHandler() {
        return "Invalid command detected";
    }

    /**
     * Designates the job to the appropriate handler based on the duke command and args
     * and returns a string of the return message from Duke for that particular input.
     *
     * @param command     the Duke command read by the parser.
     * @param argsLiteral the arguments read by the parser.
     * @return a string of Duke's return message for that particular command.
     */
    private String handleInput(DukeCommand command, String argsLiteral) {
        try {
            String dukeResponse;
            switch (command) {
            case FIND:
                dukeResponse = findHandler(argsLiteral);
                break;
            case DONE:
                dukeResponse = doneHandler(argsLiteral);
                break;
            case BYE:
                dukeResponse = byeHandler();
                break;
            case LIST:
                dukeResponse = listHandler();
                break;
            case TODO:
                dukeResponse = todoHandler(argsLiteral);
                break;
            case EVENT:
                dukeResponse = eventHandler(argsLiteral);
                break;
            case DEADLINE:
                dukeResponse = deadlineHandler(argsLiteral);
                break;
            case DELETE:
                dukeResponse = deleteHandler(argsLiteral);
                break;
            default:
                dukeResponse = defaultHandler();
                // Fallthrough
            }
            if (storage.isPresent()) {
                storage.get().writeTasksToFile(this.taskList);
            }
            return dukeResponse;
        } catch (IOException e) {
            return "Error occurred while storing tasks in data file.";
        }
    }

    /**
     * Gets Duke's introductory statement.
     *
     * @return a string representing Duke's introduction.
     */
    public String getIntroduction() {
        String introduction = "";
        if (taskList.isEmpty()) {
            introduction += "Error while loading previous task list. A new task list will be created for this session"
                    + ".\n";
        }

        if (storage.isEmpty()) {
            introduction += "Data path to storage is inaccessible. This session will not be saved.\n";
        }
        return "Hello! I'm Duke\nWhat can I do for you?";

    }

    /**
     * Gets Duke's response to a specified input.
     *
     * @param input the input specified by the user.
     * @return a string of Duke's response
     */
    public String getResponse(String input) {
        DukeCommand command = parser.getCommandType(input);
        String argsLiteral = parser.getArgsLiteral(input);
        System.out.println(command);
        System.out.println(argsLiteral);
        return handleInput(command, argsLiteral);
    }
}
