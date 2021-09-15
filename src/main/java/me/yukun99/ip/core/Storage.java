package me.yukun99.ip.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import me.yukun99.ip.exceptions.HelpBotDateTimeFormatException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;
import me.yukun99.ip.exceptions.HelpBotIoException;
import me.yukun99.ip.tasks.Deadline;
import me.yukun99.ip.tasks.Event;
import me.yukun99.ip.tasks.Task;
import me.yukun99.ip.tasks.ToDo;

/**
 * Handles storage of tasks and outputting replies.
 */
public class Storage {
    private final String outputPath;
    private final String savePath;
    private final String archivePath;
    private final TaskList taskList;

    /**
     * Constructor for a Storage instance.
     *
     * @param taskList List of tasks currently in the bot.
     */
    public Storage(TaskList taskList) {
        this.taskList = taskList;
        this.savePath = "tasks.txt";
        this.outputPath = "ACTUAL.txt";
        this.archivePath = "archive.txt";
        File previous = new File(outputPath);
        if (previous.exists()) {
            previous.delete();
        }
    }

    /**
     * Saves reply message from the bot to output file.
     *
     * @param message Reply message from the bot.
     * @throws HelpBotIoException If message could not be written to file.
     */
    public void saveMessage(String message) throws HelpBotIoException {
        try {
            FileWriter output = getFile(outputPath);
            output.write(message + System.lineSeparator());
            output.close();
        } catch (IOException e) {
            throw new HelpBotIoException(e, outputPath);
        }
    }

    /**
     * Loads previously saved tasks from file.
     */
    public void loadSavedTasks() throws HelpBotIoException {
        File save = new File(savePath);
        loadTasks(save, false);
    }

    /**
     * Loads previously archived tasks from file.
     * Will save archive to current task list and clear archive file.
     *
     * @return List of loaded tasks.
     */
    public List<Task> loadArchivedTasks() throws HelpBotIoException {
        try {
            File archive = new File(archivePath);
            List<Task> loadedTasks = loadTasks(archive, true);
            FileWriter archiveWriter = new FileWriter(archivePath);
            archiveWriter.write("");
            return loadedTasks;
        } catch (IOException e) {
            throw new HelpBotIoException(e, archivePath);
        }
    }

    private List<Task> loadTasks(File file, boolean isArchived) throws HelpBotIoException {
        List<Task> loadedTasks = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            return loadedTasks;
        }
        while (scanner.hasNext()) {
            Task task = parseTask(scanner.nextLine());
            if (task != null) {
                try {
                    taskList.addTask(task, task.getDate());
                } catch (HelpBotInvalidTaskTypeException e) {
                    taskList.addTask(task, null);
                }
            }
            if (isArchived) {
                saveTask(task);
            }
            loadedTasks.add(task);
        }
        return loadedTasks;
    }

    /**
     * Parses a saved task from file.
     *
     * @param line String representation of a saved task.
     * @return Task from save file.
     */
    private Task parseTask(String line) {
        String[] lineSplit = line.split(":", 4);
        try {
            String strType = lineSplit[0];
            String strDone = lineSplit[1];
            String name = lineSplit[2];
            Task task;
            switch (strType) {
            case "T":
                task = new ToDo(name);
                break;
            case "D":
                task = new Deadline(name, lineSplit[3]);
                break;
            case "E":
                task = new Event(name, lineSplit[3]);
                break;
            default:
                return null;
            }
            if (strDone.equals("T")) {
                task.setDone();
            }
            return task;
        } catch (ArrayIndexOutOfBoundsException | HelpBotDateTimeFormatException e) {
            return null;
        }
    }

    /**
     * Saves a task to file.
     *
     * @param task Task to be saved to file.
     * @throws HelpBotIoException If task could not be saved.
     */
    public void saveTask(Task task) throws HelpBotIoException {
        try {
            FileWriter output = getFile(savePath);
            String strTask = task.saveString();
            output.write(strTask);
            output.close();
        } catch (IOException e) {
            throw new HelpBotIoException(e, savePath);
        }
    }

    /**
     * Saves a task to archive file.
     *
     * @param task Task to be archived.
     * @throws HelpBotIoException If task could not be archived.
     */
    public void archiveTask(Task task) throws HelpBotIoException {
        try {
            FileWriter output = getFile(archivePath);
            String strTask = task.saveString();
            output.write(strTask);
            output.close();
        } catch (IOException e) {
            throw new HelpBotIoException(e, archivePath);
        }
    }

    private FileWriter getFile(String path) throws IOException {
        File previous = new File(path);
        if (!previous.exists()) {
            previous.createNewFile();
        }
        return new FileWriter(path, true);
    }

    /**
     * Saves the updated task list to file.
     *
     * @throws HelpBotIoException If task could not be uploaded.
     */
    public void updateTasks() throws HelpBotIoException {
        try {
            FileWriter output = new FileWriter(savePath);
            String strTaskList = taskList.saveString();
            output.write(strTaskList);
            output.close();
        } catch (IOException e) {
            throw new HelpBotIoException(e, savePath);
        }
    }
}
