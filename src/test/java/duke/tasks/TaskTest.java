package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TaskTest {

    class ConcreteTask extends Task {
        ConcreteTask(String name, boolean isTaskDone) {
            super(name, isTaskDone);
        }

        public String serialize() {
            return "";
        }
    }

    @Test
    void addMsg() {
        String testString = "added: name";
        Task task = new ConcreteTask("name", false);
        assertEquals(
                testString,
                task.addMsg()
        );


    }

    @Test
    void markDone() {
        Task task = new ConcreteTask("name", false);
        task.markDone();
        assertTrue(task.isTaskDone());
    }

    @Test
    void testToString() {
        String testString = "[ ] name";
        Task task = new ConcreteTask("name", false);
        assertEquals(
                testString,
                task.toString()
        );
    }
}
