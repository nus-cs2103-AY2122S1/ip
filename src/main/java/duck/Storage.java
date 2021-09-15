package duck;

import duck.exception.DuckException;
import duck.exception.DuckExceptionType;
import duck.task.Deadline;
import duck.task.Event;
import duck.task.Task;
import duck.task.Todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ArrayList;

/**
 * Deals with loading tasks from, saving tasks to and making changes to tasks on the hard disk.
 */
public class Storage {
    private final String filePath;
    private final File direc;
    private final File duck;
    private final Ui ui;

    /**
     * Constructor for a Storage object.
     *
     * @param fileDirectory Directory containing the hard disk.
     * @param fileName The hard disk.
     */
    public Storage(String fileDirectory, String fileName) {
        filePath = fileDirectory + "/" + fileName;
        ui = new Ui();

        direc = new File(fileDirectory);
        duck = new File(filePath);
    }

    /**
     * Loads the saved tasks from the hard disk to the Duke TaskList.
     *
     * @return An ArrayList of Tasks used to create the TaskList for the current instance of Duck.
     */
    public ArrayList<Task> load() {
        // Initialise task list
        ArrayList<Task> savedTasks = new ArrayList<>(100);

        try {
            // Display initialisation message
            direc.mkdirs();
            duck.createNewFile();

            // Initialise file reader
            BufferedReader reader = new BufferedReader(new FileReader(duck));
            String currLine = reader.readLine();

            // Read through all saved tasks
            while (currLine != null) {
                String[] taskString = currLine.split(" \\| ", 4);
                Task newTask;

                // Current task is a Deadline
                if (taskString[0].equals("D")) {
                    String[] deadlineDetails = taskString[3].split(" ");
                    if (deadlineDetails.length == 1) {
                        newTask = new Deadline(taskString[2], LocalDate.parse(deadlineDetails[0]));
                    } else if (deadlineDetails.length == 2) {
                        newTask = new Deadline(taskString[2],
                                LocalDate.parse(deadlineDetails[0]), LocalTime.parse(deadlineDetails[1]));
                    } else {
                        throw new DuckException(DuckExceptionType.DB_READ);
                    }

                // Current task is an Event
                } else if (taskString[0].equals("E")) {
                    String[] periodDetails = taskString[3].split(" ");
                    if (periodDetails.length == 2) {
                        newTask = new Event(taskString[2],
                                LocalDate.parse(periodDetails[0]), LocalDate.parse(periodDetails[1]));
                    } else if (periodDetails.length == 3) {
                        newTask = new Event(taskString[2], LocalDate.parse(periodDetails[0]),
                                LocalTime.parse(periodDetails[1]), LocalTime.parse(periodDetails[2]));
                    } else if (periodDetails.length == 4) {
                        newTask = new Event(taskString[2],
                                LocalDate.parse(periodDetails[0]), LocalTime.parse(periodDetails[1]),
                                LocalDate.parse(periodDetails[2]), LocalTime.parse(periodDetails[3]));
                    } else {
                        throw new DuckException(DuckExceptionType.DB_READ);
                    }

                // Current task is a Todo
                } else if (taskString[0].equals("T")) {
                    newTask = new Todo(taskString[2]);

                // Current task is in invalid format
                } else {
                    throw new DuckException(DuckExceptionType.DB_READ);
                }

                // Handles whether or not Task is done
                if (Integer.parseInt(taskString[1]) == 1) {
                    newTask.setDone();
                }

                // Add Task to task list, continue with next Task
                savedTasks.add(newTask);
                currLine = reader.readLine();
            }

            reader.close();

        } catch (DuckException e) {
            ui.showException(e);

        } catch (IOException e) {
            ui.showException(new DuckException(DuckExceptionType.DB_LAUNCH));
        }

        return savedTasks;
    }

    /**
     * Adds the given entry to the database (hard disk).
     *
     * @param s A string representation of the task to be added to the database.
     */
    public void addDbEntry(String s) {
        try {
            FileWriter writer = new FileWriter(duck, true);
            writer.write(s + "\n");
            writer.close();

        } catch (IOException e) {
            ui.showException(new DuckException(DuckExceptionType.DB_ADD));
        }
    }

    /**
     * Sets the given entry in the database to 'done'.
     *
     * @param s A string representation of the task in the database to be set to 'done'.
     */
    public void setDbEntryDone(String s) {
        try {
            // Create updated file
            String updatedPath = filePath.split("/")[0] + "/updated.txt";
            File updated = new File(updatedPath);
            updated.createNewFile();

            // Initialise file reader and writer
            FileWriter writer = new FileWriter(updated, true);
            BufferedReader reader = new BufferedReader(new FileReader(duck));
            String currLine = reader.readLine();

            // Read through file, finds and sets specified task to done
            while (currLine != null) {
                if (currLine.equals(s)) {
                    String[] toSetDone = currLine.split(" \\| ", 3);
                    writer.write(toSetDone[0] + " | 1 | " + toSetDone[2] + "\n");
                } else {
                    writer.write(currLine + "\n");
                }
                currLine = reader.readLine();
            }

            // Close writer and reader
            writer.close();
            reader.close();

            // Replace duke with the updated file
            duck.delete();
            updated.renameTo(duck);

        } catch (IOException e) {
            ui.showException(new DuckException(DuckExceptionType.DB_DONE));
        }
    }

    /**
     * Deletes the given entry from the database.
     *
     * @param s A string representation of the task to be deleted from the database.
     */
    public void deleteDbEntry(String s) {
        try {
            // Create updated file
            String updatedPath = filePath.split("/")[0] + "/updated.txt";
            File updated = new File(updatedPath);
            updated.createNewFile();

            // Initialise file reader and writer
            FileWriter writer = new FileWriter(updated, true);
            BufferedReader reader = new BufferedReader(new FileReader(duck));
            String currLine = reader.readLine();

            // Read through file, finds and excludes entry to delete
            while (currLine != null) {
                if (!currLine.equals(s)) {
                    writer.write(currLine + "\n");
                }
                currLine = reader.readLine();
            }

            // Close reader and writer
            writer.close();
            reader.close();

            // Replace duke with the updated file
            duck.delete();
            updated.renameTo(duck);

        } catch (IOException e) {
            ui.showException(new DuckException(DuckExceptionType.DB_DELETE));
        }
    }
}
