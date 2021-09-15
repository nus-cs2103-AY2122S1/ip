package duke;

import duke.ui.UserInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Scanner;

public class Storage {
    private File file;
    public Storage(Path filepath) {
        file = filepath.toFile();
    }

    public TaskList load(UserInterface ui){
        TaskList taskList = new TaskList();
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNext()) {
                String row = fileReader.nextLine();
                String[] tokens = row.split(",");
                if (tokens[0].equals("todo")) {
                    Task task = new ToDo(tokens[1]);
                    taskList.add(task);
                } else if (tokens[0].equals("event")) {
                    Task task = new Event(tokens[1], tokens[2]);
                    taskList.add(task);
                } else if (tokens[0].equals("deadline")) {
                    Task task = new Deadline(tokens[1], tokens[2]);
                    taskList.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            ui.displayError("File could not be found");
        }
        return taskList;
    }

    public void save(UserInterface ui, TaskList taskList) {
        try {
            PrintWriter dukeWriter = new PrintWriter(file);
            for (Task task : taskList.getTasks()) {
                dukeWriter.println(task.toCsvRow());
            }
            dukeWriter.close();
            ui.print("File successfully saved to " + file.getPath());
        } catch (FileNotFoundException e) {
            System.err.println("Error: Could not save file");
        } finally {
            ui.displayFarewell();
        }
    }
}
