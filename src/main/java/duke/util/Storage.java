package duke.util;

import duke.exceptions.UnknownException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> loadStorage() {
        File file = new File(filePath);
        if (!file.exists()) {
            createEmptyFile(file);
            return new ArrayList<>();
        }

        try {
            return readFromFile(file);
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong: cannot find the file");
            return new ArrayList<>();
        }
    }

    public List<Task> readFromFile(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        List<Task> tasks = new ArrayList<>();


        try {
            while (sc.hasNextLine()) {
                String text = sc.nextLine();
                String[] itemDetails = text.split("|");

                Task task;
                String task_type = itemDetails[0];

                if (task_type.equals("T")) {
                    task = new Todo(itemDetails[1], itemDetails[2]);

                } else if (task_type.equals("D")) {
                    task = new Deadline(itemDetails[1], itemDetails[2], itemDetails[3]);

                } else if (task_type.equals("E")) {
                    task = new Event(itemDetails[1], itemDetails[2], itemDetails[3]);

                } else {
                    throw new UnknownException();
                }
                tasks.add(task);
            }
        } catch (UnknownException e) {
            System.out.println(e.getMessage());
        }

        return tasks;
    }

    public void createEmptyFile(File file) {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: cannot create an empty file for Storage.");
        }
    }

    public void writeToFile(List<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : list) {
            fw.write(task.toFileString() + System.lineSeparator());
        }
        fw.close();
    }

    public void saveToStorage(List<Task> tasks) {
        try {
            writeToFile(tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: cannot save to Storage.");
        }
    }
}
