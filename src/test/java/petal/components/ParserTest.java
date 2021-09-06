package petal.components;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import petal.command.Command;
import stubs.StorageStub;
import stubs.TaskListStub;


public class ParserTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    private final Parser parser = new Parser();
    private final TaskListStub taskListStub = new TaskListStub();
    private final StorageStub storageStub = new StorageStub(taskListStub);

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void parseInput_list_listCommand() {
        Command command = parser.handleInput("list");
        assertEquals("1. [T][ ] Go for a run", command.execute(taskListStub, storageStub));
    }

    @Test
    public void parseInput_bye_byeCommand() {
        Command command = parser.handleInput("bye");
        assertEquals(Responses.GOODBYE.toString(), command.execute(taskListStub, storageStub));
    }

    @Test
    public void parseInput_dossiers_dossiersCommand() {
        Command command = parser.handleInput("dossiers");
        assertEquals("No items in list yet!", command.execute(taskListStub, storageStub));
    }

    @Test
    public void parseInput_todo_taskCommand() {
        Command command = parser.handleInput("todo go for a run");
        assertEquals("Okay. I've added this task:\n"
                        + "[T][ ] Go for a run"
                        + "\nYou now have " + "1"
                        + " task!",
                command.execute(taskListStub, storageStub));
    }

    @Test
    public void parseInput_date_dateCommand() {
        Command command = parser.handleInput("date 2/12/2021");
        assertEquals("No tasks on this date!", command.execute(taskListStub, storageStub));
    }

    @Test
    public void parseInput_find_findCommand() {
        Command command = parser.handleInput("find hi");
        assertEquals("No tasks!", command.execute(taskListStub, storageStub));
    }

    @Test
    public void parseInput_archive_archiveCommand() {
        Command command = parser.handleInput("archive 1");
        assertEquals("The task was archived.", command.execute(taskListStub, storageStub));
    }

    @Test
    public void parseInput_unintelligible_unintelligibleCommand() {
        Command command = parser.handleInput("qwndiqwndqwn");
        assertEquals(Responses.UNINTELLIGIBLE.toString(), command.execute(taskListStub, storageStub));
    }

    @Test
    public void correctTimeReturn_time_localTime() {
        assertEquals(LocalTime.parse("18:00"), Parser.parseTime("1800"));
    }

    @Test
    public void correctDateReturn_time_localDate() {
        assertEquals(LocalDate.parse("2021-12-02"), Parser.parseDate("2/12/2021"));
    }

}
