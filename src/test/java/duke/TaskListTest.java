package duke;

import org.junit.jupiter.api.Test;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Todos;

public class TaskListTest {
    @Test
    public void testTaskList() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todos("test1"));
        taskList.addTask(new Deadlines("test2", Parser.parseDate("2021-09-09")));
        taskList.addTask(new Events("test3", Parser.parseDate("1999-09-09")));

        for (int i = 0; i < 3; i++) {
            System.out.println(taskList.getTask(i).getName());
        }

        for (int i = 0; i < 3; i++) {
            taskList.deleteTask(0);
        }
    }

}
