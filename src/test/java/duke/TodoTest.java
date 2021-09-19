package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TodoTest {

    @Test
    void setDone() {
        Todo todo = new Todo(" Finish assignment");
        Todo todoDone = new Todo(" Finish assignment", true);
        assertEquals(todoDone.toString(), todo.setDone().toString());
        assertEquals("[T] [X] Finish assignment", todo.setDone().toString());
    }

    @Test
    void testToString() {
        Todo todo = new Todo(" Finish assignment");
        Todo todoDone = new Todo(" Finish assignment", true);
        assertEquals("[T] [ ] Finish assignment", todo.toString());
        assertEquals("[T] [X] Finish assignment", todoDone.toString());
    }
}
