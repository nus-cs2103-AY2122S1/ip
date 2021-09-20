package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;
import duke.Task.*;

public class Storage {
    java.nio.file.Path dirpath;
    Path taskListPath;

    Storage() {
        this.dirpath = Paths.get("data");
        this.taskListPath = Paths.get("data", "DukeTask.txt");
        boolean directoryExists = Files.exists(dirpath);
        boolean fileExist = Files.exists(taskListPath);
        if (!directoryExists) {
            try {
                Files.createDirectory(dirpath);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        if (!fileExist) {
            try {
                Files.createFile(taskListPath);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void saveTasksToStorage(TaskList tasks) {
        try {
            String newFile = tasks.toString();
            this.writeStringToFile(newFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void writeStringToFile(String newFile) throws IOException {
        Files.write(taskListPath, newFile.getBytes());
    }

    public void loadDataToTasks(TaskList tasks) {
        java.nio.file.Path dirpath = Paths.get("data");
        Path taskListPath = Paths.get("data", "DukeTask.txt");
        boolean directoryExists = Files.exists(dirpath);
        boolean fileExist = Files.exists(taskListPath);
        if (!directoryExists) {
            try {
                Files.createDirectory(dirpath);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        if (!fileExist) {
            try {
                Files.createFile(taskListPath);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                File taskList = new File(taskListPath.toString());
                Scanner scanner = new Scanner(taskList);
                while (scanner.hasNext()) {

                    Task currTask = null;
                    String curr = scanner.nextLine();
                    if (curr.equals("")) {
                        continue;
                    }
                    Character tasktype = curr.charAt(1);
                    Character isDone = curr.charAt(4);
                    String desc = curr.substring(6);
                    desc = desc.trim();
                    boolean done = isDone == 'X';
                    if (tasktype == 'T') {
                        currTask = new Todo(desc, done);
                    } else if (tasktype == 'E') {
                        int index = desc.indexOf("at:");
                        int n = desc.length();
                        String date = desc.substring(index + 4, n - 1);
                        currTask = new Event(desc.substring(0, index), done, LocalDate.parse(date));
                    } else if (tasktype == 'D') {
                        int index = desc.indexOf("by:");
                        int n = desc.length();
                        String date = desc.substring(index + 4, n - 1);

                        currTask = new Deadline(desc.substring(0, index), done, LocalDate.parse(date));
                    }
                    tasks.addTask(currTask);
                }

            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
