package bloom.app;

import bloom.constant.Message;
import bloom.task.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
	
	private final String FILE_PATH = "src/main/java/bloom/data/bloomDB.txt";
	
	public void saveToDb() {
		try {
			Path path = Paths.get(FILE_PATH);
			
			if (!Files.exists(path)) {
				Files.createFile(path);
			}
			
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < Task.tasks.size(); ++i) {
				stringBuilder.append(Task.tasks.get(i).toDb());
			}
			String tasks = stringBuilder.toString();
			
			byte[] bytes = tasks.getBytes(tasks);
			Files.write(path, bytes);
		} catch (IOException e) {
			System.out.println(Message.EXCEPTION_IO.getMessage());
		}
	}
}
