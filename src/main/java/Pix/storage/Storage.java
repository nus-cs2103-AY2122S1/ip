package Pix.storage;

import Pix.exception.PixException;
import Pix.exception.PixIOException;
import Pix.exception.PixInvalidDateException;
import Pix.task.Deadline;
import Pix.task.Event;
import Pix.task.Task;
import Pix.task.Todo;

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
     * Constructor for the Pix.storage class.
     *
     * @param filePath Path to the file to read.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        System.out.println(file.getAbsolutePath());
    }

    /**
     * Loads the text from the file to the Pix.task list.
     *
     * @return An arraylist which represents the Pix.task list.
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
                    //Add To Do Pix.task
                    taskList.add(new Todo(strArray[2], Boolean.parseBoolean(strArray[1])));
                    break;
                case "D":
                    //Add Pix.task.Deadline Pix.task
                    taskList.add(new Deadline(strArray[2], Boolean.parseBoolean(strArray[1]),
                            LocalDate.parse(strArray[3])));
                    break;
                case "E":
                    //Add Pix.task.Event Pix.task
                    taskList.add(new Event(strArray[2], Boolean.parseBoolean(strArray[1]),
                            LocalDate.parse(strArray[3])));
                    break;
                default:
                    //throw Pix.exception.PixIOException
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
     * Saves the tasks in the Task List into Pix.txt.
     *
     * @param taskList Task list to write the data from.
     */
    public void writeToFile(ArrayList<Task> taskList) throws IOException, PixIOException {
        try {
            //Clear the Pix.task list in the file and rewrite all tasks inside
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