import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import abyss.Ui;
import abyss.command.Parser;
import abyss.exception.InvalidCommandException;
import abyss.exception.InvalidTodoException;
import abyss.task.Task;
import abyss.task.TaskManager;
import abyss.task.ToDo;

public class AbyssTest {
    @Test
    public void addTodo() {
        String description = "CS2103 Weekly Quiz";
        TaskManager tasks = new TaskManager();
        Task todo = new ToDo(description);
        assertEquals(todo.toString(), tasks.addToDo(description).toString());
    }

    @Test
    public void todoCommand_invalid() {
        String err = new InvalidTodoException().toString();
        String output = "";
        try {
            Parser.parseCommand("todo ");
        } catch (InvalidCommandException e) {
            output = e.toString();
        }
        assertEquals(err, output);
    }

    @Test
    public void formatReply() {
        String[] messages = {"Hello.", "This is a test reply."};
        String reply = "";
        reply += messages[0] + "\n";
        reply += messages[1] + "\n";

        assertEquals(reply, Ui.formatReply(messages));
    }
}
