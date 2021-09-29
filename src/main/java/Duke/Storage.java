package Duke;

import Duke.task.Task;
import Duke.task.Event;
import Duke.task.Deadline;
import Duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String filePath) {
        File folder = new File(filePath);
        folder.mkdir();
        file = new File(folder, "duke.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner reader = new Scanner(this.file);
            while (reader.hasNextLine()) {
                String task = reader.nextLine();
                tasks.add(loadTask(task));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public static Task loadTask(String task) {
        String[] parsed = task.split(" \\| ");
        String type = parsed[0];
        String description = parsed[2];
        Task loadedTask;
        if (type.equals("T")) {
            loadedTask = new Todo(description);
        } else if (type.equals("D")) {
            loadedTask = new Deadline(description, LocalDate.parse(parsed[3]));
        } else {
            loadedTask = new Event(description, LocalDate.parse(parsed[3]));
        }
        if (parsed[1].equals("1")) {
            loadedTask.markAsDone();
        }
        return loadedTask;
    }

    public void save(TaskList tasks) {
        try {
            Scanner reader = new Scanner(this.file);
            this.file.delete();
            try {
                this.file.createNewFile();
                PrintWriter writer = new PrintWriter(this.file);
                for (Task task : tasks.getTasks()) {
                    writer.println(task.addToFile());
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void doneTask(int n) {
        int count = 0;
        ArrayList<String> tasks = new ArrayList<>();
        try {
            Scanner reader = new Scanner(this.file);
            while (reader.hasNextLine()) {
                count++;
                String data = reader.nextLine();
                if (count == n) {
                    String update = data.substring(0, 4) + '1' + data.substring(5);
                    tasks.add(update);
                } else {
                    tasks.add(data);
                }
            }
            this.file.delete();
            try {
                this.file.createNewFile();
                PrintWriter writer = new PrintWriter(this.file);
                for (String task : tasks) {
                    writer.println(task);
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
