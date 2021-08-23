package commands;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandsTest {
  @Test
  public void byeTest() {
    assertEquals(true, new ByeCommand(new ArrayList<>()).isExit());
  }

  @Test
  public void deadlineTest() {
    assertEquals(false, new DeadlineCommand(new ArrayList<>()).isExit());
  }

  @Test
  public void todoTest() {
    assertEquals(false, new ToDoCommand(new ArrayList<>()).isExit());
  }

  @Test
  public void eventTest() {
    assertEquals(false, new EventCommand(new ArrayList<>()).isExit());
  }

  @Test
  public void deleteTest() {
    assertEquals(false, new DeleteCommand(new ArrayList<>()).isExit());
  }

  @Test
  public void doneTest() {
    assertEquals(false, new DoneCommand(new ArrayList<>()).isExit());
  }

  @Test
  public void listTest() {
    assertEquals(false, new ListCommand(new ArrayList<>()).isExit());
  }

  @Test
  public void dueTest() {
    assertEquals(false, new DueCommand(new ArrayList<>()).isExit());
  }
}