package duke;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DukeTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void DukeDummyTest(){
        try {
            new Duke("/Users/hungkhoaitay/Duke.Duke/data/duke.txt");
            assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void AddDeadline_StorageTest() {
        Duke duke = new Duke("/Users/hungkhoaitay/Duke.Duke/data/duke.txt");
        Storage storage = duke.storage;
        try {
            Duke.todoList = new ArrayList<>();
            Duke.todoList.add(Task.deadline("return book /by 2022-02-18"));
            storage.clear();
            storage.upload();
            assertEquals(duke.storage.read().get(0), "D , false , return book  , 2022-02-18");
        } catch (DukeException.DukeEmptyTask e) {
            e.printStackTrace();
        } catch (DukeException.DukeEmptyNote e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void DoneCommandTest() {
        Duke duke = new Duke("/Users/hungkhoaitay/Duke.Duke/data/duke.txt");
        Storage storage = duke.storage;
        try {
            Duke.todoList = new ArrayList<>();
            Duke.todoList.add(Task.deadline("return book /by 2022-02-18"));
            Duke.todoList.add(Task.event("book conference /at 2022-02-19"));
            Command.done("1").execute();
            storage.clear();
            storage.upload();
            assertEquals(duke.storage.read().get(0), "D , true , return book  , 2022-02-18");
        } catch (DukeException.DukeEmptyTask e) {
            e.printStackTrace();
        } catch (DukeException.DukeEmptyNote e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void DeleteTodo_StorageTest() {
        Duke duke = new Duke("/Users/hungkhoaitay/Duke.Duke/data/duke.txt");
        Storage storage = duke.storage;
        try {
            Duke.todoList = new ArrayList<>();
            Duke.todoList.add(Task.todo("read book"));
            storage.upload();
            Duke.todoList.clear();
            storage.upload();
            assertEquals(duke.storage.read().isEmpty(), true);
        } catch (DukeException.DukeEmptyTask e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
