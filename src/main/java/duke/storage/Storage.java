package duke.storage;

import duke.operation.Task;
import duke.operation.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This is the Storage class to load and save tasks in file.
 */
public class Storage {
	private Path dir = Paths.get(System.getProperty("user.dir") + "/src/main/java/output");

	/**
	 * Creates duke.txt to contain task list, if not exists.
	 */
	public void createFile() {
		if (!Files.exists(dir)) {
			try {
				Files.createDirectories(dir);
			} catch (IOException e) {
				System.out.println("Unexpected IO error occurred.");
			}
		}
		try {
			String path = dir.toString() + "/duke.txt";
			File file = new File(path);
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("Unexpected IO error occurred.");
		}
	}

	/**
	 * Updates task list inside duke.txt.
	 */
	public void updateFile(TaskList storeRoom) {
		try {
			String path = dir.toString() + "/duke.txt";
			FileWriter fileWriter = new FileWriter(path);
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
