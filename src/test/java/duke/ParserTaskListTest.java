package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class ParserTaskListTest {
//todo
//    @Test
//    public void parseAndTaskList_addAndMarkDone_eventsCreatedAndMarkedDone() {
//        Scanner testScanner = new Scanner("todo sleep\n"
//                + "deadline assignment /by 2021-08-09\n"
//                + "event meeting /at 2021-08-01\n"
//                + "done 1\n"
//                + "done 2\n"
//                + "done 3\n"
//                + "bye");
//        Storage storage = new Storage();
//        Parser parser = new Parser(new TaskList(storage));
//        parser.parse(testScanner);
//        assertEquals(storage.getUserInputRecords().get(0).toString(), "[T][X] sleep");
//        assertEquals(storage.getUserInputRecords().get(1).toString(), "[D][X] assignment (by: AUGUST 9 2021)");
//        assertEquals(storage.getUserInputRecords().get(2).toString(), "[E][X] meeting (at: AUGUST 1 2021)");
//    }
//
//    @Test
//    public void parseAndTaskList_delete_eventsDeleted() {
//        Scanner testScanner = new Scanner("todo task 1\n"
//                + "todo task 2\n"
//                + "todo task 3\n"
//                + "delete 3\n"
//                + "delete 2\n"
//                + "bye");
//        Storage storage = new Storage();
//        ArrayList<Task> expectedRecord = new ArrayList<>();
//        expectedRecord.add(new ToDo("task 1"));
//        Parser parser = new Parser(new TaskList(storage));
//        parser.parse(testScanner);
//        assertEquals(storage.getUserInputRecords(), expectedRecord);
//    }
//
//    @Test
//    public void parseAndTaskList_deleteAll_allEventsDeleted() {
//        Scanner testScanner = new Scanner("todo task 1\n"
//                + "todo task 2\n"
//                + "todo task 3\n"
//                + "deleteAll\n"
//                + "bye");
//        Storage storage = new Storage();
//        Parser parser = new Parser(new TaskList(storage));
//        parser.parse(testScanner);
//        assert (storage.getUserInputRecords().isEmpty());
//    }
}
