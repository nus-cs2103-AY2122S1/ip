package banana;

/**
public class DukeTest {

    @Test
    public void test1() {
        // Checks whether the error message is correct for "blah"
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
    public void test2() {
        // Checks whether the output is correct for a DeadLine
        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.addTask(new ToDo("shower"));
        tasks.addTask(new Deadline("bake cake", "Sunday"));
        tasks.getTask(1).setIsDone();
        assertEquals("[D][X] bake cake (by: Sunday)",
                tasks.getTask(1).toString());
    }


    @Test
    public void test3() {
        // Checks whether the parser can convert the time format
        Parser p = new Parser("2100");
        String time = p.getTime(p.getInput());
        assertEquals("9pm",
                time);
    }

}
*/