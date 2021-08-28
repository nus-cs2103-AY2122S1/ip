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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {
    private String filePath = "";
    private File tasksFile;

    public Storage (String filePath) {
        this.tasksFile = new File(filePath);
        File dir = new File(tasksFile.getParent());
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        try {
            if (tasksFile.createNewFile()) {
                return new ArrayList<>();
            } else {
                ArrayList<Task> taskList = new ArrayList<>();

                Scanner sc = new Scanner(tasksFile);
                while (sc.hasNext()) {
                    String currLine = sc.nextLine();
                    String[] parts = currLine.trim().split(Pattern.quote(" | "));

                    String taskType = parts[0];
                    String taskDone = parts[1];
                    String taskDesc = parts[2];

                    Task currTask;
                    if (taskType.equals("D")) {
                        currTask = new Deadline(taskDesc, parts[3]);
                    } else if (taskType.equals("E")) {
                        currTask = new Event(taskDesc, parts[3]);
                    } else {
                        currTask = new Todo(taskDesc);
                    }

                    if (taskDone.equals("1")) {
                        currTask.markAsDone();
                    }
                    taskList.add(currTask);
                }
                return taskList;
            }
        } catch (IOException e) {
            System.out.println("Unable to load file");
            System.out.print("Exception occurred: " + e);
        }
        return new ArrayList<>();
    }

    public void updateFile() {
        String allLines = "";

        for (Task t : TaskList.getTaskList()) {
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
            allLines += currLine;
            allLines += System.lineSeparator();
        }
        try {
//        try (BBufferedWriter writer = Files.newBufferedWriter(p, StandardOpenOption.APPEND)ufferedWriter writer = Files.newBufferedWriter(p, StandardOpenOption.APPEND)) {
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
