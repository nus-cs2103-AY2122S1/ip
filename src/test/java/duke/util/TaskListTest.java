package duke.util;

import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

  @Test
  void getTaskList() {
    ArrayList<Task> taskArrayList = new ArrayList<Task>();
    TaskList taskList = new TaskList(taskArrayList);
    assertEquals(taskArrayList, taskList.getTaskList());
    Todo todo = new Todo("read book");
    taskArrayList.add(todo);
    taskList.addTask(todo);
    assertEquals(taskArrayList, taskList.getTaskList());
  }

  @Test
  void getSize() {
    TaskList taskList = new TaskList(new ArrayList<Task>());
    assertEquals(0, taskList.getSize());
    taskList.addTask(new Todo("read book"));
    assertEquals(1, taskList.getSize());
  }

  @Test
  void getTask() {
    TaskList taskList = new TaskList(new ArrayList<Task>());
    Todo todo = new Todo("read book");
    taskList.addTask(todo);
    assertEquals(todo, taskList.getTask(0));
  }

  @Test
  void addTask() {
    TaskList taskList = new TaskList(new ArrayList<Task>());
    Todo todo = new Todo("read book");
    taskList.addTask(todo);
    assertEquals(todo, taskList.getTask(0));
  }

  @Test
  void deleteTask() {
    TaskList taskList = new TaskList(new ArrayList<Task>());
    Todo todo = new Todo("read book");
    taskList.addTask(todo);
    assertEquals(1, taskList.getSize());
    taskList.deleteTask(0);
    assertEquals(0, taskList.getSize());
  }

  @Test
  void testToString() {
    TaskList taskList = new TaskList(new ArrayList<Task>());
    Todo todo = new Todo("read book");
    taskList.addTask(todo);
    Todo todo2 = new Todo("prepare lunch");
    taskList.addTask(todo2);
    assertEquals("     1.[T][ ] read book\n     2.[T][ ] prepare lunch", taskList.toString());
  }
}