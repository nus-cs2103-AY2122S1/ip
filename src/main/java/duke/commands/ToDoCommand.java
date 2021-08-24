package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * ToDoCommand class used to represent a ToDo Command.
 * Contains methods that
 * (i)    executes the ToDoCommand
 */
public class ToDoCommand extends Command {
    public ToDoCommand() {
        super("todo");
    }

    /**
     * execute() method in ToDoCommand to add a ToDo task into the TaskList.
     *
     * @param des   the user input into the Duke chat-box.
     * @param tList the TaskList object used to keep track of all tasks.
     * @throws DukeException if input for ToDo command is not properly formatted.
     */
    public void execute(String des, TaskList tList) throws DukeException {
        if (des.equals("todo")) {
            throw new DukeException("\"todo\" command not correctly formatted \nPlease insert task argument");
        }
        String description = des.substring(5);
        ToDo atHand = new ToDo(description);
        tList.add(atHand);
        System.out.println("Sure. The following task has been added: ");
        System.out.println(atHand);
        this.numberOfTasks(tList);

        Storage.createFile();
        Storage.writeToFile(tList);
    }
}
