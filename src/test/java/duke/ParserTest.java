package duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.commands.ToDoCommand;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.ToDo;

public class ParserTest {
    private Parser parser = new Parser(new DumbStorage());
    private String input1 = "";
    private String input2 = "bob";
    private String input3 = "todo playgames";
    @Test
    void parseTestNullInput() {
        Assertions.assertThrows(DukeExceptions.class, ()-> parser.parse(input1));
    }
    @Test
    void parseTestUnknownInput() {
        Assertions.assertThrows(DukeExceptions.class, ()-> parser.parse(input2));
    }
    @Test
    void parseTestToDoInputNoException() {
        Assertions.assertDoesNotThrow(()-> parser.parse(input3));
    }
    @Test
    void parseTestToDoInputReturnsToDo() {
        Object o = new Object();
        ToDoCommand toDoCommand = new ToDoCommand(ToDo.create("t"));
        try {
            o = parser.parse(input3);
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
        }

        Assertions.assertEquals(o.getClass(), toDoCommand.getClass());
    }

}

class DumbStorage extends Storage {
    @Override
    public Task getTask(int index) {
        return ToDo.create("SHOULD NOT REACH HERE");
    }
}
