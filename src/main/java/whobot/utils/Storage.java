package whobot.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import whobot.main.WhoBotException;
import whobot.task.Deadline;
import whobot.task.Event;
import whobot.task.Task;
import whobot.task.Todo;

/***
 * Class to Maintain the Storage File
 */
public class Storage {

    private static final String NO_TAG = "Others";

    /** Name of storage file */
    private String filename;

    /** Storage file */
    private final File taskFile;

    /***
     * Constructor for Storage class
     *
     * @param filename Name of the storage file
     * @throws WhoBotException if file could not be opened
     */
    public Storage(String filename) throws WhoBotException {
        assert !filename.isBlank();
        this.filename = filename;
        taskFile = new File(this.filename);
        if (!taskFile.exists()) {
            try {
                if (taskFile.getParentFile() != null && !taskFile.getParentFile().exists()) {
                    taskFile.getParentFile().mkdirs();
                }
                taskFile.createNewFile();
            } catch (IOException e) {
                throw new WhoBotException("Oops, The file to store my data could not be created."
                        + " If you continue, tasks won't be stored permanently.");
            }
        }
    }

    /***
     * Reads Data from File and Adds to Task List
     *
     * @param list the task list to store tasks in
     * @throws WhoBotException if file could not be read
     */
    public void readData(ArrayList<Task> list, HashMap<String, ArrayList<Task>> taggedList) throws WhoBotException {
        try {
            Scanner taskReader = new Scanner(taskFile);
            while (taskReader.hasNextLine()) {
                String[] data = taskReader.nextLine().split(" \\| ");
                if (data[0].equals("T")) {
                    Todo tempTodo = new Todo(data[2]);
                    if (data[1].equals("X")) {
                        tempTodo.markAsDone();
                    }
                    if (data.length == 4) {
                        if (!taggedList.containsKey(data[3])) {
                            taggedList.put(data[3], new ArrayList<>());
                        }
                        taggedList.get(data[3]).add(tempTodo);
                        tempTodo.setTag(data[3]);
                    } else {
                        taggedList.get(NO_TAG).add(tempTodo);
                    }
                    list.add(tempTodo);
                } else if (data[0].equals("D")) {
                    Deadline tempDeadline = new Deadline(data[2]);
                    if (data[1].equals("X")) {
                        tempDeadline.markAsDone();
                    }
                    if (data.length == 4) {
                        if (!taggedList.containsKey(data[3])) {
                            taggedList.put(data[3], new ArrayList<>());
                        }
                        taggedList.get(data[3]).add(tempDeadline);
                        tempDeadline.setTag(data[3]);
                    } else {
                        taggedList.get(NO_TAG).add(tempDeadline);
                    }
                    list.add(tempDeadline);
                } else if (data[0].equals("E")) {
                    Event tempEvent = new Event(data[2]);
                    if (data[1].equals("X")) {
                        tempEvent.markAsDone();
                    }
                    if (data.length == 4) {
                        if (!taggedList.containsKey(data[3])) {
                            taggedList.put(data[3], new ArrayList<>());
                        }
                        taggedList.get(data[3]).add(tempEvent);
                        tempEvent.setTag(data[3]);
                    } else {
                        taggedList.get(NO_TAG).add(tempEvent);
                    }
                    list.add(tempEvent);
                }
            }
        } catch (FileNotFoundException e) {
            throw new WhoBotException("Oops, The file to store my data could not be created."
                    + " If you continue, tasks won't be stored permanently.");
        }
    }

    // Method to Save List to WhoBot's Memory

    /***
     * Saves Task List to File
     *
     *
     * @param list the task list to save
     * @throws WhoBotException if data could not be written
     */
    public void saveMemory(ArrayList<Task> list) throws WhoBotException {
        try {
            FileWriter dataWriter = new FileWriter(taskFile);
            for (Task tempTask : list) {
                String type = tempTask.getType();
                if (type.equals("T")) {
                    dataWriter.write(type + " | " + tempTask.getStatusIcon().charAt(1) + " | " + tempTask.getTask()
                            + (tempTask.hasTag() ? (" | " + tempTask.getTag()) : ""));
                } else if (type.equals("E")) {
                    Event tempEvent = (Event) tempTask;
                    dataWriter.write(type + " | " + tempTask.getStatusIcon().charAt(1) + " | " + tempTask.getTask()
                            + " /at " + tempEvent.getTiming().format(DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"))
                            + (tempTask.hasTag() ? (" | " + tempTask.getTag()) : ""));
                } else if (type.equals("D")) {
                    Deadline tempDeadline = (Deadline) tempTask;
                    dataWriter.write(type + " | " + tempTask.getStatusIcon().charAt(1) + " | " + tempTask.getTask()
                            + " /by "
                            + (tempDeadline.hasTime()
                                    ? tempDeadline.getDeadline().format(DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"))
                                    : tempDeadline.getDeadline().format(DateTimeFormatter.ofPattern("d/M/yyyy")))
                            + (tempTask.hasTag() ? (" | " + tempTask.getTag()) : ""));
                }
                dataWriter.write("\n");
            }
            dataWriter.close();
        } catch (IOException e) {
            throw new WhoBotException("Oops, The file to store my data could not be created."
                    + " If you continue, tasks won't be stored permanently.");
        }
    }
}
