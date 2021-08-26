import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    String filePath;
    File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    public List<Task> loadTasks() {
        try {
            Scanner s = new Scanner(file);
            List<Task> result = new ArrayList<>();
            while (s.hasNext()) {
                String toParse = s.nextLine();
                String[] split = toParse.split("//"); //gonna redo the format to something like T//1//read book
                String taskType = split[0];
                String isDone = split[1];
                String description = split[2];
                if (taskType.equals("T")) {
                    Task task = new ToDo(description);
                    if (isDone.equals("1")) {
                        task.markAsDone();
                    }
                    result.add(task);
                } else if (taskType.equals("E")) {
                    Task task = new Event(description, split[3]);
                    if (isDone.equals("1")) {
                        task.markAsDone();
                    }
                    result.add(task);
                } else if (taskType.equals("D")) {
                    Task task = new Deadlines(description, split[3]);
                    if (isDone.equals("1")) {
                        task.markAsDone();
                    }
                    result.add(task);
                }
            }
            Duke.index = result.size();
            return result;
        } catch (FileNotFoundException e) {
            System.out.println("File not found when trying to load tasks");
        }
        return null;
    }
}
