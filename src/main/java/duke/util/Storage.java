package duke.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.DukeFileNotFoundException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents the storage of tasks. Storage is able to load and save tasks.
 */
public class Storage {
	private final String filePath;

	/**
	 * Constructs a new Storage object with the specified file path.
	 */
	public Storage(String filePath) {
		// https://stackoverflow.com/questions/26239151/
		File file = new File("data/duke.txt");
		try {
			file.getParentFile().mkdirs();	// Create data folder if not exists
			file.createNewFile();			// Create data file if not exists
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		this.filePath = filePath;
	}

	/**
	 * Loads the data from the hard disk.
	 *
	 * @return ArrayList of tasks
	 * @throws DukeException if file cannot be found
	 */
	public ArrayList<Task> loadTasks() throws DukeException {
		ArrayList<Task> tasks = new ArrayList<>(); // We will populate an ArrayList of tasks
		String line;
		Task task;
		final int BOXES_LENGTH = 7;

		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));

			// Read lines from file
			while ((line = reader.readLine()) != null) {
				String lineWithoutBoxes = line.substring(BOXES_LENGTH);

				// If the line contains by -> add a new Deadline
				if (line.contains("[D]")) {
					String[] details = lineWithoutBoxes.split("by:");
					String deadlineTask = getTask(details[0]);
					LocalDate deadline = LocalDate.parse(getDateTime(details[1]));
					task = new Deadline(deadlineTask, deadline);
				} else if (line.contains("[E]")) {
					String[] details = lineWithoutBoxes.split("at:");
					String eventTask = getTask(details[0]);
					String dateTime = getDateTime(details[1]);
					task = new Event(eventTask, dateTime);
				} else {
					task = new ToDo(lineWithoutBoxes);
				}

				// If the line contains "[X]" mark the task as done
				if (line.contains("[X]")) {
					task.setDone(true);
					assert task.isDone();
				}
				tasks.add(task);
			}
			reader.close();
		} catch (IOException e) {
			throw new DukeException("Where's my file");
		}
		return tasks;
	}

	/**
	 * Extract the task name from the task part of the command.
	 *
	 * @param taskDetails the task part of the command
	 * @return the task name
	 */
	public String getTask(String taskDetails) {
		return taskDetails.substring(0, taskDetails.length() - 1).trim();
	}

	/**
	 * Extracts the String datetime from the datetime part of the command.
	 *
	 * @param dateTimeDetails the datetime part of the command
	 * @return the datetime as a string
	 */
	public String getDateTime(String dateTimeDetails) {
		return dateTimeDetails.substring(1, dateTimeDetails.length() - 1);
	}

	/**
	 * Saves the tasks to the data file.
	 *
	 * @param tasks ArrayList of tasks to be saved
	 * @throws DukeFileNotFoundException if file not found
	 */
	public void saveTasks(ArrayList<Task> tasks) throws DukeFileNotFoundException {
		try {
			FileWriter fw = new FileWriter(filePath);

			// Write lines to file
			for (Task task : tasks) {
				if (task instanceof Deadline) {
					Deadline deadline = (Deadline) task;
					fw.write(deadline.toSaveString() + "\n");
				} else {
					fw.write(task + "\n");
				}
			}
			fw.close();
		} catch (IOException e) {
			 throw new DukeFileNotFoundException();
		}
	}
}
