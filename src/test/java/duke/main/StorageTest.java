package duke.main;

import duke.task.TaskList;
import duke.task.ToDo;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

public class StorageTest {
    private String path = "tasks.txt";
    private Storage storage = new Storage(path);

    private void setupStorage() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(path, "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assert writer != null;
        writer.println("T | false | abcd");
        writer.close();
    }

    @Test
    public void loadTasks_fileExists_loadsPrevious() {
        setupStorage();
        TaskList retrievedTasks = storage.load();
        TaskList toCompareList = new TaskList();
        toCompareList.addTask(new ToDo("abcd"));
        assertEquals("Unable to Load Tasks from Memory", toCompareList, retrievedTasks);
    }

    @Test
    public void updateTask_taskExists_updateSuccessful() throws IOException {
        setupStorage();
        TaskList newTasks = new TaskList();
        newTasks.addTask(new ToDo("different description"));
        storage.updateStorage(newTasks);
        BufferedReader br = new BufferedReader(new FileReader(path));
        assert (br.readLine().equals("T | false | different description"));
    }

}
