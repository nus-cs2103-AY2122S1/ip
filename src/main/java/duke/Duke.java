package duke;

import duke.data.Storage;
import duke.data.TaskList;
import duke.io.Command;
import duke.io.Parser;
import duke.io.Ui;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTaskData());
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        String userInput;
        boolean isTaskListUpdated = false;
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                try {
                    if (isTaskListUpdated) {
                        // update file
                        storage.saveTaskData(taskList);
                        isTaskListUpdated = false;
                    }

                    userInput = Parser.getInput(sc);

                    Command command = Parser.parse(userInput);
                    String description;
                    LocalDate dateTime;

                    switch (command.getCommand()) {
                        case BYE:
                            ui.printMessage("Bye. Hope to see you again soon!");
                            return;
                        case LIST:
                            ui.printTaskList(taskList);
                            break;
                        case DONE:
                            // -1 to account for zero-indexing
                            int index = Integer.parseInt(command.getArgs()[0]) - 1;
                            taskList.get(index).markAsDone();
                            isTaskListUpdated = true;

                            ui.printMessage("Nice! I've marked this task as done:\n  " + taskList.get(index));
                            break;
                        case DELETE:
                            // -1 to account for zero-indexing
                            Task removedTask = taskList.remove(Integer.parseInt(command.getArgs()[0]) - 1);
                            isTaskListUpdated = true;

                            ui.printMessage("Noted. I've removed this task:\n  " + removedTask + "\nNow you have "
                                    + taskList.size() + " tasks in the list.");
                            break;
                        case TODO:
                            Todo newTodo = new Todo(userInput.substring(5).trim());
                            this.taskList.add(newTodo);
                            isTaskListUpdated = true;

                            ui.printMessage("Got it. I've added this task:\n  " + newTodo + "\nNow you have " + this.taskList.size()
                                    + " tasks in the list.");
                            break;
                        case DEADLINE:
                            description = command.getArgs()[0];
                            dateTime = LocalDate.parse(command.getArgs()[1]);
                            Deadline newDeadline = new Deadline(description, dateTime);
                            taskList.add(newDeadline);
                            isTaskListUpdated = true;

                            ui.printMessage("Got it. I've added this task:\n  " + newDeadline + "\nNow you have "
                                    + taskList.size() + " tasks in the list.");
                            break;
                        case EVENT:
                            description = command.getArgs()[0];
                            dateTime = LocalDate.parse(command.getArgs()[1]);
                            Event newEvent = new Event(description, dateTime);
                            taskList.add(newEvent);
                            isTaskListUpdated = true;

                            ui.printMessage("Got it. I've added this task:\n  " + newEvent + "\nNow you have " + taskList.size()
                                    + " tasks in the list.");
                            break;
                        case DATE:
                            LocalDate queryDate = LocalDate.parse(userInput.substring(5));
                            TaskList dueTasks = taskList.filterByDate(queryDate);

                            ui.printTaskList(dueTasks, queryDate);
                            break;
                        default:
                            break;
                    }
                } catch (DukeException e) {
                    ui.printMessage(e.getMessage());
                } catch (DateTimeParseException e) {
                    ui.printMessage("Unknown date format. Please input a valid date in the format: YYYY-MM-DD");
                }
            }
        } catch (IOException e) {
            ui.printMessage(e.getMessage());
        }catch (Exception e) {
            ui.printMessage("An unexpected exception has occurred" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}