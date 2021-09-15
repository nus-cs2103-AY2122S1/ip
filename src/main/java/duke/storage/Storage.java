package duke.storage;

import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import duke.task.*;
import duke.exception.DukeException;
import duke.command.Parser;

public class Storage {
    private String filePath;
    private ArrayList<Task> tasks;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.tasks= new ArrayList<>();
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            File f = new File(filePath);
            Scanner fileInput = new Scanner(f);
            while (fileInput.hasNext()) {
                String Data = fileInput.nextLine();
                ProcessFileData(Data);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Cannot Load From Data.");
        }
        return this.tasks;
    }

    public void ProcessFileData(String Data) {
        char taskType = Data.charAt(0);
        char status = Data.charAt(4);
        Task task;
        String description;
        Parser parser = new Parser(Data);

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

    public void updateData() {
        try {
            FileWriter fw = new FileWriter(filePath);

            for (Task task: tasks) {
                fw.write( task.formatForFile()+ "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}
