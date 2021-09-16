package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.Type;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Storage {
    /** Absolute file path to the folder containing the data file */
    private String filePath;
    /** Name of data file */
    private String fileName;

    /**
     * Constructor for Storage class.
     * @param filePath Relative file path to the folder containing the data file.
     * @param fileName Name of data file.
     */
    public Storage(String filePath, String fileName) {
        String current_dir = new File("").getAbsolutePath();
        this.filePath = current_dir.concat(filePath);
        this.fileName = fileName;
    }

    /**
     * Returns ArrayList of Tasks loaded in from a data file.
     * @return ArrayList of Tasks listed in data file.
     * @throws IOException If reading file or file directory has issues.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File dataFilePath = new File(filePath);
        Scanner dataScanner = null;
        // check if data directory exists
        if (dataFilePath.exists()) {
            File dataFile = new File(filePath.concat(fileName));
            // if data file already exists, load the file's data into tasks, else create a new data file
            if (dataFile.exists()) {
                dataScanner = new Scanner(dataFile);
            } else {
                dataFile.createNewFile();
            }
        } else {
            dataFilePath.mkdirs();
            File dataFile = new File(filePath.concat(fileName));
            dataFile.createNewFile();
        }

        // If there is no data file loaded in
        if (dataScanner == null) {
            return tasks;
        }
        while (dataScanner.hasNextLine()) {
            // taskInfoArr is an array of information for the task, including its type, done-ness, date/time
            String[] taskInfoArr = dataScanner.nextLine().split(",");
            if (taskInfoArr[0].equals("T")) {
                tasks.add(new Todo(taskInfoArr[2]));
            } else if (taskInfoArr[0].equals("D")) {
                tasks.add(new Deadline(taskInfoArr[2],
                        LocalDate.parse(getDateFromString(taskInfoArr[3])),
                        LocalTime.parse(getTimeFromString(taskInfoArr[3]))));
            } else {
                tasks.add(new Event(taskInfoArr[2],
                        LocalDate.parse(getDateFromString(taskInfoArr[3])),
                        LocalTime.parse(getTimeFromString(taskInfoArr[3]))));
            }
            // if task is done, mark as done
            if (taskInfoArr[1].equals("1")) {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        }
        return tasks;
    }

    /**
     * Removes a Task from the data file based on the index of the Task.
     * @param taskIndex Index of Task to be removed.
     */
    public void removeData(int taskIndex) {
        File fileToBeModified = new File(filePath.concat(fileName));
        String newContent = "";
        try {
            Scanner scanner = new Scanner(fileToBeModified);
            int current = 0;
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (current != taskIndex) {
                    newContent = newContent + nextLine + "\n";
                }
                current++;
            }
            FileWriter writer = new FileWriter(fileToBeModified);
            writer.write(newContent);

            try {
                //Closing the resources
                scanner.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Mark a Task as done in the data file.
     * @param taskIndex Index of Task to be marked as done.
     */
    public void markAsDoneData(int taskIndex) {
        File fileToBeModified = new File(filePath.concat(fileName));
        String newContent = "";
        try {
            Scanner scanner = new Scanner(fileToBeModified);
            int current = 0;
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (current == taskIndex) {
                    String[] strArr = nextLine.split(",");
                    newContent = newContent + markTaskStringAsDone(strArr);
                } else {
                    newContent = newContent + nextLine + "\n";
                }
                current++;
            }
            FileWriter writer = new FileWriter(fileToBeModified);
            writer.write(newContent);

            try {
                //Closing the resources
                scanner.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Gets date from the String representation of a task's datetime in the data file.
     * @param init String representation of a task's datetime
     * @return String representation of a task's date only
     */
    private String getDateFromString(String init) {
        return init.substring(0, 10);
    }

    /**
     * Gets time from the String representation of task's datetime in the data file.
     * @param init String representation of a task's datetime
     * @return String representation of a task's time only
     */
    private String getTimeFromString(String init) {
        return init.substring(11);
    }

    /**
     * Add new Task to save into the data file.
     * @param taskName Name of task to be saved.
     * @param type Type of Task (Event, Deadline, or Todo).
     * @param datetime Date and time of Task (if applicable).
     */
    public void newTaskToData(String taskName, Type type, String datetime) {
        try {
            FileWriter dataWriter = new FileWriter(filePath.concat(fileName), true);
            if (type == Type.TODO) {
                dataWriter.write("T,0," + taskName + ", \n");
                dataWriter.close();
            } else if (type == Type.DEADLINE) {
                dataWriter.write("D,0," + taskName + "," + datetime + "\n");
                dataWriter.close();
            } else {
                // Type at this point should be EVENT
                assert type == Type.EVENT;
                dataWriter.write("E,0," + taskName + "," + datetime + "\n");
                dataWriter.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Takes an array of Strings which represents a task and marks it as done
     * @param taskStr Array of Strings which represents a task
     */
    private String markTaskStringAsDone(String[] taskStr) {
        return taskStr[0] + ",1," + taskStr[2] + "," + taskStr[3] + "\n";
    }

    /**
     * Change the datetime of the task specified by taskIndex in the data file.
     * @param datetime Date and time to change to
     */
    public void modifyDateTimeData(String datetime, int taskIndex) {
        File fileToBeModified = new File(filePath.concat(fileName));
        String newContent = "";
        try {
            Scanner scanner = new Scanner(fileToBeModified);
            int current = 0;
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (current != taskIndex) {
                    newContent = newContent + nextLine + "\n";
                } else {
                    newContent = newContent + modifyTaskStringDateTime(nextLine, datetime);
                }
                current++;
            }
            FileWriter writer = new FileWriter(fileToBeModified);
            writer.write(newContent);

            try {
                //Closing the resources
                scanner.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Change the date and time of the String representation of a Task in the data file.
     * @param nextLine String representation of Task
     * @param datetime Date and time to change to
     * @return String representation of Task with datetime modified
     */
    private String modifyTaskStringDateTime(String nextLine, String datetime) {
        String[] strArr = nextLine.split(",");
        return strArr[0] + "," + strArr[1] + "," + strArr[2] + "," + datetime + "\n";
    }
}
