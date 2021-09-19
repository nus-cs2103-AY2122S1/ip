package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class that handles finding tasks.
 */
public class AfterCommand implements Command {
    private String userInput;

    /**
     * A constructor for FindCommand object.
     */
    public AfterCommand(String userInput) {
        super();
        this.userInput = userInput;
    }


    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String format = "Correct format after... :\n"
                + "To see the tasks to begin after another task:\n"
                + "  after task x (x is a task number)\n"
                + "To see the tasks to begin after a date and time:\n"
                + "  after yyyy-MM-dd HH:mm";

        try {
            if (this.userInput.startsWith("after task")) {
                int taskNum = Integer.parseInt(userInput.substring(11));
                Task refTask = tasks.getTask(taskNum - 1);
                TaskList tasksToDoAfter = tasksAfterTask(tasks, refTask);
                return ui.getTasksAfterTask(tasksToDoAfter, refTask);
            } else {
                LocalDateTime refDateTime = LocalDateTime.parse(
                        this.userInput.substring(6),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                TaskList tasksToDoAfter = tasksAfterDateTime(tasks, refDateTime);
                return ui.getTasksAfterDateTime(tasksToDoAfter, refDateTime);
            }
        } catch (NumberFormatException nfe) {
            throw new DukeException("Please only enter an integer after command 'after task'!\n\n" + format);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number " + userInput.substring(11) + " does not exist!");
        } catch (DateTimeParseException e) {
            throw new DukeException("The time must be in this format: yyyy-MM-dd HH:mm\n\n" + format);
        }
    }

    private TaskList tasksAfterTask(TaskList tasks, Task refTask) {
        TaskList tasksToDoAfter = new TaskList();

        for (int i = 0; i < tasks.numOfTasks(); i++) {
            Task curr = tasks.getTask(i);

            if (curr.isAfterTask(refTask)) {
                tasksToDoAfter.addTask(curr);
            }
        }

        return tasksToDoAfter;
    }

    private TaskList tasksAfterDateTime(TaskList tasks, LocalDateTime dateTime) {
        TaskList tasksToDoAfter = new TaskList();

        for (int i = 0; i < tasks.numOfTasks(); i++) {
            Task curr = tasks.getTask(i);

            if (curr.isAfterDateTime(dateTime)) {
                tasksToDoAfter.addTask(curr);
            }
        }

        return tasksToDoAfter;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
