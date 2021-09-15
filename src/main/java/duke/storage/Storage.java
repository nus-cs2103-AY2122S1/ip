package duke.storage;

import duke.task.TaskList;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * A class that deals with the loading and updating of local storage
 * file that contains the list of tasks.
 */
public class Storage {
    private final File tasksFile;

    public Storage (String filePath) {
        this.tasksFile = new File(filePath);
        File dir = new File(tasksFile.getParent());
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * Loads the existing (if any) list of tasks from the user's
     * hard disk. If the file does not exist, one will be made.
     *
     * @return the list of tasks from the file
     * @throws FileNotFoundException if the file is not found
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        try {
            if (tasksFile.createNewFile()) {
                return new ArrayList<>();
            } else {
                ArrayList<Task> tasks = new ArrayList<>();

                Scanner sc = new Scanner(tasksFile);
                while (sc.hasNext()) {
                    String currLine = sc.nextLine();
                    String[] parts = currLine.trim().split(Pattern.quote(" | "));

                    String taskType = parts[0];
                    String taskDone = parts[1];
                    String taskDesc = parts[2];

                    Task currTask;
                    if (taskType.equals("D")) {
                        currTask = new Deadline(taskDesc, parts[3], parts[4]);
                    } else if (taskType.equals("E")) {
                        currTask = new Event(taskDesc, parts[3], parts[4]);
                    } else {
                        currTask = new Todo(taskDesc, parts[3]);
                    }

                    if (taskDone.equals("1")) {
                        currTask.markAsDone();
                    }
                    tasks.add(currTask);
                }
                return tasks;
            }
        } catch (IOException e) {
            System.out.println("Unable to load file");
            System.out.print("Exception occurred: " + e);
        }
        return new ArrayList<>();
    }

    /**
     * Updates the file stored on the user's hard disk once
     * the user ends the usage of the bot.
     */
    public void updateFile(TaskList tasks) {
        String allLines = "";

        for (Task t : tasks.getTaskList()) {
            String currLine = "";

            // Checking for the type of task
            if (t instanceof Todo) {
                currLine += "T | ";
            } else if (t instanceof Event) {
                currLine += "E | ";
            } else {
                currLine += "D | ";
            }

            // Checking if the task is done
            if (t.getIsDone()) {
                currLine += "1 | ";
            } else {
                currLine += "0 | ";
            }

            // Add in the task description
            currLine += t.getDescription();

            // Add in the task deadline / time
            if (t instanceof Deadline) {
                currLine += " | ";
                currLine += ((Deadline)t).getBy();
            } else if (t instanceof Event) {
                currLine += " | ";
                currLine += ((Event)t).getAt();
            }

            // Add in the task priority
            currLine += " | ";
            currLine += t.getPriority();
            allLines += currLine;
            allLines += System.lineSeparator();
        }
        try {
            tasksFile.createNewFile();
            FileWriter writer = new FileWriter(tasksFile, false);
            writer.write(allLines);
            writer.close();

        } catch (IOException e) {
            System.out.println("Unable to update file");
            System.out.print("Exception occurred: " + e);
        }
    }
}
