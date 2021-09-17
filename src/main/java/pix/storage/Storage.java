package pix.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import pix.Pix;
import pix.exception.PixException;
import pix.exception.PixInvalidDateException;
import pix.exception.PixIoException;
import pix.task.Deadline;
import pix.task.Event;
import pix.task.Task;
import pix.task.TaskList;
import pix.task.Todo;

/**
 * Storage class to manage reading and writing to the text file.
 */
public class Storage {
    private File file;
    private Pix pix;

    /**
     * Constructor for the storage class.
     *
     * @param filePath Path to the file to read.
     * @param pix Instance of Pix.
     */
    public Storage(String filePath, Pix pix) {
        this.file = new File(filePath);
        this.pix = pix;
    }

    /**
     * Loads the text from the file to the task list.
     *
     * @return An arraylist which represents the task list.
     */
    public ArrayList<Task> load() throws PixException {
        assert file.exists() : "Pix.txt does not exist!";
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] strArray = line.split("\\|", 0);
                switch (strArray[0]) {
                case "T":
                    //Add To Do task
                    if (strArray[1].equals("0")) {
                        taskList.add(new Todo(strArray[2], false));
                    } else if (strArray[1].equals("1")) {
                        taskList.add(new Todo(strArray[2], true));
                    } else {
                        throw new PixIoException();
                    }
                    break;
                case "D":
                    //Add Deadline task
                    if (strArray[1].equals("0")) {
                        taskList.add(new Deadline(strArray[2], false,
                                LocalDate.parse(strArray[3])));
                    } else if (strArray[1].equals("1")) {
                        taskList.add(new Deadline(strArray[2], true,
                                LocalDate.parse(strArray[3])));
                    } else {
                        throw new PixIoException();
                    }
                    break;
                case "E":
                    //Add Event task
                    if (strArray[1].equals("0")) {
                        taskList.add(new Event(strArray[2], false,
                                LocalDate.parse(strArray[3])));
                    } else if (strArray[1].equals("1")) {
                        taskList.add(new Event(strArray[2], true,
                                LocalDate.parse(strArray[3])));
                    } else {
                        throw new PixIoException();
                    }
                    break;
                default:
                    throw new PixIoException();
                }
            }
        } catch (PixIoException e) {
            throw new PixIoException();
        } catch (FileNotFoundException e) {
            createNewFile();
        } catch (DateTimeException e) {
            throw new PixInvalidDateException();
        }

        return taskList;
    }

    /**
     * Saves the tasks in the Task List into Pix.txt if the user undoes a command.
     *
     * @param taskList Task list to write the data from.
     */
    public void writeToFile(ArrayList<Task> taskList) throws PixIoException {
        assert file.exists() : "Pix.txt does not exist!";
        try {
            //Clear the task list in the file and rewrite all tasks inside
            FileWriter pixFileWriter = new FileWriter(file);
            pixFileWriter.write("");
            for (Task task: taskList) {
                pixFileWriter.write(task.getSaveName() + System.lineSeparator());
            }
            pixFileWriter.close();
            ArrayList<Task> updatedTaskList = load();
            pix.setTaskList(updatedTaskList);
        } catch (IOException | PixException e) {
            throw new PixIoException();
        }
    }

    /**
     * Writes to file after an undo command was inputted.
     */
    public void undidChange(TaskList taskList) throws PixIoException {
        try {
            writeToFile(taskList.getTaskList());
        } catch (PixException e) {
            throw new PixIoException();
        }
    }

    /**
     * Creates a new file for the data.
     *
     * @throws PixIoException
     */
    // @@author tenebrius1-reused
    private void createNewFile() throws PixIoException {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            throw new PixIoException();
        }
    }
}
