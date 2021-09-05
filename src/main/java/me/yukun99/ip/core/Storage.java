package me.yukun99.ip.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
    private final String outpath;
    private final String savepath;
    private final TaskList taskList;

    /**
     * Constructor for a Storage instance.
     *
     * @param filepath Filepath to use for the current Storage instance.
     * @param taskList List of tasks currently in the bot.
     * @throws IOException If any file could not be fetched.
     */
    public Storage(String filepath, TaskList taskList) throws IOException {
        this.taskList = taskList;
        filepath.replace("\\", "/");
        this.savepath = filepath + "/tasks.txt";
        this.outpath = filepath + "/ACTUAL.txt";
        File previous = new File(outpath);
        if (previous.exists()) {
            previous.delete();
        }
        previous.createNewFile();
    }

    /**
     * Saves reply message from the bot to output file.
     *
     * @param message Reply message from the bot.
     * @throws HelpBotIoException If message could not be written to file.
     */
    public void saveMessage(String message) throws HelpBotIoException {
        try {
            FileWriter output = new FileWriter(outpath, true);
            output.write(message);
            output.close();
        } catch (IOException e) {
            throw new HelpBotIoException(e, outpath);
        }
    }

    /**
     * Loads previously saved tasks from file.
     */
    public void loadTasks() {
        File saved = new File(savepath);
        Scanner savedScanner;
        try {
            savedScanner = new Scanner(saved);
        } catch (FileNotFoundException e) {
            return;
        }
        while (savedScanner.hasNext()) {
            Task task = parseTask(savedScanner.nextLine());
            if (task != null) {
                try {
                    taskList.addTask(task, task.getDate());
                } catch (HelpBotInvalidTaskTypeException e) {
                    taskList.addTask(task, null);
                }
            }
        }
    }

    /**
     * Parses a saved task from file.
     *
     * @param line String representation of a saved task.
     * @return Task from save file.
     */
    private Task parseTask(String line) {
        String[] lineSplit = line.split(":");
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
            File previous = new File(savepath);
            if (!previous.exists()) {
                previous.createNewFile();
            }
            FileWriter output = new FileWriter(savepath, true);
            String strTask = task.saveString();
            output.write(strTask);
            output.close();
        } catch (IOException e) {
            throw new HelpBotIoException(e, savepath);
        }
    }

    /**
     * Saves the updated task list to file.
     *
     * @throws HelpBotIoException If task could not be uploaded.
     */
    public void updateTasks() throws HelpBotIoException {
        try {
            FileWriter output = new FileWriter(savepath);
            String strTaskList = taskList.saveString();
            output.write(strTaskList);
            output.close();
        } catch (IOException e) {
            throw new HelpBotIoException(e, savepath);
        }
    }
}
