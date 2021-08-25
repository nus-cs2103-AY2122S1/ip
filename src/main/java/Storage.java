import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

import java.util.ArrayList;

public class Storage {

    private static String filePath;

    public Storage(String location) {
        filePath = location;
    }
    public static void saveData(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks.getTaskList()) {
                writer.write(task.formatSave() + '\n');
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred when saving tasks!");
        }
    }

    public ArrayList<Task> loadData() {
        File folder = new File("./data/");
        if (folder.mkdir()) {
            System.out.println("New data folder is created");
        }

        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("New data file is created");
            } else {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                String line;

                while ((line = reader.readLine()) != null) {
                    tasks.add(convertInputToTask(line));
                }
                System.out.println("Successfully loaded files!");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error! Cannot load file");
        } catch (IOException | InvalidInputException | SecurityException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    private Task convertInputToTask(String line) throws InvalidInputException {
        Task task;
        String[] format = line.split("\\|");
        String taskType = format[0].trim();
        boolean isDone = format[1].trim().equals("1");
        String taskDescription = format[2].trim();

        switch (taskType) {
        case ("T"):
            task = new Todo(taskDescription);
            break;
        case ("E"):
            String at = format[3].trim();
            task = new Event(taskDescription, at);
            break;
        case ("D"):
            String by = format[3].trim();
            task = new Deadline(taskDescription, by);
            break;
        default:
            throw new InvalidInputException("Error converting tasks!");
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}
