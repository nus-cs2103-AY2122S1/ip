package duke.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

public class CommandTodoTest {
    String correctFormat = "test01";
    String wrongFormat = "ksjdksd ajsdkajsd";
    ArrayList<String> args = new ArrayList<>();

    @Test
    public void test01() {
        args.add(correctFormat);
        Command testCommandTodo = new CommandTodo(args);
        assertEquals(testCommandTodo.isValidArgument(), true);
        args.clear();
    }

    @Test
    public void test02() {
        args.add(wrongFormat);
        Command testCommandTodo = new CommandTodo(args);
        assertEquals(testCommandTodo.isValidArgument(), false);
        args.clear();
    }


}
