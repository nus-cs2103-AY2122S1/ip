package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskCompareTest {

    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void dummyTest2() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("abc"));
        tasks.add(new Deadline("def", "tmr"));

        tasks.sort(null);
        for (Task t : tasks) {
            System.out.println(t);
        }

    }

}
