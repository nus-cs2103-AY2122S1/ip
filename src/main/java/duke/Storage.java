package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.DeadlineTask;
import duke.task.DoWithinPeriodTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.ToDoTask;


/**
 * Class to abstract the storing of commands
 */
public class Storage {

    private File storageFile;

    /**
     * Constructor for the Storage Class
     *
     * @param filePath The path of the file
     */
    public Storage(String filePath) {
        if (filePath != null) {
            storageFile = new File(filePath);
        }
    }

    /**
     * Method to save the Task List in the File
     *
     * @param tasks The Task List of the Duke
     */
    public void saveTaskList(TaskList tasks) {
        assert tasks != null : "Tasks is not initialized!";
        try {
            File dir = new File("data");
            boolean isDeleted = false;
            boolean isCreated = false;
            boolean isDirectory = dir.exists() ? true : dir.mkdirs();
            File data = new File(dir, "duke.txt");
            if (isDirectory && data.exists()) {
                isDeleted = data.delete();
            }
            if (isDeleted) {
                isCreated = data.createNewFile();
            }
            if (isCreated) {
                writeToFile(tasks, data);
            }
        } catch (IOException e) {
            File deletedFile = new File("data/duke.txt");
            boolean isDeleted = deletedFile.delete();

            if (isDeleted) {
                boolean value = deletedFile.exists();
                if (value) {
                    return;
                }
            }
        }
    }

    private void writeToFile(TaskList tasks, File data) throws IOException {
        assert tasks != null : "Tasks is not initialized!";
        FileWriter storeData = new FileWriter(data);
        Task task;
        for (int i = 0; i < tasks.getTaskListLength(); i++) {
            task = tasks.getTask(i);
            if (task instanceof DeadlineTask) {
                DeadlineTask deadline = (DeadlineTask) task;
                storeData.write(deadline.writeToFile());
            } else if (task instanceof EventTask) {
                EventTask event = (EventTask) task;
                storeData.write(event.writeToFile());
            } else if (task instanceof DoWithinPeriodTask) {
                DoWithinPeriodTask doWithinPeriodTask = (DoWithinPeriodTask) task;
                storeData.write(doWithinPeriodTask.writeToFile());
            } else if (task instanceof ToDoTask) {
                ToDoTask todo = (ToDoTask) task;
                storeData.write(todo.writeToFile());
            }
        }
        storeData.close();
    }

    /**
     * Method to return the Type of Command to be stored
     *
     * @param command The Command Passed for comparing to the different types of CommandTypes
     * @return The Type of Command Received
     */
    public Command.CommandTypes getCommand(String command) {
        try {
            if (command != null) {
                return Command.CommandTypes.valueOf(command.toUpperCase());
            } else {
                return Command.CommandTypes.UNKNOWN;
            }
        } catch (IllegalArgumentException e) {
            return Command.CommandTypes.UNKNOWN;
        }
    }

    /**
     * Method to get the List of Tasks stored in the file
     *
     * @return A list of Tasks which are stored in the file
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        try {
            Scanner dataInput = new Scanner(storageFile);
            String[] data;
            while (dataInput.hasNextLine()) {
                data = dataInput.nextLine().split(" [|] ");
                switch (getCommand(data[0])) {
                case DEADLINE:
                    addDeadline(data, tasks);
                    break;
                case EVENT:
                    addEvent(data, tasks);
                    break;
                case DO_WITHIN_PERIOD:
                    addDoWithinPeriod(data, tasks);
                    break;
                case TODO:
                    addToDo(data, tasks);
                    break;
                case UNKNOWN:
                default:
                }
            }
        } catch (FileNotFoundException ignored) {
            // Don't need to display or return anything since file not found is equivalent to no tasks in the TaskList
        }
        return tasks;
    }

    private void addDeadline(String[] data, List<Task> tasks) {
        assert data != null : "data has not been initialised";
        try {
            if (data.length == 4) {
                DeadlineTask deadline = new DeadlineTask(data[1], data[2], data[3]);
                tasks.add(deadline);
            }
        } catch (DateTimeParseException ignored) {
            // Don't need to display or return anything since this task is corrupted and won't be added to the TaskList
        }
    }

    private void addEvent(String[] data, List<Task> tasks) {
        assert data != null : "data has not been initialised";
        try {
            if (data.length == 4) {
                EventTask event = new EventTask(data[1], data[2], data[3]);
                tasks.add(event);
            }
        } catch (DateTimeParseException ignored) {
            // Don't need to display or return anything since this task is corrupted and won't be added to the TaskList
        }
    }

    private void addDoWithinPeriod(String[] data, List<Task> tasks) {
        assert data != null : "data has not been initialised";
        try {
            if (data.length == 5) {
                DoWithinPeriodTask doWithinPeriodTask = new DoWithinPeriodTask(data[1], data[2], data[3], data[4]);
                tasks.add(doWithinPeriodTask);
            }
        } catch (DateTimeParseException ignored) {
            // Don't need to display or return anything since this task is corrupted and won't be added to the TaskList
        }
    }

    private void addToDo(String[] data, List<Task> tasks) {
        assert data != null : "data has not been initialised";
        if (data.length == 3) {
            ToDoTask todo = new ToDoTask(data[1], data[2]);
            tasks.add(todo);
        }
    }
}
