import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
	String path;
	private FileReader fileIn;
	private Scanner sc;
	
	public Storage(String path) throws IOException {
		this.path = path;
		try {
			fileIn = new FileReader(path);
			sc = new Scanner(fileIn);
		} catch (IOException e) {
			File storeFile = new File(path);
			storeFile.getParentFile().mkdirs();
			storeFile.createNewFile();
		}
	}
	
	public ArrayList<Task> load() {
		ArrayList<Task> taskData = new ArrayList<>();
		if (sc == null) {
			return taskData;
		}
		while (sc.hasNextLine()) {
			String ln = sc.nextLine();
			String[] temp = ln.split("\\|");
			switch (temp[0]) {
			case "T":
				taskData.add(new Todo(temp[2].equals("1"), temp[1]));
				break;
			case "D":
				taskData.add(new Deadline(temp[2].equals("1"), temp[1], temp[3]));
				break;
			case "E":
				taskData.add(new Event(temp[2].equals("1"), temp[1], temp[3]));
				break;
			}
		}
		return taskData;
	}
	
	public void backup(TaskList tasklist) throws IOException {
		FileWriter fileOut = new FileWriter(path, false);
		fileOut.write(String.join("\n", tasklist.toBackupFormat()));
		fileOut.flush();
		fileOut.close();
	}
}
