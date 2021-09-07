package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class that handles finding tasks.
 */
public class DoAfterCommand implements Command {
    private String userInput;

    /**
     * A constructor for FindCommand object.
     */
    public DoAfterCommand(String userInput) {
        super();
        this.userInput = userInput;
    }


    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String[] words = userInput.split(" ");
            int taskNum = Integer.parseInt(words[1]);
            Task taskToDoAfter = tasks.getTask(taskNum - 1);

            if (words[3].equals("task")) {
                int taskBeforeNum = Integer.parseInt(words[4]);
                Task taskToDoBefore = tasks.getTask(taskBeforeNum - 1);
                taskToDoAfter.setDoAfterTask(taskToDoBefore);
                return ui.getSetAfterTaskMessage(taskToDoAfter, taskToDoBefore);
            } else {
                LocalDateTime refDateTime = LocalDateTime.parse(
                        words[3] + " " + words[4],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                taskToDoAfter.setDoAfterDateTime(refDateTime);
                return ui.getSetAfterDateTimeMessage(taskToDoAfter, refDateTime);
            }
        } catch (NumberFormatException nfe) {
            throw new DukeException("Please only enter an integer after command 'do' and 'after task'!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("One of the task entered does not exist!");
        } catch (DateTimeParseException e) {
            throw new DukeException("The time must be in this format: yyyy-MM-dd HH:mm");
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
