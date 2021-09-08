package nyx.command;

import java.io.IOException;

import nyx.NyxException;
import nyx.Storage;
import nyx.task.Deadline;
import nyx.task.Event;
import nyx.task.Task;
import nyx.task.TaskList;
import nyx.task.ToDo;

public class UpdateCommand extends Command {
    public UpdateCommand(String information) {
        super(information);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws NyxException {
        String[] splitInfo = information.split(" ", 2);
        assert splitInfo.length > 0 : "Update information not read in correctly";

        try {
            int index = Integer.parseInt(splitInfo[0]) - 1;

            if (taskList.getNumTasks() == 0) {
                return "No task to delete!";
            }

            Task task = taskList.getTask(index);

            if (task instanceof ToDo) {
                task.updateContent(splitInfo[1]);
            } else if (task instanceof Deadline) {
                String[] secondInfo = splitInfo[1].split(" /by ");
                if (secondInfo.length != 2) {
                    throw new NyxException("You need to update deadline using the following format:"
                            + "\n{ details } /by { datetime }");
                }
                task.updateContent(secondInfo[0]);
                Deadline deadline = (Deadline) task;
                deadline.changeDateTime(secondInfo[1]);
            } else {
                String[] secondInfo = splitInfo[1].split(" /at ");
                if (secondInfo.length != 2) {
                    throw new NyxException("You need to update event using the following format:"
                            + "\n{ details } /at { datetime }");
                }
                task.updateContent(secondInfo[0]);
                Event event = (Event) task;
                event.changeDateTime(secondInfo[1]);
            }
            storage.overwriteData(taskList);

            return String.format("Noted! I've update this task to:\n  %s", task);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new NyxException("Invalid task index!");
        } catch (IOException e) {
            throw new NyxException("Unable to save the changes...");
        }
    }

    public static void throwEmptyException() throws NyxException {
        throw new NyxException("You need to specify what to update!");
    }
}
