package duke.storage;

import duke.operation.*;
import duke.tool.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the Storage class to load and save tasks in file.
 */
public class Storage {
	private final String dir;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm");

	public Storage(String dir) {
		this.dir = dir;
	}

	/**
	 * Loads data from duke.txt.
	 * @return taskList
	 * @throws IOException
	 */
	public ArrayList<Task> loadData() throws IOException {
		ArrayList<Task> taskList = new ArrayList<>();
		try {
			File data = new File(dir);
			Scanner scanner = new Scanner(data);
			while (scanner.hasNext()) {
				String nextLine = scanner.nextLine();
				String[] task = nextLine.split("]");
				boolean isDone = task[1].equals("[X");
				String msg = task[2].trim().split(" ")[0].trim();
				if (task[0].equals("[T")) {
					taskList.add(new ToDo(msg, isDone));
				} else if (task[0].equals("[D")) {
					String time = task[3].trim();
					taskList.add(new Deadline(msg, LocalDateTime.parse(time, formatter), isDone));
				} else if (task[0].equals("[E")) {
					String time = task[3].trim();
					taskList.add(new Event(msg, LocalDateTime.parse(time, formatter), isDone));
				} else {
				}
			}
		} catch (FileNotFoundException e) {
			if (new File("output").mkdir()) {
				System.out.println("folder not exists, creating...");
			} else if (new File("output/duke.txt").createNewFile()){
				System.out.println("file not exists, creating...");
			}
		}
		return taskList;
	}

	/**
	 * Updates task list inside duke.txt.
	 */
	public void updateFile(TaskList storeRoom) {
		try {
			FileWriter fileWriter = new FileWriter(dir, false);
			for (Task task : storeRoom.getTaskList()) {
				fileWriter.write(task.toString());
				fileWriter.write("\n");
			}
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("Unexpected IO error occurred");
		}
	}
}
