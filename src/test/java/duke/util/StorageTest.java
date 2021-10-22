package duke.util;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


class StorageTest {

    @Test
    void readData_success() {
        try {
            Storage dukeDB = new Storage("data/duke.txt");
            dukeDB.readData();
        } catch (Exception e) {
            fail("there is error on readData");
        }
    }

    @Test
    void addData_success() {
        try {
            Storage dukeDB = new Storage("data/duke.txt");
            Task ddl = new Deadline("return book", "2/2/2019 1800");
            Task todo = new Todo("buy milk");
            Task event = new Event("meeting", "2/12/2020");
            dukeDB.addData(ddl);
            dukeDB.addData(todo);
            dukeDB.addData(event);
        } catch (Exception e) {
            fail("there is error on addData");
        }
    }

    @Test
    void addData_invalidInput_errorThrow() {
        try {
            Storage dukeDB = new Storage("data/duke.txt");
            Task fakeTask = null;
            dukeDB.addData(fakeTask);

            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains(""));
        }
    }

    @Test
    void doneData_success() {
        try {
            Storage dukeDB = new Storage("data/test.txt");
            Task newTask = new Todo("buy apples");
            dukeDB.addData(newTask);
            dukeDB.doneData(1);
        } catch (Exception e) {
            fail("there is error on doneData");
        }
    }

    @Test
    void deleteData_success() {
        try {
            Storage dukeDB = new Storage("data/test.txt");
            Task newTask = new Todo("buy bananas");
            dukeDB.addData(newTask);
            dukeDB.deleteData(1);
        } catch (Exception e) {
            fail("there is error on doneData");
        }
    }
}
