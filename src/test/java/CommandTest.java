import duke.FileController;
import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class CommandTest {
    @Test
    public void testAddCommand() {
        FileController fc = new FileController("data/", "test.txt");
        String contents = fc.readContentsAsString();
        TaskList taskList = new TaskList(contents);
        while (taskList.size() > 0) {
            taskList.remove(0);
        }
        Task t = new Todo("HELP!!!!");
        Command c = new AddTaskCommand(t);
        c.execute(taskList, fc);
        Assert.assertEquals(taskList.get(0), t);

    }
}
