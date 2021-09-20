package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.parser.Parser;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Storage {
    private String filePath;
    private ArrayList<Task> tasks;

    /**
     * Constructor for Storage class. Initialize Storage instance using given filePath.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.tasks = new ArrayList<>();
    }

    /**
     * Load task info from local data.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            File f = new File(filePath);
            Scanner fileInput = new Scanner(f);
            while (fileInput.hasNext()) {
                String data = fileInput.nextLine();
                processFileData(data);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Cannot Load From Data.");
        }
        return this.tasks;
    }

    /**
     * Parse the local data.
     */
    public void processFileData(String data) {
        char taskType = data.charAt(0);
        char status = data.charAt(4);
        Task task;
        String description;
        Parser parser = new Parser(data);

        if (taskType == 'T') {
            description = parser.getFileTask();
            task = new Todo(description);
        } else {
            description = parser.getFileTask();
            LocalDate date = parser.getFileTime();
            if (taskType == 'E') {
                task = new Event(description, date);
            } else {
                task = new Deadline(description, date);
            }
        }

        if (status == '1') {
            task.markDone();
        }
        tasks.add(task);
    }

    /**
     * Save the change in the hard disk.
     */
    public void updateData() {
        try {
            FileWriter fw = new FileWriter(filePath);

            for (Task task: tasks) {
                fw.write(task.formatForFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}
