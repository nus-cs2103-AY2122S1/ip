package me.yukun99.ip.core;

import me.yukun99.ip.exceptions.HelpBotDateTimeFormatException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;
import me.yukun99.ip.tasks.Deadline;
import me.yukun99.ip.tasks.Event;
import me.yukun99.ip.tasks.Task;
import me.yukun99.ip.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles storage of tasks and outputting replies.
 */
public class Storage {
	private final String outpath;
	private final String filepath;
	private final TaskList taskList;
	private Scanner scanner;

	/**
	 * Constructor for a Storage instance.
	 *
	 * @param filepath Filepath to use for the current Storage instance.
	 * @param taskList List of tasks currently in the bot.
	 * @throws IOException If any file could not be fetched.
	 */
	public Storage(String filepath, TaskList taskList) throws IOException {
		this.taskList = taskList;
		this.filepath = filepath.replace("\\", "/");
		File input = new File(this.filepath + "/input.txt");
		this.outpath = filepath + "/ACTUAL.txt";
		File previous = new File(this.filepath + "/ACTUAL.txt");
		if (previous.exists()) {
			previous.delete();
		}
		previous.createNewFile();
		try {
			this.scanner = new Scanner(input);
		} catch (FileNotFoundException e) {
			this.scanner = new Scanner(System.in);
		}
	}

	/**
	 * Gets commands from input.txt.
	 *
	 * @return Scanner containing commands from input.txt.
	 */
	public Scanner getInputs() {
		return scanner;
	}

	/**
	 * Saves reply message from the bot to output file.
	 *
	 * @param message Reply message from the bot.
	 * @throws IOException If message could not be written to file.
	 */
	public void saveMessage(String message) throws IOException {
		FileWriter output = new FileWriter(outpath, true);
		output.write(message);
		output.close();
	}

	/**
	 * Loads previously saved tasks from file.
	 */
	public void loadTasks() {
		File saved = new File(filepath + "/tasks.txt");
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
	 */
	public void saveTask(Task task) {
		try {
			File previous = new File(filepath + "/tasks.txt");
			if (!previous.exists()) {
				previous.createNewFile();
			}
			FileWriter output = new FileWriter(filepath + "/tasks.txt", true);
			String strTask = task.saveString();
			output.write(strTask);
			output.close();
		} catch (IOException ignored) {}
	}

	/**
	 * Saves the updated task list to file.
	 */
	public void updateTasks() {
		try {
			FileWriter output = new FileWriter(filepath + "/tasks.txt");
			String strTaskList = taskList.saveString();
			output.write(strTaskList);
			output.close();
		} catch (IOException ignored) {}
	}
}
