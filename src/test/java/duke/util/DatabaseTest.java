package duke.util;

import static org.testng.AssertJUnit.fail;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


class DatabaseTest {

    @Test
    void readData_success() {
        try {
            Database dukeDB = new Database("data/duke.txt");
            dukeDB.readData();
        } catch (Exception e) {
            fail("there is error on readData");
        }
    }

    @Test
    void addData_success() {
        try {
            Database dukeDB = new Database("data/duke.txt");
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
    void doneData_success() {
        try {
            Database dukeDB = new Database("data/duke.txt");
            dukeDB.doneData(1);
        } catch (Exception e) {
            fail("there is error on doneData");
        }
    }

    @Test
    void deleteData_success() {
        try {
            Database dukeDB = new Database("data/duke.txt");
            dukeDB.deleteData(1);
        } catch (Exception e) {
            fail("there is error on doneData");
        }
    }
}
