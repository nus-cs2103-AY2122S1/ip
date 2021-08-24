package parser;

import duke.parser.Parser;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void todoCommandTest() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Parser parser = new Parser("todo Eat Chicken");
        tasks.addTask(tasks, parser.todoCommand(), ui);
        assertEquals("[T][ ] Eat Chicken", tasks.taskNumber(0).toString());
    }

    @Test
    public void deadlineCommandTest() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Parser parser = new Parser("deadline Return Book /by 9/4/2021 1630");
        tasks.addTask(tasks, parser.deadlineCommand(), ui);
        assertEquals("[D][ ] Return Book (by: 9 Apr 2021 04:30 PM)", tasks.taskNumber(0).toString());
    }
}