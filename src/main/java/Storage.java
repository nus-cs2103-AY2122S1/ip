import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String filepath;

    private Storage() {}

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    private String parseTaskToFileFormat(Task task) {
        String isDone = task.isDone ? "1" : "0";
        if (task instanceof ToDo) {
            ToDo t = (ToDo) task;
            return "T | " + isDone + " | " + t.description;
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return "D | " + isDone + " | " + d.description + " | " + d.by;
        } else if (task instanceof Event) {
            Event e = (Event) task;
            return "E | " + isDone + " | " + e.description + " | " + e.at;
        } else {
            return "";
        }
    }

    public void createFile() {
        try {
            File f = new File(filepath);
            f.getParentFile().mkdirs();
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendToFile(String text) {
        try {
            FileWriter fw = new FileWriter(filepath, true);
            fw.write(text + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendToFile(Task task) {
        appendToFile(parseTaskToFileFormat(task));
    }

    public void rewriteFileWithTasks(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filepath);
            StringBuilder newFileContent = new StringBuilder();
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                newFileContent.append(parseTaskToFileFormat(task)).append(System.lineSeparator());
            }
            fw.write(newFileContent.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> loadFileContents() {
        try {
            File f = new File(filepath);
            Scanner s = new Scanner(f);

            ArrayList<Task> tasksInFile = new ArrayList<>();
            while (s.hasNext()) {
                String str = s.nextLine();
                String[] words = str.split(" \\| ");
                String taskType = words[0];
                boolean isDone = words[1].equals("1");
                String taskDescription = words[2];

                switch (taskType) {
                case "T":
                    ToDo todo = new ToDo(taskDescription, isDone);
                    tasksInFile.add(todo);
                    break;
                case "D":
                    LocalDateTime by = LocalDateTime.parse(words[3]);
                    Deadline deadline = new Deadline(taskDescription, by, isDone);
                    tasksInFile.add(deadline);
                    break;
                case "E":
                    LocalDateTime at = LocalDateTime.parse(words[3]);
                    Event event = new Event(taskDescription, at, isDone);
                    tasksInFile.add(event);
                    break;
                default:
                    break;
                }
            }
            s.close();

            return tasksInFile;
        } catch (FileNotFoundException e) {
            createFile();
            return new ArrayList<>();
        }
    }
}
