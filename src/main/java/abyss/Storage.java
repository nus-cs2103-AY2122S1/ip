package abyss;

import java.io.*;

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

    public TaskList loadTasks() throws IOException, LoadTaskException {
        TaskList tasks = new TaskList();
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
                task = tasks.addToDo(entry[2]);
                if (isDone.equals("1")) {
                    task.markAsDone();
                }
                break;
            case "D":
                task = tasks.addDeadline(entry[2], entry[3]);
                if (isDone.equals("1")) {
                    task.markAsDone();
                }
                break;
            case "E":
                task = tasks.addEvent(entry[2], entry[3]);
                if (isDone.equals("1")) {
                    task.markAsDone();
                }
                break;
            default:
                throw new LoadTaskException("Invalid task in file.");
            }
        }
        reader.close();
        return tasks;
    }

    public void saveTasks(TaskList tasks) throws IOException {
        StringBuffer input = new StringBuffer();
        for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
            Task task = tasks.get(i);
            input.append(task.toFileEntry());
            input.append("\n");
        }
        FileWriter writer = new FileWriter(filePath);
        writer.write(input.toString());
        writer.close();
    }
}
