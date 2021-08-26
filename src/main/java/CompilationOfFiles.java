import java.io.File;
import java.nio.file.Files;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.*;

public class CompilationOfFiles {
    public static void loadAndSaveFile(ListOfTasks xs) {
        try {
            Task temp;
            Files.createDirectories(Paths.get("data/"));
            File newFile = new File("data/duke.txt");
            if (newFile.createNewFile()) {
                System.out.println("A new file has been created as there are no saved files.");
            } else {
                Scanner sc = new Scanner(newFile);
                while (sc.hasNext()) {
                    String[] line = sc.nextLine().split("/");

                    if ((line[0]) == "TODO") {
                        Task t = new ToDo(line[2], "TODO");
                        if (line[1] == "X") {
                            t.isDone();
                        }
                        xs.add(t.toString());
                    } else if ((line[0]) == "DEADLINE") {
                        Deadline d = new Deadline(line[2], line[3], "DEADLINE");
                        if (line[1] == "X") {
                            d.isDone();
                        }
                        xs.addDeadline(d.toString());
                    } else if ((line[0]) == "EVENT") {
                        Event e = new Event(line[2], line[3], "EVENT");
                        if (line[1] == "X") {
                            e.isDone();
                        }
                        xs.addEvent(e.toString());
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
            FileWriter newFileWriter = new FileWriter("data/duke.txt");
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
            File newFile = new File("data/duke.txt");
            Scanner sc = new Scanner(newFile);
            FileWriter f = new FileWriter("data/duke.txt", true);
            f.write((sc.hasNextLine() ? System.lineSeparator() : "") + taskType + "|" + (t.getStatusIcon() == "[X] " ? 1 : 0) + "|" + t.getInformation());
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    }

