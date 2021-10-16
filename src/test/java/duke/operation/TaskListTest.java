package duke.operation;

import duke.tool.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class TaskListTest {
	private final ArrayList<Task> arrayList = new ArrayList<>(List.of(new ToDo("haha", true)));
	private final TaskList taskList = new TaskList(arrayList);
	@Test
	public void getTaskListTest(){
		assertEquals(arrayList, taskList.getTaskList());
	}


}
