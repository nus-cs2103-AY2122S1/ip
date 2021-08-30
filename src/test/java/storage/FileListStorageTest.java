package storage;

import configuration.Setting;
import model.Task;
import model.ToDos;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileListStorageTest {
	private static FileListStorage<Task> fileListStorage;
	
	@BeforeAll
	public static void setUp() {
		fileListStorage = new FileListStorage<>(Setting.DATA_DIR_PATH + "/" + Setting.DATA_TASKS);
	}
	
	@Test
	@DisplayName("Adding Array List of Task should work")
	public void writeArrayListToFile_arrayListTask_addedSuccessfully() {
		ArrayList<Task> tasks = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			tasks.add(new ToDos("some dummy event " + i));
		}
		
		assertDoesNotThrow(() -> fileListStorage.writeArrayListToFile(tasks));
	}
	
	@Test
	@DisplayName("Reading Array List of Task should work")
	public void readArrayListFromFile_arrayListTask_sizeGT0() {
		ArrayList<Task> tasks = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			tasks.add(new ToDos("some dummy event " + i));
		}
		
		fileListStorage.writeArrayListToFile(tasks);
		
		assertDoesNotThrow(() -> {
			ArrayList<Task> tempTasks = fileListStorage.readArrayListFromFile();
			assertTrue(tempTasks.size() >= 10);
		});
	}
}
