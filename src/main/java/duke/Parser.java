package duke;

import java.util.List;
import java.util.regex.Pattern;

public class Parser {
    /** Regex for a Done command. */
    private final String REGEX_DONE = "done [0-9]+";
    /** Regex for a Delete command. */
    private final String REGEX_DELETE = "delete [0-9]+";
    /** Regex for a ToDo command specifying a new ToDo task. */
    private final String REGEX_TODO = "todo [\\w\\s-]+";
    /** Regex for a Deadline command specifying a new Deadline task. */
    private final String REGEX_DEADLINE = "deadline [\\w\\s-]+ \\/by [\\w\\s-]+";
    /** Regex for a Event command specifying a new Event task. */
    private final String REGEX_EVENT = "event [\\w\\s-]+ \\/at [\\w\\s-]+";
    /** Regex for a Find command. */
    private final String REGEX_FIND = "find [\\w\\s-]+";

    private String command;
    private TaskList tasks;
    private Ui ui;

    // TODO: change javadocs
    /**
     * Handles all user commands.
     * Takes in a user command and checks which command it is. Upon determining the command, performs the
     * necessary action.
     *
     * @param command The user command to be handled.
     * @param tasks The TaskList object containing the list of tasks and operations.
     * @param ui The UI object that handles messages to the user.
     * @return Whether or not the Duke program should exit or continue running.
     */
    public DukeStatus parse(String command, TaskList tasks, Ui ui) {
        this.command = command;
        this.tasks = tasks;
        this.ui = ui;
        DukeStatus currentStatus;

        if (command.equals("help")) {
            currentStatus = handleHelpCommand();
        } else if (command.equals("list")) {
            currentStatus = handleListCommand();
        } else if (command.equals("bye")) {
            currentStatus = handleByeCommand();
        } else if (Pattern.matches(REGEX_DONE, command)) {
            currentStatus = handleDoneCommand();
        } else if (Pattern.matches(REGEX_DELETE, command)) {
            currentStatus = handleDeleteCommand();
        } else if (command.equals("todo")) {
            currentStatus = handleEmptyToDoCommand();
        } else if (Pattern.matches(REGEX_TODO, command)) {
            currentStatus = handleToDoCommand();
        } else if (Pattern.matches(REGEX_DEADLINE, command)) {
            currentStatus = handleDeadlineCommand();
        } else if (Pattern.matches(REGEX_EVENT, command)) {
            currentStatus = handleEventCommand();
        } else if (Pattern.matches(REGEX_FIND, command)) {
            currentStatus = handleFindCommand();
        } else {
            currentStatus = handleUnrecognisableCommand();
        }
        return currentStatus;
    }

    private DukeStatus handleHelpCommand() {
        DukeStatus currentStatus = DukeStatus.MESSAGE;
        currentStatus.setResponse(ui.getHelpMessage());
        return currentStatus;
    }

    private DukeStatus handleListCommand() {
        String[] response = tasks.getStringArr();
        response[0] = "Here are the tasks in your list:";
        DukeStatus currentStatus = DukeStatus.MESSAGE;
        currentStatus.setResponse(ui.getResponse(response));
        return currentStatus;
    }

    private DukeStatus handleByeCommand() {
        return DukeStatus.INACTIVE;
    }

    private DukeStatus handleDoneCommand() {
        String indexStr = command.substring(5);
        int index = Integer.parseInt(indexStr) - 1;
        String res = tasks.markAsDone(index);
        DukeStatus currentStatus = DukeStatus.MESSAGE;
        currentStatus.setResponse(ui.getResponse("Nice! I've marked this task as done:", res));
        return currentStatus;
    }

    private DukeStatus handleDeleteCommand() {
        String indexStr = command.substring(7);
        int index = Integer.parseInt(indexStr) - 1;
        String removedTask = tasks.removeTask(index);
        String numTasksLeft = tasks.numTasks();
        DukeStatus currentStatus = DukeStatus.MESSAGE;
        currentStatus.setResponse(ui.getResponse("Noted. I've removed this task:", removedTask, numTasksLeft));
        return currentStatus;
    }

    private DukeStatus handleEmptyToDoCommand() {
        DukeStatus currentStatus = DukeStatus.ERROR;
        currentStatus.setResponse(ui.getResponse("OOPS!!! The description of a todo cannot be empty."));
        return currentStatus;
    }

    private DukeStatus handleToDoCommand() {
        String name = command.substring(5);
        String taskAdded = tasks.addToDo(name);
        String numTasksLeft = tasks.numTasks();
        DukeStatus currentStatus = DukeStatus.MESSAGE;
        currentStatus.setResponse(ui.getResponse("Got it. I've added this task:", taskAdded, numTasksLeft));
        return currentStatus;
    }

    private DukeStatus handleDeadlineCommand() {
        int breakPos = command.indexOf("/by");
        String name = command.substring(9, breakPos - 1);
        String due = command.substring(breakPos + 4);
        String taskAdded = tasks.addDeadline(name, due);
        String numTasksLeft = tasks.numTasks();
        DukeStatus currentStatus = DukeStatus.MESSAGE;
        currentStatus.setResponse(ui.getResponse("Got it. I've added this task:", taskAdded, numTasksLeft));
        return currentStatus;
    }

    private DukeStatus handleEventCommand() {
        int breakPos = command.indexOf("/at");
        String name = command.substring(6, breakPos - 1);
        String time = command.substring(breakPos + 4);
        String taskAdded = tasks.addEvent(name, time);
        String numTasksLeft = tasks.numTasks();
        DukeStatus currentStatus = DukeStatus.MESSAGE;
        currentStatus.setResponse(ui.getResponse("Got it. I've added this task:", taskAdded, numTasksLeft));
        return currentStatus;
    }

    private DukeStatus handleFindCommand() {
        String textToSearch = command.substring(5);
        List<String> tasksFoundList = tasks.searchTasks(textToSearch);
        String[] tasksFoundArr = new String[tasksFoundList.size() + 1];
        tasksFoundArr[0] = "Here are the matching tasks in your list:";
        for (int i = 0; i < tasksFoundList.size(); i++) {
            int tempIndex = i + 1;
            tasksFoundArr[tempIndex] = tempIndex + ". " + tasksFoundList.get(i);
        }
        DukeStatus currentStatus = DukeStatus.MESSAGE;
        currentStatus.setResponse(ui.getResponse(tasksFoundArr));
        return currentStatus;
    }

    private DukeStatus handleUnrecognisableCommand() {
        DukeStatus currentStatus = DukeStatus.ERROR;
        currentStatus.setResponse(ui.getResponse("OOPS!!! I'm sorry, but I don't know what that means ):"));
        return currentStatus;
    }
}
