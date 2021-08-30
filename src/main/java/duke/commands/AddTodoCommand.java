package duke.commands;

import java.io.FileWriter;
import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.tasks.Todo;

/**
 * Command that adds todo to task list.
 */
public class AddTodoCommand extends Command {

    /**
     * Constructor for AddTodoCommand.
     *
     * @param desc description for todo.
     */
    public AddTodoCommand(String desc) {
        super(desc);
    }

    /**
     * Returns if the command is the exit command.
     *
     * @return false since this command is not the exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command. Adds deadline to task list. Updates the save file.
     *
     * @param tasks the task list.
     */
    @Override
    public void execute(TaskList tasks) throws IOException {

        Todo todo = new Todo(super.getDesc(), false);

        tasks.add(todo);

        System.out.println("Got it. I've added this task:");
        System.out.println(todo);

        if (tasks.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }
}
