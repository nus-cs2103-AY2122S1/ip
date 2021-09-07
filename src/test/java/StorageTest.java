import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class StorageTest {

    private static TaskList makeTasks() {

        Event event = new Event("Give tuition", LocalDateTime.parse("2021-11-23T00:00"),
            LocalDateTime.parse("2021-11-23T00:00"), false, true);
        Todo todo = new Todo("Feed Momo", true);
        Deadline deadline = new Deadline("Assignment", LocalDateTime.parse("2021-11-23T00:00"));

        ArrayList<Task> taskArrayList = new ArrayList<Task>();
        taskArrayList.add(event);
        taskArrayList.add(todo);
        taskArrayList.add(deadline);

        TaskList tasks = new TaskList(taskArrayList);

        return tasks;
    }


    private static void createTestStore() {
        File dataDir = new File("testData");
        dataDir.mkdirs();
        File testTaskListFile = new File("testData/testTaskList.txt");
        try {
            testTaskListFile.createNewFile();
            FileWriter fw = new FileWriter("testData/testTaskList.txt");
            fw.write("| E |   | X | 2021-11-23T00:00 | 2021-11-23T00:00 | Give tuition\n"
                + "| T | X | Feed Momo\n"
                + "| D |   |   | 2021-11-23T00:00 | Assignment");
            fw.close();

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    @Test
    public void testLoad() throws DukeException {

        TaskList expectedTaskList = makeTasks();
        String filePath = "testData/testTaskList.txt";

        createTestStore();

        Storage testStorage = new Storage(filePath);

        Assertions.assertEquals(true, testStorage.loadTasks().equals(expectedTaskList));


    }

    @Test
    public void testSave() {

        String filePath = "testData2/testTaskList.txt";
        Storage testStorage = new Storage(filePath);
        testStorage.saveTasks(makeTasks());

        File testFile2 = new File("testData2/testTaskList.txt");
        try {
            Scanner scanner = new Scanner(testFile2);
            String expectedText = "| E |   | X | 2021-11-23T00:00 | 2021-11-23T00:00 | Give tuition"
                + "| T | X | Feed Momo"
                + "| D |   |   | 2021-11-23T00:00 | Assignment";
            String allText = "";

            while (scanner.hasNext()) {
                allText += scanner.nextLine();
            }

            Assertions.assertEquals(expectedText, allText);
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } finally {
        }
    }


}
