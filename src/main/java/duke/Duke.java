package duke;

import duke.command.Command;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.util.DukeException;
import duke.util.Ui;
import duke.util.Parser;
import duke.util.Storage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static duke.util.Ui.DONE_MESSAGE;
import static duke.util.Ui.EXIT_MESSAGE;
import static duke.util.Ui.INVALID_NUMBER;
import static duke.util.Ui.LIST_MESSAGE;
import static duke.util.Ui.MISSING_DELETE_NUMBER_MESSAGE;
import static duke.util.Ui.MISSING_DONE_NUMBER_MESSAGE;
import static duke.util.Ui.NO_TASKS_IN_LIST_MESSAGE;
import static duke.util.Ui.REWELCOME_MESSAGE;
import static duke.util.Ui.TOO_MANY_ARGUMENTS_LIST_MESSAGE;
import static duke.util.Ui.WELCOME_MESSAGE;

/**
 * The Duke program. The input loop is abstracted here.
 */
public class Duke {
    /** Storage file path */
    private static final String OUTER_DIR = "data";
    private static final String FILE = "taskList.txt";

    /** Instance variables */
    private final Storage myStorage;
    private TaskList taskList;

    /**
     * The input loop. Handles user input, creates tasks and outputs messages accordingly.
     *
     * @param taskList The TaskList object to read from and write to
     * @param storage The Storage object to read from and write to
     */
    private static void inputLoop(TaskList taskList, Storage storage) {
        Scanner sc = new Scanner(System.in);
        boolean canContinue = true;
        while (canContinue) {
            String input = sc.nextLine();
            String[] inputArr = input.split(" ");
            try {
                String firstWord = inputArr[0];
                String remainingText = Parser.getRemainingText(firstWord, input);
                Command command = Command.initialiseCommand(firstWord);
                switch (command) {
                case LIST:
                    if (!remainingText.isEmpty()) {
                        throw new DukeException(TOO_MANY_ARGUMENTS_LIST_MESSAGE);
                    }
                    if (taskList.size() == 0) {
                        Ui.display_message(NO_TASKS_IN_LIST_MESSAGE);
                    } else {
                        Ui.display_message(String.format(LIST_MESSAGE, taskList));
                    }
                    break;
                case DONE:
                    if (remainingText.isEmpty()) {
                        throw new DukeException(MISSING_DONE_NUMBER_MESSAGE);
                    }
                    try {
                        int taskIndex = Integer.parseInt(remainingText);
                        Ui.display_message(String.format(DONE_MESSAGE, taskList.markTaskAsDone(taskIndex)));
                    } catch (NumberFormatException err) {
                        throw new DukeException(INVALID_NUMBER);
                    }
                    break;
                case DELETE:
                    if (remainingText.isEmpty()) {
                        throw new DukeException(MISSING_DELETE_NUMBER_MESSAGE);
                    }
                    try {
                        int taskIndex = Integer.parseInt(remainingText);
                        Ui.display_message(taskList.deleteTask(taskIndex));
                    } catch (NumberFormatException err) {
                        throw new DukeException(INVALID_NUMBER);
                    }
                    break;
                case TODO:
                    ToDo myTodo = ToDo.newTodo(remainingText);
                    Ui.display_message(taskList.addTask(myTodo));
                    break;
                case FIND:
                    Ui.display_message(taskList.findTask(remainingText));
                    break;
                case DEADLINE:
                    Deadline myDeadline = Deadline.newDeadline(remainingText, false);
                    Ui.display_message(taskList.addTask(myDeadline));
                    break;
                case EVENT:
                    Event myEvent = Event.newEvent(remainingText, false);
                    Ui.display_message(taskList.addTask(myEvent));
                    break;
                case BYE:
                    canContinue = false;
                    break;
            }
                storage.updateTaskListToFile(taskList);
            } catch (DukeException err) {
                Ui.display_message(err.getMessage());
            }
        }
        sc.close();
    }

    public Duke(Path filePath) {
        myStorage = new Storage(filePath);
        try {
            taskList = new TaskList(myStorage.load());
        } catch (DukeException err) {
            Ui.display_message(err.getMessage());
            taskList = new TaskList();
        }
    }

    public void run() {
        if (!myStorage.didTaskFileExist()) {
            Ui.display_message(WELCOME_MESSAGE);
        } else {
            Ui.display_message(REWELCOME_MESSAGE);
        }
        myStorage.readTaskFile(taskList);
        inputLoop(taskList, myStorage);
        Ui.display_message(EXIT_MESSAGE);
    }

    public static void main(String[] args) {
        new Duke(Paths.get(OUTER_DIR, FILE)).run();
    }
}
