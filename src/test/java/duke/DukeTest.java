package duke;

import duke.command.Command;
import duke.command.IncorrectCommand;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DukeTest {
    @Test
    public void parser_parseIncorrectString_incorrectCommand() {
        String input = "hello";
        int expected = new IncorrectCommand().hashCode();
        int actual =  Parser.parse(input).hashCode();
        assertEquals(expected, actual);
    }

    @Test
    public void taskList_addTodo_printOutTodo() {
        TaskList tested = new TaskList();
        Todo todo = new Todo("have dinner");
        tested.add(todo);
        Task actual = tested.get(0);
        assertEquals(todo, actual);
    }

    @Test
    public void doneCommand_doneTask_markAsDone() throws DukeException {
        String filePath = "./data/tasks.txt";
        TaskList tested = new TaskList();
        Todo todo = new Todo("have dinner");
        tested.add(todo);
        Command c = Parser.parse("done 0");
        c.execute(tested, new Ui(), new Storage(filePath) );
        boolean actual = tested.get(0).isDone();
        assertEquals(true, actual);
    }
}