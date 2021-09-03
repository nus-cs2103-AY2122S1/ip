package duke;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import duke.exception.DukeFileSystemException;
import duke.exception.InvalidDukeCommandException;


/**
 * A class that provides functionalities for users to run a command-line-interface task tracker.
 */
public class Duke {
    // Constant declarations
    private final Ui ui;
    private final DukeParser parser;
    private Optional<Storage> storage;
    private TaskList taskList;


    public Duke() {
        String filePath = "data/duke.txt";
        this.ui = new Ui();
        this.parser = new DukeParser();
        try {
            this.storage = Optional.of(new Storage(filePath));
            this.taskList = this.storage.map(value -> new TaskList(value.load())).orElseGet(TaskList::new);
        } catch (IOException e) {
            ui.dukeShowError("The file path " + filePath + " is invalid. This session will not be stored.");
            this.storage = Optional.empty();
            this.taskList = new TaskList();
        } catch (DukeFileSystemException e) {
            ui.dukePrint(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    /**
     * Constructs a new instance of Duke, the task manager. If the file path specified, which is used to store and load
     * previously added tasks, is invalid, Duke will default to a new empty task list instead, and executing Duke.run
     * () will not cache the tasks added or deleted for that particular session.
     *
     * @param filePath the file path to store and load previously added tasks
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.parser = new DukeParser();
        try {
            this.storage = Optional.of(new Storage(filePath));
            this.taskList = this.storage.map(value -> new TaskList(value.load())).orElseGet(TaskList::new);
        } catch (IOException e) {
            ui.dukeShowError("The file path " + filePath + " is invalid. This session will not be stored.");
            this.storage = Optional.empty();
            this.taskList = new TaskList();
        } catch (DukeFileSystemException e) {
            ui.dukePrint(e.getMessage());
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
        String eventTime;
        try {
            eventTime = args.split(" /at ")[1];
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
        String finishDate;
        try {
            finishDate = args.split(" /by ")[1];
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
        return taskList.toString();
    }

    private String doneHandler(String args) {
        if (args.equals("")) {
            return "The done command expects an integer argument indicating the index of a task.";
        }
        Task task;
        try {
            task = taskList.getTask(Integer.parseInt(args));
        } catch (NumberFormatException e) {
            return "Invalid argument for done command. Argument should be an integer.";
        } catch (IndexOutOfBoundsException e) {
            return "Invalid integer. Integer should match the index of a task."
                    + " Run list to look at the list of tasks and their corresponding indices.";
        }
        String acknowledgementMessage = "Nice! I've marked this task as done:\n";
        task.markAsDone();
        return acknowledgementMessage + task;
    }

    private String deleteHandler(String args) {
        if (args.equals("")) {
            return "The delete command expects an integer argument indicating the index of a task.";
        }
        Task task;
        try {
            task = taskList.deleteTask(Integer.parseInt(args));
        } catch (NumberFormatException e) {
            return "Invalid argument for delete command. Argument should be an integer.";
        } catch (IndexOutOfBoundsException e) {
            return "Invalid integer. Integer should match the index of a task."
                    + " Run list to look at the list of tasks and their corresponding indices.";
        }
        return String.format("Noted. I've removed this task:\n%s\nNow you have %d task%s in the list.",
                task, taskList.size(), taskList.size() > 1 ? "s" : "");
    }

    private String findHandler(String args) {
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
     *
     * @param command     the Duke command read by the parser.
     * @param argsLiteral the arguments read by the parser.
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
     * Prompts users to input their commands to Duke
     */
    private void promptUserCommands() {
        String introduction = "Hello! I'm Duke\nWhat can I do for you?";
        ui.dukePrint(introduction);

        Scanner reader = new Scanner(System.in);
        while (true) {
            String userInput = reader.nextLine();
            DukeCommand command = parser.getCommandType(userInput);
            String argsLiteral = parser.getArgsLiteral(userInput);
            handleInput(command, argsLiteral);
            if (command == DukeCommand.BYE) {
                break;
            }
        }
        reader.close();
    }

    /**
     * Starts the execution of a new session of the Duke task manager CLI.
     */
    public void run() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        promptUserCommands();
    }

    /**
     * Gets Duke's introductory statement.
     * @return a string representing Duke's introduction.
     */
    public String getIntroduction() {
        return "Hello! I'm Duke\nWhat can I do for you?";

    }

    /**
     * Gets Duke's response to a specified input.
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

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
