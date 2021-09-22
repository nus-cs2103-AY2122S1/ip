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
     * Constructor for path where file is saved.
     *
     * @param filepath path of the file
     */
    public CompilationOfFiles(String filepath) {
        this.filepath = filepath;
    }

    /**
     * This is the main method for loading and saving files.
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
                    if ((line[0]).equals("TODO")) {
                        Task t = new ToDo(line[2], "TODO");
                        if (line[1].equals("1")) {
                            t.markDone();
                        }
                        tasks.includeAdditionalTask(t);
                    } else if ((line[0]).equals("DEADLINE")) {
                        Deadline d = new Deadline(line[2], line[3], "DEADLINE");
                        if (line[1].equals("1")) {
                            d.markDone();
                        }
                        tasks.includeAdditionalTask(d);
                    } else if ((line[0]).equals("EVENT")) {
                        Event e = new Event(line[2], line[3], "EVENT");
                        if (line[1].equals("1")) {
                            e.markDone();
                        }
                        tasks.includeAdditionalTask(e);
                    } else {
                        System.out.println("    OOPS!!! I'm sorry, "
                                + "but I don't know what that means :-(");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This is the method for updating the list of files due to changes.
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
     * This is the method for updating a specific file according to changes.
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

