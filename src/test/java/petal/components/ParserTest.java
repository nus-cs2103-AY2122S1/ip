package petal.components;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import petal.Petal;
import petal.command.Command;
import stubs.FormatTest;
import stubs.StorageStub;
import stubs.TaskListStub;


public class ParserTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final FormatTest formatTest = new FormatTest();
    private final Parser parser = new Parser();
    private final Petal petal = new Petal();
    private final Ui ui = new Ui(petal);
    private final TaskListStub taskListStub = new TaskListStub(ui);
    private final StorageStub storageStub = new StorageStub(taskListStub, ui);

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void parseInput_list_matchExpected() {
        Command command = parser.handleInput("list");
        command.execute(taskListStub, ui, storageStub);
        assertEquals(formatTest.formatForTest("No items in list yet!"), outputStream.toString().trim());
    }

    @Test
    public void parseInput_bye_matchExpected() {
        Command command = parser.handleInput("bye");
        command.execute(taskListStub, ui, storageStub);
        assertEquals(formatTest.formatForTest("You're leaving :( I hope you return soon!"),
                outputStream.toString().trim());

    }

    @Test
    public void parseInput_todo_matchExpected() {
        Command command = parser.handleInput("todo run");
        command.execute(taskListStub, ui, storageStub);
        assertEquals("Okay. I've added this task:\n" + "[T][ ] Run" + "\nYou now have 1 task!",
                             outputStream.toString().trim());
    }

    @Test
    public void parseInput_done_matchExpected() {
        Command command = parser.handleInput("done 1");
        command.execute(taskListStub, ui, storageStub);
        String expected = Responses.LINE + "\nYou have completed the task: " + "'"
                                         + "run!"
                                         + "\nI am so happy for you!\n"
                                         + Responses.LINE;
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void parseInput_delete_matchExpected() {
        Command command = parser.handleInput("delete 1");
        command.execute(taskListStub, ui, storageStub);
        String expected = "Okay. I've deleted this task:\n[ ] " + "run" + "\nYou now have 1 task(s)!";
        assertEquals(expected, outputStream.toString().trim());
    }

}
