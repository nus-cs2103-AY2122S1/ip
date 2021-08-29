package commands;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("Test")
public class CommandsTest {

  @Test
  public void byeTest() {
    assertTrue(new ByeCommand(new ArrayList<>()).isExit());
  }

  @Test
  public void deadlineTest() {
    assertFalse( new DeadlineCommand(new ArrayList<>()).isExit());
  }

  @Test
  public void todoTest() {
    assertFalse(new ToDoCommand(new ArrayList<>()).isExit());
  }

  @Test
  public void eventTest() {
    assertFalse(new EventCommand(new ArrayList<>()).isExit());
  }

  @Test
  public void deleteTest() {
    assertFalse(new DeleteCommand(new ArrayList<>()).isExit());
  }

  @Test
  public void doneTest() {
    assertFalse(new DoneCommand(new ArrayList<>()).isExit());
  }

  @Test
  public void listTest() {
    assertFalse(new ListCommand(new ArrayList<>()).isExit());
  }

  @Test
  public void dueTest() {
    assertFalse(new DueCommand(new ArrayList<>()).isExit());
  }

  @Test void findTest() {
    assertFalse(new FindCommand(new ArrayList<>()).isExit());
  }
}
