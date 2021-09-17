package test;

import lebron.Lebron;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LebronTest {

    @Test
    void run() {
        Lebron lebron = new Lebron();

        assertEquals("    OOPS! I'm sorry, but I don't know what that means.\n", lebron.run("hello"));
        assertEquals("    OOPS! I'm sorry, but I don't know what that means.\n", lebron.run("whats up"));
        assertEquals("    Bye. Hope to see you again soon!\n", lebron.run("bye"));
        assertEquals("    OOPS! I have no memory of your last action! Try doing something first!",
                lebron.run("undo"));
        assertEquals("    Bye. Hope to see you again soon!\n", lebron.run("bye"));
    }
}