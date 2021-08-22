package storage;

import exception.PixException;
import exception.PixIOException;
import exception.PixInvalidDateException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;

    /**
     * Constructor for the storage class.
     *
     * @param filePath Path to the file to read.
     */
    public Storage(String filePath) {
        this.file = new File("data/Pix.txt");
    }

    /**
     * Loads the text from the file to the task list.
     *
     * @return An arraylist which represents the task list.
     */
    public ArrayList<Task> load() throws PixException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] strArray = line.split("\\|", 0);
                switch (strArray[0]) {
                case "T":
                    //Add To Do task
                    taskList.add(new Todo(strArray[2], Boolean.parseBoolean(strArray[1])));
                    break;
                case "D":
                    //Add task.Deadline task
                    taskList.add(new Deadline(strArray[2], Boolean.parseBoolean(strArray[1]),
                            LocalDate.parse(strArray[3])));
                    break;
                case "E":
                    //Add task.Event task
                    taskList.add(new Event(strArray[2], Boolean.parseBoolean(strArray[1]),
                            LocalDate.parse(strArray[3])));
                    break;
                default:
                    //throw exception.PixIOException
                    throw new PixIOException();
                }
            }
        } catch (PixIOException e) {
            throw new PixIOException();
        } catch (FileNotFoundException e) {
            throw new PixException("Loading error");
        } catch (DateTimeException e) {
            throw new PixInvalidDateException();
        }

        return taskList;
    }

    /**
     * Saves the tasks in the task.Task List into Pix.txt.
     *
     * @param taskList task.Task list to write the data from.
     */
    public void writeToFile(ArrayList<Task> taskList) throws IOException, PixIOException {
        try {
            //Clear the task list in the file and rewrite all tasks inside
            FileWriter pixFileWriter = new FileWriter(file);
            pixFileWriter.write("");
            for (Task task: taskList) {
                pixFileWriter.write(task.getSaveName() + System.lineSeparator());
            }
            pixFileWriter.close();
        } catch (IOException e) {
            throw new PixIOException();
        }
    }
}