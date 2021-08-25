package duke;

import duke.command.Command;
import duke.command.task.Deadline;
import duke.command.task.Event;
import duke.command.task.TaskList;
import duke.command.task.ToDo;
import duke.util.DukeException;
import duke.util.Message;
import duke.util.Parser;
import duke.util.Storage;

import java.util.Scanner;

public class Duke {

    // Messages
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String REWELCOME_MESSAGE = "Welcome back!\nWhat can Duke do for you once again?";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:\n%s";
    private static final String NO_TASKS_IN_LIST_MESSAGE = "You have no tasks currently. Go create some!";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:\n %s";
    private static final String INVALID_NUMBER = "Please input a valid task number";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String TOO_MANY_ARGUMENTS_LIST_MESSAGE = "An argument after 'list' is not required. Just 'list' will do.";

    // Errors
    private static final String MISSING_DELETE_NUMBER_MESSAGE = "Please input a number after the delete command";
    private static final String MISSING_DONE_NUMBER_MESSAGE = "Please input a number after the done command";

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
                        Message.display_message(NO_TASKS_IN_LIST_MESSAGE);
                    } else {
                        Message.display_message(String.format(LIST_MESSAGE, taskList));
                    }
                    break;
                case DONE:
                    if (remainingText.isEmpty()) {
                        throw new DukeException(MISSING_DONE_NUMBER_MESSAGE);
                    }
                    try {
                        int taskIndex = Integer.parseInt(remainingText);
                        Message.display_message(String.format(DONE_MESSAGE, taskList.markTaskAsDone(taskIndex)));
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
                        Message.display_message(taskList.deleteTask(taskIndex));
                    } catch (NumberFormatException err) {
                        throw new DukeException(INVALID_NUMBER);
                    }
                    break;
                case TODO:
                    ToDo myTodo = ToDo.newTodo(remainingText);
                    Message.display_message(taskList.addTask(myTodo));
                    break;
                case DEADLINE:
                    Deadline myDeadline = Deadline.newDeadline(remainingText, false);
                    Message.display_message(taskList.addTask(myDeadline));
                    break;
                case EVENT:
                    Event myEvent = Event.newEvent(remainingText, false);
                    Message.display_message(taskList.addTask(myEvent));
                    break;
                case BYE:
                    canContinue = false;
                    break;
            }
                storage.updateTaskListToFile(taskList);
            } catch (DukeException err) {
                Message.display_message(err.getMessage());
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Storage myStorage = new Storage();
        if (!myStorage.didTaskFileExist()) {
            Message.display_message(WELCOME_MESSAGE);
        } else {
            Message.display_message(REWELCOME_MESSAGE);
        }
        myStorage.readTaskFile(taskList);
        inputLoop(taskList, myStorage);
        Message.display_message(EXIT_MESSAGE);
    }
}
