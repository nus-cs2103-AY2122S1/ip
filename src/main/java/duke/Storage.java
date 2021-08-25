package duke;

import exception.DukeException;
import exception.DukeExceptionType;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ArrayList;

public class Storage {
    private final String filePath;
    private final File direc;
    private final File duke;
    private final Ui ui;
    
    public Storage(String fileDirectory, String fileName, Ui ui) {
        this.filePath = fileDirectory + "/" + fileName;
        this.ui = ui;

        direc = new File(fileDirectory);
        duke = new File(filePath);
    }

    /**
     * Loads the saved tasks from the hard disk to the Duke TaskList.
     * 
     * @return An ArrayList of Tasks used to create the TaskList for the current instance of Duke.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> savedTasks = new ArrayList<>(100);

        try {
            ui.showOpenLine();
            ui.showInitialise();

            if (direc.mkdirs()) {
                ui.showNewDataDirectory();
            }
            if (duke.createNewFile()) {
                ui.showNewHardDisk();
            }

            ui.showCloseLine();

            BufferedReader reader = new BufferedReader(new FileReader(duke));
            String currLine = reader.readLine();

            while (currLine != null) {
                String[] taskString = currLine.split(" \\| ", 3);
                Task newTask;

                if (taskString[0].equals("D")) {
                    String[] deadlineDetails = taskString[2].split(" ");
                    if (deadlineDetails.length == 2) {
                        newTask = new Deadline(deadlineDetails[0], LocalDate.parse(deadlineDetails[1]));
                    } else if (deadlineDetails.length == 3) {
                        newTask = new Deadline(deadlineDetails[0],
                                LocalDate.parse(deadlineDetails[1]), LocalTime.parse(deadlineDetails[2]));
                    } else {
                        throw new DukeException(DukeExceptionType.DB_READ);
                    }

                } else if (taskString[0].equals("E")) {
                    String[] periodDetails = taskString[2].split(" ");
                    if (periodDetails.length == 3) {
                        newTask = new Event(periodDetails[0],
                                LocalDate.parse(periodDetails[1]), LocalDate.parse(periodDetails[2]));
                    } else if (periodDetails.length == 4) {
                        newTask = new Event(periodDetails[0], LocalDate.parse(periodDetails[1]),
                                LocalTime.parse(periodDetails[2]), LocalTime.parse(periodDetails[3]));
                    } else if (periodDetails.length == 5) {
                        newTask = new Event(periodDetails[0],
                                LocalDate.parse(periodDetails[1]), LocalTime.parse(periodDetails[2]),
                                LocalDate.parse(periodDetails[3]), LocalTime.parse(periodDetails[4]));
                    } else {
                        throw new DukeException(DukeExceptionType.DB_READ);
                    }


                } else if (taskString[0].equals("T")) {
                    newTask = new Todo(taskString[2]);

                } else {
                    throw new DukeException(DukeExceptionType.DB_READ);
                }

                if (Integer.parseInt(taskString[1]) == 1) {
                    newTask.setDone();
                }

                savedTasks.add(newTask);
                currLine = reader.readLine();
            }

        } catch (DukeException e) {
            ui.showException(e);

        } catch (IOException e) {
            ui.showException(new DukeException(DukeExceptionType.DB_LAUNCH));
        }

        return savedTasks;
    }

    /**
     * Adds the given entry to the database (hard disk).
     * 
     * @param s A string representation of the task to be added to the database.
     */
    public void addDBEntry(String s) {
        try {
            FileWriter writer = new FileWriter(duke, true);
            writer.write(s + "\n");
            writer.close();

        } catch (IOException e) {
            ui.showException(new DukeException(DukeExceptionType.DB_ADD));
        }
    }

    /**
     * Sets the given entry in the database to 'done'.
     *
     * @param s A string representation of the task in the database to be set to 'done'.
     */
    public void setDBEntryDone(String s) {
        try {
            String updatedPath = filePath.split("/")[0] + "/updated.txt";
            File updated = new File(updatedPath);
            updated.createNewFile();

            FileWriter writer = new FileWriter(updated, true);

            BufferedReader reader = new BufferedReader(new FileReader(duke));
            String currLine = reader.readLine();

            while (currLine != null) {
                if (currLine.equals(s)) {
                    String[] toSetDone = currLine.split(" \\| ", 3);
                    writer.write(toSetDone[0] + " | 1 | " + toSetDone[2] + "\n");
                } else {
                    writer.write(currLine + "\n");
                }
                currLine = reader.readLine();
            }

            writer.close();

            duke.delete();
            updated.renameTo(duke);

        } catch (IOException e) {
            ui.showException(new DukeException(DukeExceptionType.DB_DONE));
        }
    }

    /**
     * Deletes the given entry from the database.
     *
     * @param s A string representation of the task to be deleted from the database.
     */
    public void deleteDBEntry(String s) {
        try {
            String updatedPath = filePath.split("/")[0] + "/updated.txt";
            File updated = new File("data/updated.txt");
            updated.createNewFile();

            FileWriter writer = new FileWriter(updated, true);

            BufferedReader reader = new BufferedReader(new FileReader(duke));
            String currLine = reader.readLine();

            while (currLine != null) {
                if (!currLine.equals(s)) {
                    writer.write(currLine + "\n");
                }
                currLine = reader.readLine();
            }

            writer.close();

            duke.delete();
            updated.renameTo(duke);

        } catch (IOException e) {
            ui.showException(new DukeException(DukeExceptionType.DB_DELETE));
        }
    }
}
