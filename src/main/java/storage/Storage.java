package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.Duke;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Storage {
    /** Relative file path to the folder containing the data file */
    private String filePath;
    /** Name of data file */
    private String fileName;

    /**
     * Constructor for Storage class.
     * @param filePath Relative file path to the folder containing the data file.
     * @param fileName Name of data file.
     */
    public Storage(String filePath, String fileName) {
        this.filePath = filePath;
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
        // check if data directory exists
        if (dataFilePath.exists()) {
            File dataFile = new File(filePath.concat(fileName));
            // if data file already exists, load the file's data into tasks, else create a new data file
            if (dataFile.exists()) {
                Scanner dataScanner = new Scanner(dataFile);
                while (dataScanner.hasNextLine()) {
                    String[] arr = dataScanner.nextLine().split(",");
                    // if task is a task.Todo
                    if (arr[0].equals("T")) {
                        tasks.add(new Todo(arr[2]));
                        // if task is done, mark as done
                        if (arr[1].equals("1")) {
                            tasks.get(tasks.size() - 1).markAsDone();
                        }
                    } else if (arr[0].equals("D")) {
                        tasks.add(new Deadline(arr[2], LocalDate.parse(arr[3].substring(0, 10)),
                                LocalTime.parse(arr[3].substring(11))));
                        // if task is done, mark as done
                        if (arr[1].equals("1")) {
                            tasks.get(tasks.size() - 1).markAsDone();
                        }
                    } else {
                        tasks.add(new Event(arr[2], LocalDate.parse(arr[3].substring(0, 10)),
                                LocalTime.parse(arr[3].substring(11))));
                        // if task is done, mark as done
                        if (arr[1].equals("1")) {
                            tasks.get(tasks.size() - 1).markAsDone();
                        }
                    }
                }
            } else {
                dataFile.createNewFile();
            }
        } else {
            dataFilePath.mkdirs();
            File dataFile = new File(filePath.concat(fileName));
            dataFile.createNewFile();
        }
        return tasks;
    }

    /**
     * Removes a Task from the data file based on the index of the Task.
     * @param number Index of Task to be removed.
     */
    public void removeData(int number) {
        File fileToBeModified = new File(filePath.concat(fileName));
        String newContent = "";
        try {
            Scanner scanner = new Scanner(fileToBeModified);
            int current = 0;
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (current != number) {
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
     * @param number Index of Task to be marked as done.
     */
    public void markAsDoneData(int number) {
        File fileToBeModified = new File(filePath.concat(fileName));
        String newContent = "";
        try {
            Scanner scanner = new Scanner(fileToBeModified);
            int current = 0;
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (current == number) {
                    String[] arr = nextLine.split(",");
                    newContent = newContent + arr[0] + ",1," + arr[2] + "," + arr[3] + "\n";
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
     * Add new Task to save into the data file.
     * @param taskName Name of task to be saved.
     * @param type Type of Task (Event, Deadline, or Todo).
     * @param time Time of Task (if applicable).
     */
    public void newTaskToData(String taskName, Duke.Type type, String time) {
        try {
            FileWriter dataWriter = new FileWriter(filePath.concat(fileName), true);
            if (type == Duke.Type.TODO) {
                dataWriter.write("T,0," + taskName + ", \n");
                dataWriter.close();
            } else if (type == Duke.Type.DEADLINE) {
                dataWriter.write("D,0," + taskName + "," + time + "\n");
                dataWriter.close();
            } else {
                dataWriter.write("E,0," + taskName + "," + time + "\n");
                dataWriter.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
