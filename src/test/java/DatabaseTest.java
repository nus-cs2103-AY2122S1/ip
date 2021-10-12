import duke.core.Duke;
import duke.databse.Database;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DatabaseTest {
    @Test
    public void getDataTest(){

        try {
            Database database = new Database("data/todoList2.txt");
            database.getData();
        } catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void writeDataTest(){

        try {
            Database database = new Database("data/todoList2.txt");

            database.writeToDatabase(new Todo("test", false));
            database.writeToDatabase(new Event("test", false, "/at 2020-11-02 4pm - 6pm"));
            database.writeToDatabase(new Deadline("test", false, "/by 2020-11-03 4pm"));

        } catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void updateDataTest(){

        try {
            Database database = new Database("data/todoList2.txt");

            database.updateData(new Todo("test", false), 1);
            database.updateData(new Event("test", false, "/at 2020-11-02 4pm - 6pm"), 1);
            database.updateData(new Deadline("test", false, "/by 2020-11-03 4pm"), 1);

        } catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }

}
