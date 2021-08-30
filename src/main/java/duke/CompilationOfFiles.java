package duke;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;


public class CompilationOfFiles {
    private static String filepath;

    public CompilationOfFiles(String filepath) {
        this.filepath = filepath;
    }

    public static void loadAndSaveFile(ListOfTasks tasks) {
        try {
            Task temp;
            Files.createDirectories(Paths.get("data/"));
            File newFile = new File(filepath);
            if (newFile.createNewFile()) {
                System.out.println("A new file has been created as there are no saved files.");
            } else {
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
                        tasks.includeAdditionalTask( d);
                    } else if ((line[0]).equals("EVENT")) {
                        Event e = new Event(line[2], line[3], "EVENT");
                        if (line[1].equals("1")) {
                            e.markDone();
                        }
                        tasks.includeAdditionalTask(e);
                    } else {
                        System.out.println("    OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void updateFile(ArrayList<Task> list) {
        try {
            FileWriter newFileWriter = new FileWriter(filepath);
            newFileWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Task task : list) {
            updateSavedFile(task,task.getType());
        }
    }

    public static void updateSavedFile(Task t, String taskType) {
        try {
            File newFile = new File(filepath);
            Scanner sc = new Scanner(newFile);
            FileWriter f = new FileWriter(filepath, true);
            f.write((sc.hasNextLine() ? System.lineSeparator() : "") + taskType + "/"
                    + (t.getStatusIcon() == "[X] " ? "1" : "0") + "/" + t.getInformation()
                    + (taskType.equals("TODO") ? "" : "/" + t.getDetails()));
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

