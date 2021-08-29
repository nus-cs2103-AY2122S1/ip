package duke.storage;

import duke.exceptions.DukeException;
import duke.parser.DateTimeParser;
import duke.tasklist.TaskList;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * The Storage Class handles the reading and writing of tasks onto the disk
 */
public class Storage {
    private final String filePath;
    private final String folderPath;

    /**
     * public constructor to initialise the filepath and folderpath of a storage object
     * @param filePath filePath of the storage file
     * @param folderPath path of the folder in which the file is stored
     */
    public Storage(String filePath, String folderPath) {
        this.filePath = filePath;
        this.folderPath = folderPath;
    }


    /**
     * Reads all the existing tasks in the file and appends them to the task list
     *
     * @param taskList the task list to be operated on
     */
    public void readTasks(TaskList taskList) {
        try {
            Task task;
            File fol = new File(folderPath);
            fol.mkdir();
            File file = new File(filePath);
            file.createNewFile();

            Scanner sc = new Scanner(file);
            if (file.exists()) {
                while (sc.hasNext()) {
                    String inp = sc.nextLine();
                    task = stringToTask(inp);
                    if (!taskList.containsTask(task)) {
                        taskList.add(task);
                    }
                }
            }
        } catch (IOException ex) {
            throw new DukeException("The storage file could not be created");
        }
    }

    /**
     * Saves all the tasks in a list to the file
     *
     * @param taskList the input task list whose tasks will be saved locally
     */
    public void saveTasks(TaskList taskList) {
        try {
            FileWriter f = new FileWriter(filePath);
            for (int i = 0; i < taskList.size(); i++) {
                Task t = taskList.get(i);
                f.write(taskToString(t));
            }
            f.close();
        } catch (IOException ex) {
            throw new DukeException("The storage file could not be found");
        }
    }

    /**
     * Converts a string into a task to be stored in the task list
     *
     * @param str task string to be converted
     * @return the output task
     */
    public Task stringToTask(String str) {
        String[] parsed = str.split("\\|");
        String taskType = parsed[0].trim();
        Task task;
            switch (taskType) {
            case "T":
                task = new ToDo(parsed[2].trim());
                if (parsed[1].trim().equals("1")) {
                    task.markAsDone();
                }
                break;
            case "D":
                task = new Deadline(parsed[2].trim(), DateTimeParser.readDate(parsed[3].trim()));
                if (parsed[1].trim().equals("1")) {
                    task.markAsDone();
                }
                break;
            case "E":
                task = new Event(parsed[2].trim(), DateTimeParser.readDateTime(parsed[3].trim()));
                if (parsed[1].trim().equals("1")) {
                    task.markAsDone();
                }
                break;

            default:
                task = new Task("");
            }
        return task;
    }

    /**
     * Converts a task into a string to be stored in the file
     *
     * @param task task to be converted into a string
     * @return the output string which is the format in which the task is stored locally
     */
    public String taskToString(Task task) {
        String[] parsedTask = task.toString().split("\\s");
        String taskType = parsedTask[0];
        String str;

            switch (taskType) {
            case "[T]":
                str = "T" + " | " + task.getIntStatus() + " | " + task.getDescription() + "\n";
                break;
            case "[D]":
                str = "D" + " | " + task.getIntStatus() + " | " + task.getDescription() + " | "
                        + task.getDateString() + "\n";
                break;
            case "[E]":
                str = "E" + " | " + task.getIntStatus() + " | " + task.getDescription() + " | "
                        + task.getDateString() + "\n";
                break;
            default:
                str = "";
                break;
            }
        return str;
    }


}