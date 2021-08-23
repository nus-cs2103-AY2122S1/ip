package duke;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTaskListTest {

    @Test
    public void parseAndTaskList_addAndMarkDone_eventsCreatedAndMarkedDone() {
        try {
            Scanner testScanner = new Scanner(Paths.get("java","duke","add-markdone-command"));
            Storage storage = new Storage();
            Parser parser = new Parser(new TaskList(storage));
            parser.parse(testScanner);
            assertEquals(storage.getUserInputRecord().get(0).toString(),"[T][X] sleep");
            assertEquals(storage.getUserInputRecord().get(1).toString(),"[D][X] assignment (by: AUGUST 9 2021)");
            assertEquals(storage.getUserInputRecord().get(2).toString(),"[E][X] meeting (at: AUGUST 1 2021)");

        } catch (IOException e) {
            System.out.println("Test file does not exist");
        }
    }

    @Test
    public void parseAndTaskList_delete_eventsDeleted() {
        try {
            Scanner testScanner = new Scanner(Paths.get("java","duke","delete-command"));
            Storage storage = new Storage();
            Parser parser = new Parser(new TaskList(storage));
            parser.parse(testScanner);
            assert(storage.getUserInputRecord().isEmpty());

        } catch (IOException e) {
            System.out.println("Test file does not exist");
        }
    }
}
