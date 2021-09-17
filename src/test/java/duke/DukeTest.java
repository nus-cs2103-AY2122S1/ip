package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class DukeTest {
    @Test
    public void addTask_event_success() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        File file = new File("dukeTest.txt");
        file.delete();
        Storage storage = new Storage("dukeTest.txt");
        Parser parser = new Parser(tasks, storage);

        parser.parseInput("event meeting /at 22/2/2022 2222");
        assertEquals("[E][ ] meeting (at: 22 Feb 2022 22:22)", tasks.get(0).toString());
    }

    @Test
    public void removeTask_event_success() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        File file = new File("dukeTest.txt");
        file.delete();
        Storage storage = new Storage("dukeTest.txt");
        Parser parser = new Parser(tasks, storage);

        parser.parseInput("event meeting /at 22/2/2022 2222");
        parser.parseInput("delete 1");
        assertEquals(0, tasks.size());

    }

    @Test
    public void findTask_event_sucess() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        File file = new File("dukeTest.txt");
        file.delete();
        Storage storage = new Storage("dukeTest.txt");
        Parser parser = new Parser(tasks, storage);
        parser.parseInput("event meeting /at 22/2/2022 2222");
        parser.parseInput("event dinner /at 22/2/2022 2222");

        assertEquals("\t1. [E][ ] meeting (at: 22 Feb 2022 22:22)", parser.parseInput("find meeting")[1].toString());
    }
    @Test
    public void deleteTask_event_sucess() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        File file = new File("dukeTest.txt");
        file.delete();
        Storage storage = new Storage("dukeTest.txt");
        Parser parser = new Parser(tasks, storage);
        parser.parseInput("event meeting /at 22/2/2022 2222");
        parser.parseInput("event dinner /at 22/2/2022 2222");
        parser.parseInput("delete 1");
        assertEquals(1, tasks.size());
    }

    @Test
    public void deleteTask_event_fail() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        File file = new File("dukeTest.txt");
        file.delete();
        Storage storage = new Storage("dukeTest.txt");
        Parser parser = new Parser(tasks, storage);
        parser.parseInput("event meeting /at 22/2/2022 2222");
        assertEquals("OOPS!!! Task not found!", parser.parseInput("delete 3")[0]);
    }

    @Test
    public void doneTask_event_sucess() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        File file = new File("dukeTest.txt");
        file.delete();
        Storage storage = new Storage("dukeTest.txt");
        Parser parser = new Parser(tasks, storage);
        parser.parseInput("event meeting /at 22/2/2022 2222");
        parser.parseInput("done 1");
        assertEquals(true, tasks.get(0).isDone);
    }

    @Test
    public void doneTask_event_fail() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        File file = new File("dukeTest.txt");
        file.delete();
        Storage storage = new Storage("dukeTest.txt");
        Parser parser = new Parser(tasks, storage);
        parser.parseInput("event meeting /at 22/2/2022 2222");
        assertEquals("OOPS!!! Task not found!", parser.parseInput("done 3")[0]);
    }

    @Test
    public void snoozeEvent_event_sucess() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        File file = new File("dukeTest.txt");
        file.delete();
        Storage storage = new Storage("dukeTest.txt");
        Parser parser = new Parser(tasks, storage);
        parser.parseInput("event meeting /at 22/2/2022 2222");
        parser.parseInput("event dinner /at 22/2/2022 2222");
        parser.parseInput("snooze 1 /to 23/2/2022 2222");

        assertEquals("\t1. [E][ ] meeting (at: 23 Feb 2022 22:22)", parser.parseInput("find meeting")[1].toString());
    }

    @Test
    public void snoozeEvent_event_fail() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        File file = new File("dukeTest.txt");
        file.delete();
        Storage storage = new Storage("dukeTest.txt");
        Parser parser = new Parser(tasks, storage);
        parser.parseInput("event meeting /at 22/2/2022 2222");
        parser.parseInput("event dinner /at 22/2/2022 2222");
        parser.parseInput("snooze 1 /to 23/2/2022 22:22");

        assertEquals("OOPS!!! Input your date in the following format: DD/MM/YYYY HHMM", parser.parseInput("snooze 1 /to 2-2-2222")[0]);
        assertEquals("OOPS!!! Please enter a valid index.", parser.parseInput("snooze /to 2/2/2222 2222")[0]);
        assertEquals("OOPS!!! Please enter a valid index.", parser.parseInput("snooze wat /to 2/2/2222 2222")[0]);
        assertEquals("OOPS!!! Please enter a valid index.", parser.parseInput("snooze wat /to 2/2/2222 2222")[0]);
        assertEquals("OOPS!!! Please enter a valid index.", parser.parseInput("snooze 3 /to 2/2/2222 2222")[0]);
        assertEquals("OOPS!!! Input your date in the following format: DD/MM/YYYY HHMM", parser.parseInput("snooze 1 /to ")[0]);
        assertEquals("OOPS!!! Input your date in the following format: DD/MM/YYYY HHMM", parser.parseInput("snooze 1 /to")[0]);
    }

    @Test
    public void addDeadline_event_fail() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        File file = new File("dukeTest.txt");
        file.delete();
        Storage storage = new Storage("dukeTest.txt");
        Parser parser = new Parser(tasks, storage);
        assertEquals("OOPS!!! Please follow this format: deadline <description> /by <date (DD/MM/YYYY HHMM)>",
                parser.parseInput("deadline /by 2/2/2022 2222")[0]);
    }


    @Test
    public void addEvent_event_fail() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        File file = new File("dukeTest.txt");
        file.delete();
        Storage storage = new Storage("dukeTest.txt");
        Parser parser = new Parser(tasks, storage);
        assertEquals("OOPS!!! Please follow this format: event <description> /at <date (DD/MM/YYYY HHMM)>",
                parser.parseInput("event /at 2/2/2022 2222")[0]);
    }
    @Test
    public void addInvalidDate_event_sucess() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        File file = new File("dukeTest.txt");
        file.delete();
        Storage storage = new Storage("dukeTest.txt");
        Parser parser = new Parser(tasks, storage);
        assertEquals("OOPS!!! Input your date in the following format: DD/MM/YYYY HHMM",
                parser.parseInput("deadline homework /by 2-2-2022 2222")[0]);
    }
    @Test
    public void addInvalidDate2_event_sucess() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        File file = new File("dukeTest.txt");
        file.delete();
        Storage storage = new Storage("dukeTest.txt");
        Parser parser = new Parser(tasks, storage);
        assertEquals("OOPS!!! Input your date in the following format: DD/MM/YYYY HHMM",
                parser.parseInput("event homework /at 2/2/2022 22:22")[0]);
    }
}
