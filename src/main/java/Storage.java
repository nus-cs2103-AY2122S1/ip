import java.io.*;
import java.util.*;

class Storage {
	private final String relativePath = "../../../";
	private final String filePath;
	private File file;

	public Storage(String dirName, String fileName) throws IOException {
		File dir = new File(relativePath + dirName);

		/* case when folder does not exist */
		if (!dir.exists()) {
			dir.mkdir();
		}

		this.filePath = relativePath + dirName + "/" + fileName;
		this.file = new File(filePath);
		/* case when file does not exist */
		this.file.createNewFile();
	}

	public List<Task> loadTasks() throws FileNotFoundException {
		Scanner sc = new Scanner(this.file);
		List<Task> tasks = new ArrayList<>();

		while (sc.hasNext()) {
			String[] tokens = sc.nextLine().split("-");
			String type = tokens[0];
			boolean isDone = tokens[1].equals("1");
			String description = tokens[2];
			Task task = null;

			if (type.equals("T")) {
				task = new ToDo(description);
			} else if (type.equals("D")) {
				task = new Deadline(description, tokens[3]);
			} else if (type.equals("E")) {
				task = new Event(description, tokens[3]);
			} else {
				/* err */
			}

			if (isDone) {
				task.markDone();
			}

			tasks.add(task);
		}

		return tasks;
	}

	public void saveTasks(List<Task> tasks) throws IOException {
		/* re-write the entire contents of the file */
		FileWriter fw = new FileWriter(this.filePath);
		String delim = "-";
		for (Task task : tasks) {
			fw.write(String.join(delim, task.getSaveParameters()));
			fw.write(System.lineSeparator());
		}
		fw.close();
	}
}