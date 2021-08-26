package duke;

import org.junit.jupiter.api.Test;

public class DukeTest {
    @Test
    public void testTaskList() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todos("test1"));
        taskList.addTask(new Deadlines("test2", Parser.parseDate("2021-09-09")));
        taskList.addTask(new Events("test3", Parser.parseDate("1999-09-09")));

        for(int i = 0; i < 3; i++) {
            System.out.println(taskList.getTask(i).name);
        }

        for(int i = 0; i < 3; i++) {
            taskList.deleteTask(0);
        }
    }

    @Test
    public void testStorage() {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data/test.txt","data", taskList);
        storage.start();
        Duke duke = new Duke();

        String[] test1 = new String[] {"todo", "dsauihdisad"};
        String[] test2 = new String[] {"deadline", "gergsdfgdsgd", "/by", "1999-08-09"};
        String[] test3 = new String[] {"event", "dsasgfdsfdsfad", "dsadsa", "/at", "2021-01-04"};

        duke.createTask(Duke.Tasks.TODO, test1, storage, taskList);
        duke.createTask(Duke.Tasks.DEADLINE, test2, storage, taskList);
        duke.createTask(Duke.Tasks.EVENT, test3, storage, taskList);
    }

}
