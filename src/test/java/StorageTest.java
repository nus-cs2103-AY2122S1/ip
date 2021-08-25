import bobbybot.Storage;
import bobbybot.Task;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    //todo: remove dependencies with stub
    @Test
    public void load_loadTestDB_success() throws FileNotFoundException {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Storage storage = new Storage("testDatabase.txt");
        List<Task> tasks = storage.load();
        System.out.println(tasks);
        String actualString = "[[T][X] to this as well, [T][X] and this,"
                + " [D][X] this assignment  (by: Jan 01 2021 10:22), "
                + "[T][ ] show this in db, [T][ ] this, [E][ ] that (at: sunday)]";
        assertEquals(tasks.toString(),actualString);
    }
}
