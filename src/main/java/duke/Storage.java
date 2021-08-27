package duke;

import javax.swing.text.DateFormatter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

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
        if (!taskFile.exists()) {
            File dir = new File("data");
            if (!(dir.exists() && dir.isDirectory())) {
                dir.mkdir();
            }
            try {
                taskFile.createNewFile();
            } catch (IOException e) {
                throw new DukeException("");

            } catch (SecurityException e) {

            }
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

                switch (splitString[0].trim()) {

                case "T":
                    userInput.add(new Todo(splitString[2]));
                    break;
                case "D":
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM d yyyy");
                    LocalDate date = LocalDate.parse(splitString[3].trim(), df);
                    userInput.add(new Deadline(splitString[2].trim(), date));
                    break;
                case "E":
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
                    LocalDateTime dateTime = LocalDateTime.parse(splitString[3].trim(), dtf);
                    userInput.add(new Event(splitString[2], dateTime));
                    break;

                }

                if (splitString.length > 2 && splitString[1].equals("Y")) {
                    userInput.get(userInput.size() - 1).markAsDone();
                }
            }
            return userInput;

        } catch (FileNotFoundException e) {
            throw new DukeException("");
        }
    }

    /**
     * Returns the string containing the task in
     * a format which can be saved in the file.
     *
     * @param task The task to be saved in the file.
     * @return The string representing the task.
     */
    public String fileString(Task task) {
        String toAdd = task.taskIndicator() + " | " +
                (task.getStatusIcon().equals("X")
                        ? "Y" : "N") + " | " + task.description.trim();

        if (task.taskIndicator().equals("D")) {
            Deadline temp = (Deadline) task;
            toAdd += " | " + temp.changeDateFormat().trim();
        }  else if(task.taskIndicator().equals("E")) {

            Event temp = (Event) task;
            toAdd += " | " + temp.getAt().trim();
        }
        return toAdd;
    }

    /**
     * Rewrites the entire file according to
     * what is stored in the list.
     *
     * @param taskList The list containing the tasks.
     */
    public void editFileAll(TaskList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            Task tempFile = taskList.get(i);
            String toAdd = fileString(tempFile);
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
    public void editFile(String content) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(System.lineSeparator() + content);
            fw.close();
        } catch (IOException e) {
            System.out.println("OH NO :( "
                    + "There seems to be something wrong with the file.");
        }
    }

    /**
     * Appends content to the file.
     *
     * @param content The content to be appended.
     */
    public void appendToFile(String content) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.append(System.lineSeparator() + content);
            fw.close();
        } catch (IOException e) {
            System.out.println("OH NO :( There "
                   + "seems to be something wrong with the file.");
        }
    }

}
