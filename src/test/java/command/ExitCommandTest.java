package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import pika.command.ExitCommand;
import pika.exception.PikaException;
import pika.task.TaskList;
import pika.ui.Storage;

public class ExitCommandTest {

    @Test
    public void test1() throws PikaException {
        assertFalse(new ExitCommand(null).isRunning());
    }

    @Test
    public void test2() throws PikaException {
        assertEquals("Pika...chuuuuu....", new ExitCommand(null).execute(new TaskList(), new Storage("data")));
    }
}
