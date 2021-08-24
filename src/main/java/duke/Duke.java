package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Duke {

    private TaskList taskList;
    private final Storage storage;
    private final Ui ui;

    private Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (DukeException de) {
            this.ui.showError(de);
            this.taskList = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Runs the Duke bot.
     */
    public void run() {
        boolean isDukeOpen = true;

        while (isDukeOpen) {
            try {
                String userInput = this.ui.readCommand();
                Parser.CommandType type = Parser.decipherInput(userInput);

                if (type == Parser.CommandType.EXIT) {
                    //Closes the program.
                    this.storage.save(this.taskList);
                    isDukeOpen = false;
                    this.ui.exit();

                } else if (type == Parser.CommandType.LIST) {
                    // List all tasks in the task list.
                    System.out.println("Here are the tasks in your list:");
                    this.taskList.listTasks();

                } else if (type == Parser.CommandType.DONE) {
                    // Mark a certain task as done.
                    String[] split = userInput.split(" ");

                    if (split.length > 1) {
                        int taskNumber = Integer.parseInt(split[1].trim());
                        this.taskList.markTaskAsDone(taskNumber);
                        this.storage.save(this.taskList);

                    } else {
                        throw new DukeException("☹ OOPS!!! Please state which task number "
                                + "you want to mark as done.");
                    }

                } else if (type == Parser.CommandType.DELETE) {
                    // Deletes a task from the task list.
                    String[] split = userInput.split(" ", 2);

                    if (split.length > 1 && split[1].trim().length() > 0) {
                        int taskNumber = Integer.parseInt(split[1].trim());
                        this.taskList.deleteTask(taskNumber);
                        this.storage.save(this.taskList);

                    } else {
                        throw new DukeException("☹ OOPS!!! Please state which task number"
                                + "you want to delete.");
                    }

                } else if (type == Parser.CommandType.UNKNOWN) {
                    // Incorrect user input.
                    throw new DukeException(
                            "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

                } else {
                    // Adds a Task to the task list.
                    String[] split = userInput.split(" ", 2);

                    if (split.length > 1 && split[1].trim().length() > 0) {
                        String substring = split[1].trim();
                        Task newTask;
                        switch (type) {
                            case TODO:
                                newTask = new ToDo(substring, false);
                                break;

                            case DEADLINE:
                                String[] nameAndDeadline = substring.split(" /by ");

                                if (nameAndDeadline.length > 1
                                        && nameAndDeadline[1].trim().length() > 0) {
                                    LocalDateTime deadline = Parser
                                            .formatDateTime(nameAndDeadline[1]);
                                    newTask = new Deadline(nameAndDeadline[0], deadline,
                                            false);

                                } else {
                                    throw new DukeException("☹ OOPS!!! Please provide a date or "
                                            + "time for the deadline.");
                                }
                                break;

                            default:
                                String[] nameAndTime = substring.split(" /at ");

                                if (nameAndTime.length > 1
                                        && nameAndTime[1].trim().length() > 0) {
                                    String[] splitEndTime = nameAndTime[1].split(" - ");
                                    LocalDateTime eventTime = Parser
                                            .formatDateTime(splitEndTime[0]);

                                    if (splitEndTime.length > 1
                                            && splitEndTime[1].trim().length() > 0) {
                                        LocalTime endTime = LocalTime.parse(splitEndTime[1]);
                                        newTask = new Event(nameAndTime[0],
                                                eventTime, endTime, false);

                                    } else {
                                        throw new DukeException("☹ OOPS!!! Please provide an end "
                                                + "time for the event.");
                                    }

                                } else {
                                    throw new DukeException("☹ OOPS!!! Please provide a date or "
                                            + "time for the event.");
                                }
                                break;
                        }
                        this.taskList.addTask(newTask);
                        this.storage.save(this.taskList);

                    } else {
                        throw new DukeException("☹ OOPS!!! The description of a " + split[0]
                                + " cannot be empty.");
                    }
                }gi
            } catch (DukeException de) {
                this.ui.showError(de);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("save.txt");
        duke.run();
    }
}
