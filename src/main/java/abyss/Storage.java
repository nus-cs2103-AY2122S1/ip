package abyss;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Storage {
    private String filePath;
    private File file;

    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = new File(filePath);
        if (!file.isFile()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    public ArrayList<Task> loadTasks() throws IOException, LoadTaskException {
        ArrayList<Task> tasks = new ArrayList<>();
        FileReader fileReader = new FileReader(filePath);
        BufferedReader reader = new BufferedReader(fileReader);
        String line;
        while ((line = reader.readLine()) != null) {
            String[] entry = line.split(" \\| ", 4);
            String taskType = entry[0];
            String isDone = entry[1];
            Task task;
            switch (taskType) {
            case "T":
                task = new ToDo(entry[2]);
                if (isDone.equals("1")) {
                    task.markAsDone();
                }
                break;
            case "D":
                task = new Deadline(entry[2],  LocalDate.parse(entry[3]));
                if (isDone.equals("1")) {
                    task.markAsDone();
                }
                break;
            case "E":
                task = new Event(entry[2],  LocalDate.parse(entry[3]));
                if (isDone.equals("1")) {
                    task.markAsDone();
                }
                break;
            default:
                throw new LoadTaskException("Invalid task in file.");
            }
            tasks.add(task);
        }
        reader.close();
        return tasks;
    }

    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        StringBuffer input = new StringBuffer();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            input.append(task.toFileEntry());
            input.append("\n");
        }
        FileWriter writer = new FileWriter(filePath);
        writer.write(input.toString());
        writer.close();
    }
}
