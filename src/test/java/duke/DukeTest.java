package duke;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DukeTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void dukeDummyTest() {
        try {
            new Duke("/Users/hungkhoaitay/Duke/data/dukeTest.txt");
            assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void hiTest() {
        Duke duke = new Duke("/Users/hungkhoaitay/Duke/data/dukeTest.txt");
        String input = "hi";
        String expected =
                "--------------------------------------------------\n"
                        + "OOPS!!! I don't understand: hi\n"
                        + "--------------------------------------------------";
        System.out.println(duke.getResponse(input));
        System.out.println(expected);
        System.out.println(Objects.equals(duke.getResponse(input), expected));
        assertTrue(expected.equals(duke.getResponse(input)));
    }

    @Test
    public void addDeadlineTest() {
        Duke duke = new Duke("/Users/hungkhoaitay/Duke/data/dukeTest.txt");
        String expected = "--------------------------------------------------\n"
                + "OOPS!!! I don't understand: hi\n"
                + "--------------------------------------------------";
        assertEquals(duke.getResponse("\"return book /by 2022-02-18\""), expected);
    }

    @Test
    public void doneCommandTest() {
        Duke duke = new Duke("/Users/hungkhoaitay/Duke/data/dukeTest.txt");
        Storage storage = duke.storage;
        try {
            Duke.todoList = new ArrayList<>();
            Duke.todoList.add(Task.deadline("return book /by 2022-02-18"));
            Duke.todoList.add(Task.event("book conference /at 2022-02-19"));
            Command.done("1").execute();
            storage.clear();
            storage.upload();
            assertEquals(duke.storage.read().get(0), "D , true , return book  , 2022-02-18");
            storage.clear();
        } catch (DukeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteTodoTest() {
        Duke duke = new Duke("/Users/hungkhoaitay/Duke/data/dukeTest.txt");
        Storage storage = duke.storage;
        try {
            storage.clear();
            Duke.todoList = new ArrayList<>();
            Duke.todoList.add(Task.todo("read book"));
            storage.upload();
            Duke.todoList.clear();
            storage.upload();
            assertEquals(duke.storage.read().isEmpty(), true);
            storage.clear();
        } catch (DukeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findTest() {
        Duke duke = new Duke("/Users/hungkhoaitay/Duke/data/dukeTest.txt");
        Storage storage = duke.storage;
        try {
            storage.clear();
            Duke.todoList = new ArrayList<>();

            duke.getResponse("todo this");
            duke.getResponse("todo that");
            duke.getResponse("todo these");
            duke.getResponse("todo hasagi");

            String expected = "Here are the matching tasks in your list:\n"
                    + "1. [T] [ ] this\n"
                    + "2. [T] [ ] that\n"
                    + "3. [T] [ ] these";

            assertEquals(expected, duke.getResponse("find th").toString());
            // assertTrue(expected.equals(duke.getResponse("find th")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
