package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void serialize_sequence() {

        String serial = "Task:deadline\n"
                + "\tName:NAME\n"
                + "\tDone:true\n"
                + "\tBy:BY\n";
        Task deadline = new Deadline("NAME", true, "BY");

        assertEquals(
                serial,
                deadline.serialize()
        );
    }

    @Test
    void testToString_done() {
        String toString = "[D][X] NAME(by: BY)";
        Task deadline = new Deadline("NAME", true, "BY");
        assertEquals(
                toString,
                deadline.toString()
        );
    }

    @Test
    void testToString_notDone() {
        String toString = "[D][ ] NAME(by: BY)";
        Task deadline = new Deadline("NAME", false, "BY");
        assertEquals(
                toString,
                deadline.toString()
        );
    }
}
