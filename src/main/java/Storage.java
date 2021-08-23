import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


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
		// We will populate an ArrayList of tasks
		ArrayList<Task> tasks = new ArrayList<>();

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
					String deadlineTask = details[0].substring(0, details[0].length() - 1).trim();
					String deadline = details[1].substring(1, details[1].length() - 1);
					task = new Deadline(deadlineTask, deadline);
				} else if (line.contains("[E]")) {
					String[] details = lineWithoutBoxes.split("at:");
					String eventTask = details[0].substring(0, details[0].length() - 1).trim();
					String time = details[1].substring(1, details[1].length() - 1);
					task = new Event(eventTask, time);
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
				fw.write(task + "\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
