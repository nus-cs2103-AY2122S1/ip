package duke;

import duke.task.*;

import java.util.Scanner;

/**
 * Duke is a chatbot application for CS2103T individual project.
 */
public class Duke {
    private static final String filePath = "data/duke.txt";
    private static final Storage storage = new Storage(filePath);

    /**
     * Receives input from the user and executes Duke's actions
     *
     * @param sc       the given scanner instance
     * @param taskList the given task list instance.
     */
    public static void run(Scanner sc, TaskList taskList) {
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            int firstWordIndex = input.indexOf(" ");
            String actionText = firstWordIndex == -1 ? input : input.substring(0, firstWordIndex);
            Parser.Action action = Parser.getAction(actionText);
            String rest = input.substring(firstWordIndex + 1);
            try {
                switch (action) {
                case BYE: {
                    Ui.bye();
                    storage.writeToTaskTxt(taskList.getTasks());
                    sc.close();
                    return;
                }
                case LIST: {
                    taskList.showTasks();
                    break;
                }
                case DONE: {
                    try {
                        int taskNumber = Integer.parseInt(rest);
                        taskList.markTaskAsDone(taskNumber - 1);
                        break;
                    } catch (NumberFormatException e) {
                        throw new DukeException("A number must be given to specified the task.");
                    }
                }
                case TODO: {
                    if (firstWordIndex == -1) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    Todo temp = new Todo(rest);
                    taskList.addTask(temp);
                    Ui.showAddTaskMessage(temp, taskList.getSize());
                    break;
                }
                case DEADLINE: {
                    if (firstWordIndex == -1) {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    }
                    String[] arr = rest.split(" /by ");
                    if (arr.length == 1) {
                        throw new DukeException("/by is needed to specified the time.");
                    }
                    String description = arr[0];
                    String detail = arr[1];
                    Deadline temp = new Deadline(description, Parser.parseDateTime(detail));
                    taskList.addTask(temp);
                    Ui.showAddTaskMessage(temp, taskList.getSize());
                    break;
                }
                case EVENT: {
                    if (firstWordIndex == -1) {
                        throw new DukeException("The description of an event cannot be empty.");
                    }
                    String[] arr = rest.split(" /at ");
                    if (arr.length == 1) {
                        throw new DukeException("/at is needed to specified the time.");
                    }
                    String description = arr[0];
                    String detail = arr[1];
                    Event temp = new Event(description, Parser.parseDateTime(detail));
                    taskList.addTask(temp);
                    Ui.showAddTaskMessage(temp, taskList.getSize());
                    break;
                }
                case DELETE: {
                    try {
                        int taskNumber = Integer.parseInt(rest);
                        Task temp = taskList.removeTask(taskNumber - 1);
                        Ui.showRemoveTaskMessage(temp, taskList.getSize());
                        break;
                    } catch (NumberFormatException e) {
                        throw new DukeException("A number must be given to specified the task.");
                    }
                }
                case UNKNOWN:
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                Ui.showMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Ui.greet();
        Scanner sc = new Scanner(System.in);
        try {
            TaskList taskList = new TaskList(storage.readFromTaskTxt());
            run(sc, taskList);
        } catch (DukeException e) {
            Ui.showMessage(e.getMessage());
        } finally {
            sc.close();
        }
    }
}
