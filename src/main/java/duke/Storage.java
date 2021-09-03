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
            throw new DukeException("ensure you have created a folder named 'data' within the main project directory!");
        }
        try {
            Scanner fileScanner = new Scanner(this.storage);
            TaskList tasklist = new TaskList();

            while (fileScanner.hasNext()) {
                String fileData = fileScanner.nextLine();
                Parser parser = new Parser(fileData);

                if (parser.isDone()) {
                    tasklist.done(parser.secondPartInInt());
                } else if (parser.isToDo()) {
                    ToDo task = new ToDo(parser.secondPart());
                    tasklist.add(task);
                } else if (parser.isDeadline()) {
                    Deadline task = new Deadline(parser.deadline()[0], parser.deadline()[1]);
                    tasklist.add(task);
                } else if (parser.isEvent()) {
                    Event task = new Event(parser.event()[0], parser.event()[1]);
                    tasklist.add(task);
                } else if (parser.isDelete()) {
                    tasklist.delete(parser.secondPartInInt());
                }
            }

            return tasklist;
        } catch (FileNotFoundException e) {
            throw new DukeException("ensure you have created a folder named 'data' within the main project directory!");
        }

        //load contents into TaskList
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
