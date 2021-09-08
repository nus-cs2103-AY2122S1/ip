package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class Storage {
    private File storage;
    private String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
        this.storage = new File(fileName);
    }

    public TaskList load() throws DukeException {
        try {
            this.storage.createNewFile();
        } catch (IOException error) {
            throw new DukeException("ensure you have created a folder named 'data' "
                    + "within the main project directory!");
        }
        try {
            // Initialisation of required objects
            Scanner fileScanner = new Scanner(this.storage);
            TaskList tasklist = new TaskList();

            while (fileScanner.hasNext()) {

                //Precompute scanned data for efficiency
                String fileData = fileScanner.nextLine();
                Parser parser = new Parser(fileData);

                //Compute and generate TaskList based on scanned data
                if (parser.isDone()) {
                    tasklist.done(parser.getSecondPartInInt());
                } else if (parser.isToDo()) {
                    ToDo task = new ToDo(parser.getSecondPart());
                    tasklist.add(task);
                } else if (parser.isDeadline()) {
                    Deadline task = new Deadline(parser.splitSecondPartForDeadline()[0],
                            parser.splitSecondPartForDeadline()[1]);
                    tasklist.add(task);
                } else if (parser.isEvent()) {
                    Event task = new Event(parser.splitSecondPartForEvent()[0],
                            parser.splitSecondPartForEvent()[1]);
                    tasklist.add(task);
                } else if (parser.isDelete()) {
                    tasklist.delete(parser.getSecondPartInInt());
                }
            }

            // Return computed TaskList
            return tasklist;
        } catch (FileNotFoundException e) {
            throw new DukeException("ensure you have created a folder named "
                    + "'data' within the main project directory!");
        }

    }

    private void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(this.fileName, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public void save(String history) throws DukeException{
        try {
            appendToFile(history + System.lineSeparator());
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
