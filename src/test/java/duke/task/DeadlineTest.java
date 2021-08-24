package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

  @Test
  void toStringData() {
    Deadline deadline = new Deadline("watch lecture", LocalDate.parse("2021-08-24"));
    assertEquals("D | 0 | watch lecture | 2021-08-24", deadline.toStringData());
    deadline.markAsDone();
    assertEquals("D | 1 | watch lecture | 2021-08-24", deadline.toStringData());
  }

  @Test
  void testToString() {
    Deadline deadline = new Deadline("watch lecture", LocalDate.parse("2021-08-24"));
    assertEquals("[D][ ] watch lecture (by: Aug 24 2021)", deadline.toString());
    deadline.markAsDone();
    assertEquals("[D][X] watch lecture (by: Aug 24 2021)", deadline.toString());
  }
}