package duke.command;

import java.util.ArrayList;
import java.util.Arrays;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.util.Storage;
import duke.util.Ui;

public class AddCommand implements Command {
    private String[] inputs;

    public AddCommand(String[] inputs) {
        this.inputs = inputs;
    }

    /**
     * Returns error messages or a string showing the added task.
     * The input string must follow input format, otherwise error messages will be return.
     * The new task will be created and added to lst.
     * @param taskList the taskList containing all tasks
     * @return a string showing the added task and number of tasks
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasks = taskList.getTasks();
        ArrayList<String> validType = new ArrayList<>(
                Arrays.asList("deadline", "event", "todo"));
        String type = inputs[0];
        if (!validType.contains(type)) {
            return "I'm sorry, but I don't know what that means :-(";
        }
        if (inputs.length < 2) {
            return "The description of a todo cannot be empty.";
        }

        try {
            String content = inputs[1];
            addTask(type, content, tasks);
        } catch (DukeException e) {
            return e.getMessage();
        }

        try {
            storage.saveData(taskList);
        } catch (DukeException e) {
            return e.getMessage();
        }

        String output = "     Got it. I've added this task: \n"
                + "      " + tasks.get(tasks.size() - 1).toString() + "\n"
                + "     Now you have " + tasks.size() + " tasks in the list. \n";

        return output;
    }

    void addTask(String type, String content, ArrayList<Task> tasks) throws DukeException {
        if (type.equals("todo")) {
            tasks.add(new Todo(content));
        } else if (type.equals("deadline")) {
            String[] strings = content.split(" /by ");
            if (strings.length != 2) {
                throw new DukeException("I'm sorry, but I don't know what that means. "
                        + "Please check the format of your deadline.");
            }
            tasks.add(new Deadline(content.split(" /by ")[0], content.split(" /by ")[1]));
        } else if (type.equals("event")) {
            String[] strings = content.split(" /at ");
            if (strings.length != 2) {
                throw new DukeException("I'm sorry, but I don't know what that means. "
                        + "Please check the format of your event.");
            }
            tasks.add(new Event(content.split(" /at ")[0], content.split(" /at ")[1]));
        } else {
            assert false : "The type of task is not recognized.";
        }

    }


}
