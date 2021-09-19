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
        assert tasks != null : "Tasklist is not initialized!";
        UI ui = new UI();
        try {
            File dir = new File("data");
            boolean isDeleted = false;
            boolean isCreated = false;
            boolean isDirectory = dir.mkdirs();

            File data = new File(dir, "duke.txt");
            if (isDirectory && data.exists()) {
                isDeleted = data.delete();
            }
            if (isDeleted) {
                isCreated = data.createNewFile();
            }
            if (isCreated) {
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
                    } else if (task instanceof ToDoTask) {
                        ToDoTask todo = (ToDoTask) task;
                        storeData.write(todo.writeToFile());
                    }
                }
                storeData.close();
            }
        } catch (IOException e) {
            File deletedFile = new File("data/duke.txt");
            boolean isDeleted = deletedFile.delete();
            boolean shouldShow = false;

            if (isDeleted) {
                shouldShow = deletedFile.exists();
            }

            if (shouldShow) {
                return;
            }
        }
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
        List<Task> taskList = new ArrayList<>();
        try {
            Scanner dataInput = new Scanner(storageFile);
            String[] data;
            while (dataInput.hasNextLine()) {
                try {
                    data = dataInput.nextLine().split(" [|] ");
                    switch (getCommand(data[0])) {
                    case DEADLINE:
                        if (data.length != 4) {
                            continue;
                        } else {
                            DeadlineTask deadline = new DeadlineTask(data[1], data[2], data[3]);
                            taskList.add(deadline);
                        }
                        break;
                    case EVENT:
                        if (data.length != 4) {
                            continue;
                        } else {
                            EventTask event = new EventTask(data[1], data[2], data[3]);
                            taskList.add(event);
                        }
                        break;
                    case TODO:
                        if (data.length != 3) {
                            continue;
                        } else {
                            ToDoTask todo = new ToDoTask(data[1], data[2]);
                            taskList.add(todo);
                        }
                        break;
                    case UNKNOWN:
                    default:
                    }
                } catch (DateTimeParseException ignored) {
                    ignored.getMessage();
                }
            }
        } catch (FileNotFoundException ignored) {
            ignored.getMessage();
        }
        return taskList;
    }
}
