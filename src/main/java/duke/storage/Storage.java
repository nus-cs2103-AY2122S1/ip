package duke.storage;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exception.InvalidInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;


/**
 * Represents a Storage object to keep of track changes to the task list.
 *
 * @author: James Kua
 * @version: Duke-Level-8
 */
public class Storage {

    /** String that contains the filePath of the saved TaskList. */
    private static String filePath;

    /**
     * Constructor for a Storage object.
     *
     * @param location filepath of the location to save the file.
     */
    public Storage(String location) {
        filePath = location;
    }

    /**
     * Writes and saves the file to the file location.
     *
     * @param tasks TaskList of tasks.
     */
    public static void saveData(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks.getTaskList()) {
                writer.write(task.formatSave() + '\n');
            }
            writer.close();
        } catch (IOException e) {
            Ui.printMessage("An error occurred when saving tasks!");
        }
    }

    /**
     * Loads saved data (if any) from the file location.
     * If no saved data exists, creates and writes a new file in the file location.
     *
     * @return ArrayList of saved tasks (if any).
     */
    public ArrayList<Task> loadData() {
        File folder = new File("./data/");
        if (folder.mkdir()) {
            Ui.printMessage("New data folder is created");
        }

        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                Ui.printMessage("New data file is created");
            } else {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                String line;

                while ((line = reader.readLine()) != null) {
                    tasks.add(convertInputToTask(line));
                }
                Ui.printMessage("Successfully loaded files!");
            }
        } catch (FileNotFoundException e) {
            Ui.printMessage("Error! Cannot load file");
        } catch (IOException | InvalidInputException | SecurityException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    private Task convertInputToTask(String line) throws InvalidInputException {
        Task task;
        String[] format = line.split("\\|");
        String taskType = format[0].trim();
        boolean isDone = format[1].trim().equals("1");
        String taskDescription = format[2].trim();

        switch (taskType) {
        case ("T"):
            task = new Todo(taskDescription);
            break;
        case ("E"):
            String at = format[3].trim();
            String[] splitAt = at.split(", ", 2);
            LocalDate eventDate;
            LocalTime eventTiming;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                eventDate = LocalDate.parse(splitAt[0].trim(), formatter);
            } catch (DateTimeParseException e) {
                throw new InvalidInputException("Error loading tasks!");
            }
            if (splitAt.length == 1) {
                task = new Event(taskDescription, eventDate);
            } else {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mma");
                    eventTiming = LocalTime.parse(splitAt[1].trim(), formatter);
                } catch (DateTimeParseException e) {
                    throw new InvalidInputException("Error loading tasks!");
                }
                task = new Event(taskDescription, eventDate, eventTiming);
            }
            break;
        case ("D"):
            String by = format[3].trim();
            String[] splitBy = by.split(",", 2);
            LocalDate deadlineDate;
            LocalTime deadlineTiming;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                deadlineDate = LocalDate.parse(splitBy[0].trim(), formatter);
            } catch (DateTimeParseException e) {
                throw new InvalidInputException("Error loading tasks!");
            }
            if (splitBy.length == 1) {
                task = new Deadline(taskDescription, deadlineDate);
            } else {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mma");
                    deadlineTiming = LocalTime.parse(splitBy[1].trim(), formatter);
                } catch (DateTimeParseException e) {
                    throw new InvalidInputException("Error loading tasks!");
                }
                task = new Deadline(taskDescription, deadlineDate, deadlineTiming);
            }
            break;
        default:
            throw new InvalidInputException("Error converting tasks!");
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}
