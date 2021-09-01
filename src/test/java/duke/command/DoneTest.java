package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.dukeexception.NoListException;
import duke.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DoneTest {
    @Test
    public void execute_normalInput_markedAsDone() {
        try {
            Storage storage = new Storage("./testData/file.txt");
            Ui ui = new Ui();
            TaskList tasks = new TaskList();
            tasks.add(new Todo("project log"));
            new Done(1).exec(tasks, ui, storage);
            assertEquals("X", tasks.get(0).getStatusIcon());
        } catch (NoListException e) {
            System.out.println(e.getMessage());
        }
    }
}