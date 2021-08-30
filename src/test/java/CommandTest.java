import duke.*;
import duke.command.*;
import duke.tasks.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class CommandTest {
    @Test
    public void testAddCommand() {
        FileController fc = new FileController("data/", "test.txt");
        String contents = fc.readContentsAsString();
        TaskList taskList = new TaskList(contents);
        UI ui = new UI();
        while (taskList.size() > 0) {
            taskList.remove(0);
        }
        Task t = new Todo("HELP!!!!");
        Command c = new AddTaskCommand(t);
        c.execute(taskList, ui, fc);
        Assert.assertEquals(taskList.get(0), t);

    }
}
