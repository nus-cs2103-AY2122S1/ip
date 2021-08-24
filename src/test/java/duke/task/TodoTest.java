package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

  @Test
  void toStringData() {
    Todo todo = new Todo("read book");
    assertEquals("T | 0 | read book", todo.toStringData());
    todo.markAsDone();
    assertEquals("T | 1 | read book", todo.toStringData());
  }

  @Test
  void testToString() {
    Todo todo = new Todo("read book");
    assertEquals("[T][ ] read book", todo.toString());
    todo.markAsDone();
    assertEquals("[T][X] read book", todo.toString());
  }
}