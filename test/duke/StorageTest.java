package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @Test
    void convertTaskStringToTask() {
        Task task = new ToDo("hello world!");
        task.done();
        try {
            assertEquals(task, Storage.convertTaskStringToTask("T&&1&&hello world!&&"));
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}