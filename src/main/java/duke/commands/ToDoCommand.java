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
     * Returns String object to describe execution of ToDoCommand.
     *
     * @param des   User input into the Duke chat-box.
     * @param tList TaskList object used to keep track of all tasks.
     * @return String representation of ToDoCommand.
     * @throws DukeException If input for ToDo command is not properly formatted.
     */
    public String execute(String des, TaskList tList) throws DukeException {
        checkValidDes(des);
        if (des.equals("todo")) {
            throw new DukeException("\"todo\" command not correctly formatted \nPlease insert task argument");
        }
        ToDo atHand = addToDoFromDescriptionToTaskList(des, tList);
        Storage.createFile();
        Storage.writeToFile(tList);
        return "Sure. The following task has been added: \n"
                + atHand
                + "\n"
                + "\n"
                + numberOfTasks(tList)
                + "\n";
    }

    /**
     * Returns ToDo object after adding it into the given task list.
     *
     * @param des User input into the Duke chat-box.
     * @param tList TaskList object used to keep track of all tasks.
     * @return ToDo task that is the most recent addition to tList.
     */
    private ToDo addToDoFromDescriptionToTaskList(String des, TaskList tList) {
        String description = des.substring(5);
        ToDo atHand = new ToDo(description);
        tList.add(atHand);
        return atHand;
    }
}
