import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Represents the storage of tasks. Storage is able to load and save tasks.
 */
public class Storage {
	private final String filepath;
	
	public Storage(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * Load the data from the hard disk.
	 * 
	 * @return ArrayList of tasks
	 * @throws DukeException
	 */
	public ArrayList<Task> loadTasks() throws DukeException {
		ArrayList<Task> tasks = new ArrayList<>();		// We will populate an ArrayList of tasks

		try {
			BufferedReader reader = new BufferedReader(new FileReader(filepath));
			String line;
			Task task;

			// Read lines from file
			while ((line = reader.readLine()) != null) {
				String lineWithoutBoxes = line.substring(7);
				System.out.println(lineWithoutBoxes);
					
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
				}
				tasks.add(task);
			}
			reader.close();
		} catch (IOException e) {
			throw new DukeException("where's my file");
		}
		return tasks;
	}

	public String getTask(String taskDetails) {
		return taskDetails.substring(0, taskDetails.length() - 1).trim();
	}

	public String getDateTime(String dateTimeDetails) {
		return dateTimeDetails.substring(1, dateTimeDetails.length() - 1);
	}
	
	/**
	 * 	Save the tasks to the data file.
	 * 
	 * @param tasks ArrayList of tasks to be saved
	 */
	public void saveTasks(ArrayList<Task> tasks) {
		try {
			FileWriter fw = new FileWriter(filepath);

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
			e.printStackTrace();
		}
	}
}
