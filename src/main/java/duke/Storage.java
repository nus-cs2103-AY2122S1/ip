package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File storage;

    public Storage(String filePath) {
        this.filePath = filePath;

        try {
            File storage = new File(filePath);
            // check if directory exists
            File dir = storage.getParentFile();
            if (!dir.exists()) {
                dir.mkdir();
            }
            // check if file exists
            if (!storage.exists()) {
                storage.createNewFile();
            }
            this.storage = storage;
        } catch (IOException e) {
            Ui.showError(e.getMessage());
        }
    }


    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        try {
            Scanner readFile = new Scanner(storage);
            while (readFile.hasNextLine()) {
                String task = readFile.nextLine();
                if (!task.isBlank()) {
                    parseTask(task, tasks);
                }
            }
        } catch (FileNotFoundException e) {
            Ui.showError(e.getMessage());
        }
        return tasks;
    }

    public void parseTask(String str, List<Task> tasks) {
        Scanner sc = new Scanner(str);
        String category = sc.next();
        String input[] = sc.nextLine().substring(5).split("/");
        String des = input[0].strip();
        String time = "";
        if (!category.equals("T") ) {
            time = input[1].strip();
        };

        if ("T".equals(category)) {
            tasks.add(new Todo(des));
        } else if ("D".equals(category)) {
            LocalDate ld = LocalDate.parse(time);
            tasks.add(new Deadline(des, ld));
        } else if ("E".equals(category)) {
            LocalDate ld = LocalDate.parse(time);
            tasks.add(new Event(des, ld));
        }
    }

    public void save(List<Task> tasks) {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            for (Task task : tasks) {
                myWriter.write(task.toStoredString() + "\n");
                System.out.println(task.toStoredString());
            }
            myWriter.close();
        } catch (IOException e) {
            Ui.showError(e.getMessage());
        }
    }

}