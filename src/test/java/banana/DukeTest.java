package banana;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void test1(){
        try {
            Parser p = new Parser("blah");
            p.useInput(new TaskList(new ArrayList<>()));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(",
                    e.getMessage());
        }
    }

    @Test
    public void test2(){
        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.addTask(new ToDo("shower"));
        tasks.addTask(new Deadline("bake cake", "Sunday"));
        tasks.getTask(1).setIsDone();
        assertEquals("[D][X] bake cake (by: Sunday)",
                    tasks.getTask(1).toString());
    }

    @Test
    public void test3(){
        Ui ui = new Ui();
        assertEquals("There was an error getting the input",
                ui.showLoadingError());
    }

    @Test
    public void test4(){
        Parser p = new Parser("2100");
        String time = p.getTime(p.getInput());
        assertEquals("9pm",
               time);
    }

}
