package duke.storage;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

public class Storage {

	Path dirPath;
	Path filePath;

	public Storage(Path dirPath, Path filePath) {
		this.dirPath = dirPath;
		this.filePath = filePath;
	}

    public ArrayList<Task> getTasks() {
		try {
        System.out.println(System.getProperty("user.dir"));
			File taskFile = new File(this.filePath.toString());
			ArrayList<Task> tasks = new ArrayList<>();
			Scanner sc = new Scanner(taskFile);
			while (sc.hasNextLine()) {
				String[] taskArray = sc.nextLine().split(" : ");
				Task task;
				if (taskArray[0].equals("T")) {
					task = new Todo(taskArray[2], taskArray[1].equals("1"));
				} else  if (taskArray[0].equals("D")) {
					task = new Deadline(taskArray[2], taskArray[1].equals("1"), taskArray[3]);
				} else {
					task = new Event(taskArray[2], taskArray[1].equals("1"), taskArray[3]);
				}
				tasks.add(task);
			}
			sc.close();
			return tasks;
		} catch (IOException e) {
			System.out.println(e.toString());
			return new ArrayList<>();
		}
	}

	public void saveTasks(ArrayList<Task> tasks) {
		try {
			File taskDir = new File(this.dirPath.toString());
			if (!taskDir.exists()) {
				taskDir.mkdir();
			}
			File taskFile = new File(this.filePath.toString());
			taskFile.createNewFile();
			FileWriter writer = new FileWriter(this.filePath.toString());
			for (Task task : tasks) {
				writer.write(task.saveString() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
}
