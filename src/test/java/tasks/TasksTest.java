package tasks;
import duke.DukeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("Test")
public class TasksTest {

  @Test
  public void ToDoTaskTest1() {
      Task t = new ToDoTask("this");
      assertEquals("[T]", t.getType());
      assertEquals("this", t.getDescription());
      assertEquals("[ ]", t.getStatus());
      assertEquals("T|this|0", t.getSaveFormat());
      t.setIsDone();
      assertEquals("[X]", t.getStatus());
      assertEquals("T|this|1", t.getSaveFormat());
      t.setIsDone();
  }

  @Test
  public void ToDoTaskTest2() {
      assertEquals("[T]", new ToDoTask("this").getType());
  }

  @Test
  public void ToDoTaskTest3() {
      Task t = new ToDoTask("this");
      t.markAsDone();
      assertEquals("[X]", t.getStatus());
  }


  @Test
  public void EventTaskTest1() {
      try {
          Task t = new EventTask("this", "when");
          assertEquals("[E]", t.getType());
          assertEquals("this (at: when)", t.getDescription());
          assertEquals("[ ]", t.getStatus());
          assertEquals("E|this|when|0", t.getSaveFormat());
          t.setIsDone();
          assertEquals("[X]", t.getStatus());
          assertEquals("E|this|when|1", t.getSaveFormat());
          t.setIsDone();
      } catch (DukeException e) {
          System.out.println(e.getMessage());
      }
  }

  @Test
  public void EventTaskTest2() {
    try {
        assertEquals("[E]", new EventTask("this", "when").getType());
    } catch (DukeException e) {
        System.out.println(e.getMessage());
    }
  }

  @Test
  public void EventTaskTest3() {
      try {
          Task t = new EventTask("this", "when");
          t.markAsDone();
          assertEquals("[X]", t.getStatus());
      } catch (DukeException e) {
          System.out.println(e.getMessage());
      }
  }

  @Test
  public void EventTaskTest4() {
      try {
          Task t = new EventTask("this", "2/12/2019 1800");
          assertEquals("this (at: 2 DECEMBER 2019)", t.getDescription());
          assertEquals("2019-12-02", t.getLocalDate().toString());
      } catch (DukeException e) {
          System.out.println(e.getMessage());
      }
  }

  @Test
  public void DeadLineTaskTest1() {
    try {
        Task t = new DeadLineTask("this", "when");
        assertEquals("[D]", t.getType());
        assertEquals("this (by: when)", t.getDescription());
        assertEquals("[ ]", t.getStatus());
        assertEquals("D|this|when|0", t.getSaveFormat());
        t.setIsDone();
        assertEquals("[X]", t.getStatus());
        assertEquals("D|this|when|1", t.getSaveFormat());
        t.setIsDone();
    } catch (DukeException e) {
        System.out.println(e.getMessage());
    }
  }

  @Test
  public void DeadLineTaskTest2() {
      try {
          assertEquals("[D]", new DeadLineTask("this", "when").getType());
      } catch (DukeException e) {
          System.out.println(e.getMessage());
      }
  }

  @Test
  public void DeadLineTaskTest3() {
      try {
          Task t = new DeadLineTask("this", "when");
          t.markAsDone();
          assertEquals("[X]", t.getStatus());
      } catch (DukeException e) {
          System.out.println(e.getMessage());
      }
  }

  @Test
  public void DeadLineTaskTest4() {
      try {
          Task t = new DeadLineTask("this", "2/12/2019 1800");
          assertEquals("this (by: 2 DECEMBER 2019)", t.getDescription());
          assertEquals("2019-12-02", t.getLocalDate().toString());
      } catch (DukeException e) {
          System.out.println(e.getMessage());
      }
  }
}
