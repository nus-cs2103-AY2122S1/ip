import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        File file = new File(filePath);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdir();
        }
    }
    public TaskList load() {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            TaskList taskList = new TaskList();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineSplit = line.split(" \\| ");
                for (String s: lineSplit) {
                    System.out.println(s);
                }
                String lineType = lineSplit[0];
                Task newTask;
                switch (lineType) {
                case "T":
                    newTask = new Todo(lineSplit[2]);
                    break;
                case "D":
                    newTask = new Deadline(lineSplit[2], lineSplit[3]);
                    break;
                case "E":
                    newTask = new Event(lineSplit[2], lineSplit[3]);
                    break;
                default:
                    throw new DukeException("Invalid task storage format");
                }
                if (lineSplit[1].equals("1")) {
                    newTask.markAsDone();
                }
                taskList.addTask(newTask);
            }
            scanner.close();
            return taskList;
        } catch (FileNotFoundException e) {
            return new TaskList();
        } catch (DukeException e) {
            System.out.printf("HuAI Liddat!!! %s\n", e);
            return new TaskList();
        }
    }
    public void save(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        File file = new File(filePath);
        try {
            FileWriter writer = new FileWriter(filePath);
            String data = "";
            for (Task task : tasks) {
                int isDone = task.isDone() ? 1 : 0;
                switch (task.getType()) {
                case TODO:
                    data += String.format("T | %d | %s\n",
                            isDone, task.getDescription());
                    break;
                case EVENT:
                    Event event = (Event) task;
                    data += String.format("E | %d | %s | %s\n",
                            isDone, task.getDescription(), event.getDateTime());
                    break;
                case DEADLINE:
                    Deadline deadline = (Deadline) task;
                    data += String.format("D | %d | %s | %s\n",
                            isDone, task.getDescription(), deadline.getDateTime());
                    break;
                }
            }
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
