package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * A class which encapsulates interaction with the file,
 * such as saving or editing the file.
 */
public class Storage {

    /**The relative path of the file */
    private String filePath;

    /** The file which will be edited */
    private File taskFile;

    /**
     * A public constructor to initialise the file path
     * to the given one.
     *
     * @param filePath The relative path of the file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskFile = new File(filePath);
    }

    /**
     * Checks if the required directory and file is present.
     * Creates the file and/or the directory if it is
     * not present.
     *
     * @throws DukeException If the file or directory cannot
     *                       be created.
     */
    public void checkFile() throws DukeException {
        File dir = new File("data");
        boolean hasDirectory = dir.exists() && dir.isDirectory();

        if (!hasDirectory) {
            dir.mkdir();
        }

        try {
            taskFile.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Oh No! There seems to be something "
                    + "wrong with the file...");
        }
    }

    /**
     * Loads the current file content and adds the tasks
     * into the TaskList if any.
     *
     * @return An ArrayList containing the tasks.
     * @throws DukeException If the file cannot be found.
     */
    public ArrayList<Task> load() throws DukeException {

        ArrayList<Task> userInput = new ArrayList<>();
        try {
            checkFile();
            Scanner s = new Scanner(taskFile);
            while (s.hasNextLine()) {
                String taskString = s.nextLine();
                String[] splitString = taskString.split(" \\| ");
                Task task;

                switch (splitString[0].trim()) {
                case "T":
                    task = new Todo(splitString[2]);
                    break;
                case "D":
                    task = loadDeadline(splitString[2], splitString[3]);
                    break;
                case "E":
                    task = loadEvent(splitString[2], splitString[3]);
                    break;
                default:
                    throw new DukeException("I cannot recognise the file content :(");
                }
                userInput.add(task);
                checkDoneStatus(task, splitString[1]);
            }
            return userInput;

        } catch (FileNotFoundException e) {
            throw new DukeException("File is not found...");
        }
    }

    /**
     * Loads the string from the file and returns the deadline
     * task which the string represents.
     *
     * @param description Description of the task
     * @param date Deadline of the task
     * @return Deadline task
     */
    public Deadline loadDeadline(String description, String date) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate localDate = LocalDate.parse(date.trim(), df);
        return new Deadline(description.trim(), localDate);
    }

    /**
     * Loads the string from the file and returns the
     * event task which the string represents.
     *
     * @param description Description of the task.
     * @param dateTime Date and time of the task.
     * @return The event task.
     */
    public Event loadEvent(String description, String dateTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM d yyyy , HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime.trim(), dtf);
        return new Event(description.trim(), localDateTime);
    }

    /**
     * Marks task as done if the content from file
     * states that it is done.
     *
     * @param task Task to be checked.
     * @param doneIndicator The done status of the task.
     */
    public void checkDoneStatus(Task task, String doneIndicator) {
        if (doneIndicator.trim().equals("Y")) {
            task.markAsDone();
        }
    }


    /**
     * Returns the string containing the task in
     * a format which can be saved in the file.
     *
     * @param task The task to be saved in the file.
     * @return The string representing the task.
     */
    public String getFileString(Task task) {
        String toAdd = task.getTaskIndicator() + " | "
                + (task.getStatusIcon().equals("X")
                        ? "Y" : "N") + " | " + task.getDescription().trim();

        if (task.getTaskIndicator().equals("D")) {
            Deadline temp = (Deadline) task;
            toAdd += " | " + temp.changeDateFormat().trim();
        } else if (task.getTaskIndicator().equals("E")) {
            Event temp = (Event) task;
            toAdd += " | " + temp.getFormattedAt().trim();
        }
        return toAdd;
    }

    /**
     * Rewrites the entire file according to
     * what is stored in the list.
     *
     * @param taskList The list containing the tasks.
     * @throws DukeException if file cannot be edited.
     */
    public void editFileAll(TaskList taskList) throws DukeException {
        for (int i = 0; i < taskList.size(); i++) {
            Task tempFile = taskList.get(i);
            String toAdd = getFileString(tempFile);
            if (i == 0) {
                editFile(toAdd);
            } else {
                appendToFile(toAdd);
            }
        }
    }

    /**
     * Rewrites the entire file with the string given.
     *
     * @param content The content to be added into the file.
     */
    public void editFile(String content) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("OH NO :( "
                    + "There seems to be something wrong with the file.");
        }
    }

    /**
     * Appends content to the file.
     *
     * @param content The content to be appended.
     * @throws DukeException if file cannot be edited.
     */
    public void appendToFile(String content) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.append(System.lineSeparator() + content);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("OH NO :( There "
                   + "seems to be something wrong with the file. I"
                            + "cannot sync the file with these new changes :(");
        }
    }

}
