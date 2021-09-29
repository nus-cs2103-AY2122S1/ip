package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;



/**
 * Class includes methods required to read and write for saving a file.
 */
public class CompilationOfFiles {
    private static String filepath;

    /**
     * Creates for path where file is saved.
     *
     * @param filepath path of the file
     */
    public CompilationOfFiles(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads and saves files.
     *
     * @param tasks list of tasks
     */
    public static void loadAndSaveFile(ListOfTasks tasks) {
        try {
            Files.createDirectories(Paths.get("data/"));
            File newFile = new File(filepath);
            if (newFile.createNewFile()) {
                System.out.println("A new file has been created "
                        + "as there are no saved files.");
            } else {
                assert newFile != null : "File should not be empty";
                Scanner sc = new Scanner(newFile);
                while (sc.hasNext()) {
                    String[] line = sc.nextLine().split("/");
                    addTaskFromFile(line, tasks);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Adds tasks from file to list.
     *
     * @param arr refers to scanner
     * @param taskList list of tasks
     */
    public static void addTaskFromFile(String[] arr, ListOfTasks taskList) {
        if ((arr[0]).equals("TODO")) {
            Task t = new ToDo(arr[2], "TODO");
            if (arr[1].equals("1")) {
                t.markDone();
            }
            taskList.includeAdditionalTask(t);
        } else if ((arr[0]).equals("DEADLINE")) {
            Deadline d = new Deadline(arr[2], arr[3], "DEADLINE");
            if (arr[1].equals("1")) {
                d.markDone();
            }
            taskList.includeAdditionalTask(d);
        } else if ((arr[0]).equals("EVENT")) {
            Event e = new Event(arr[2], arr[3], "EVENT");
            if (arr[1].equals("1")) {
                e.markDone();
            }
            taskList.includeAdditionalTask(e);
        } else {
            System.out.println("    OOPS!!! I'm sorry, "
                    + "but I don't know what that means :-(");
        }
    }

    /**
     * Updates the list of files due to changes.
     *
     * @param list list of tasks
     */
    public static void updateFile(ArrayList<Task> list) {
        try {
            FileWriter newFileWriter = new FileWriter(filepath);
            newFileWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Task task : list) {
            updateSavedFile(task, task.getType());
        }
    }

    /**
     * Updates a specific file according to changes.
     *
     * @param t list of tasks
     * @param taskType type of task being saved
     */
    public static void updateSavedFile(Task t, String taskType) {
        try {
            File newFile = new File(filepath);
            Scanner sc = new Scanner(newFile);
            FileWriter f = new FileWriter(filepath, true);
            f.write((sc.hasNextLine() ? System.lineSeparator() : "")
                    + taskType + "/"
                    + (t.getStatusIcon() == "[X] " ? "1" : "0")
                    + "/" + t.getInformation()
                    + (taskType.equals("TODO") ? "" : "/" + t.getDetails()));
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
