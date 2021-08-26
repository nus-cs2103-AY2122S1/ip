package duke;

import duke.commands.ToDoCommand;
import duke.tasks.ToDo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ParserTest {
    Parser parser = new Parser();
    String input1 = "";
    String input2 = "bob";
    String input3 = "todo playgames";
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
        } catch (DukeExceptions e){
            System.out.println(e.getMessage());
        }

        Assertions.assertEquals(o.getClass(), toDoCommand.getClass());
    }

}
