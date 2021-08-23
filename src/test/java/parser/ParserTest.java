package parser;

import commands.*;
import commands.DeleteCommand;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
  @Test
  public void Test1() {
    Command c = new DeleteCommand(new ArrayList<>());
    assertEquals(c.getClass(), new Parser().parse("delete this").getClass());
  }

  @Test
  public void Test2() {
    Command c = new DeleteCommand(new ArrayList<>());
    assertEquals(c.getClass(), new Parser().parse("delete        ").getClass());
  }

  @Test
  public void Test3() {
    Command c = new ByeCommand(new ArrayList<>());
    assertEquals(c.getClass(), new Parser().parse("bye 12").getClass());
  }

  @Test
  public void Test4() {
    Command c = new ByeCommand(new ArrayList<>());
    assertEquals(c.getClass(), new Parser().parse("bye").getClass());
  }

  @Test
  public void Test5() {
    Command c = new DeadlineCommand(new ArrayList<>());
    assertEquals(c.getClass(), new Parser().parse("deadline whatever").getClass());
  }

  @Test
  public void Test6() {
    Command c = new DoneCommand(new ArrayList<>());
    assertEquals(c.getClass(), new Parser().parse("done g!@#!$").getClass());
  }

  @Test
  public void Test7() {
    Command c = new DoneCommand(new ArrayList<>());
    assertEquals(c.getClass(), new Parser().parse("done 1").getClass());
  }

  @Test
  public void Test8() {
    Command c = new DueCommand(new ArrayList<>());
    assertEquals(c.getClass(), new Parser().parse("due yesterday").getClass());
  }

  @Test
  public void Test9() {
    Command c = new EventCommand(new ArrayList<>());
    assertEquals(c.getClass(), new Parser().parse("EvEnT").getClass());
  }

  @Test
  public void Test10() {
    Command c = new ListCommand(new ArrayList<>());
    assertEquals(c.getClass(), new Parser().parse("liST").getClass());
  }

  @Test
  public void Test11() {
    Command c = new ToDoCommand(new ArrayList<>());
    assertEquals(c.getClass(), new Parser().parse("todo blah").getClass());
  }
}
