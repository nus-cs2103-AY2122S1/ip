package meap.util;

import meap.exception.DukeException;
import meap.task.Deadline;
import meap.task.Task;
import meap.task.TaskList;
import meap.task.ToDo;
import meap.task.Event;

public class Parser {
    private final TaskList TASKLIST;

    /**
     * Factory method of Parser class.
     * Initializes tasklist so commands parsed can be executed on the TaskList.
     *
     * @param taskList TaskList the commands will be working on
     */
    public static Parser initialize(TaskList taskList) {
        return new Parser(taskList);
    }

    /**
     * Constructor for Parser class
     * @param taskList TaskList the commands will be working on
     */
    private Parser(TaskList taskList) {
        this.TASKLIST = taskList;
    }

    /**
     * Parses string command, executes appropriate command and returns system reply
     *
     * @return System's reply message to user input
     */
    public String parseCommand(String userInput) {
        if (userInput.matches(Ui.LIST_COMMAND_REGEX)) {
            return String.format(
                    "Here are the tasks in your list:\n%s",
                    TASKLIST.toString()
            );
        } else if (userInput.matches(Ui.BYE_COMMAND_REGEX)) {
            return Ui.CLOSING_STATEMENT;
            // MainWindow handles termination logic (closure of application)
        } else if (userInput.matches(Ui.FIND_COMMAND_REGEX)) {
            String keyword = userInput.split(" ", 2)[1];

            return TASKLIST.executeFindCommand(keyword);
        } else if (userInput.matches(Ui.DONE_COMMAND_REGEX)) {
            String inputBody = userInput.split(" ", 2)[1];
            int idxFrom0 = Integer.parseInt(inputBody) - 1;

            return TASKLIST.executeDoneCommand(idxFrom0);
        } else if (userInput.matches(Ui.DELETE_COMMAND_REGEX)) {
            //eg. delete 3
            String inputBody = userInput.split(" ", 2)[1];
            int idxFrom0 = Integer.parseInt(inputBody) - 1;

            return TASKLIST.executeDeleteCommand(idxFrom0);
        } else if (userInput.matches(ToDo.COMMAND_REGEX)) {
            //eg. todo xxx
            String inputBody = userInput.split(" ", 2)[1];

            Task newTask = ToDo.of(inputBody);
            String reply = String.format(
                    "Got it! I've added this task:\n %s\nNow you have %d tasks in the list.",
                    newTask.toString(),
                    TASKLIST.length() + 1
            );
            TASKLIST.addTask(newTask);
            return reply;
        } else if (userInput.matches(Deadline.COMMAND_REGEX)) {
            //eg. deadline xxx /by dd-MM-uuuu HHmm
            String inputBody = userInput.split(" ", 2)[1];
            String[] deadlineDetails = inputBody.split("\\s/by\\s", 2);
            String deadlineTask = deadlineDetails[0];
            String deadlineByDate = deadlineDetails[1];

            Task newTask = Deadline.of(deadlineTask, deadlineByDate);
            String reply = String.format(
                    "Got it! I've added this task:\n %s\nNow you have %d tasks in the list.",
                    newTask.toString(),
                    TASKLIST.length()  + 1
            );
            TASKLIST.addTask(newTask);
            return reply;
        } else if (userInput.matches(Event.COMMAND_REGEX)) {
            //eg. event xxx /by xxx
            String inputBody = userInput.split(" ", 2)[1];
            String[] eventDetails = inputBody.split("\\s/at\\s", 2);
            String eventTask = eventDetails[0];
            String eventTime = eventDetails[1];

            Task newTask = Event.of(eventTask, eventTime);
            String reply = String.format(
                    "Got it! I've added this task:\n %s\nNow you have %d tasks in the list.",
                    newTask.toString(),
                    TASKLIST.length()  + 1
            );
            TASKLIST.addTask(newTask);
            return reply;
        } else if (userInput.matches(Ui.HELP_COMMAND_REGEX)) {
            return Ui.allUserCommandsSyntax();
        }
        throw DukeException.of(userInput);
    }
}
