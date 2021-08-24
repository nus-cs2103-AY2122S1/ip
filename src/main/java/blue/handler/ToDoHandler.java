package blue.handler;

import blue.BlueException;
import blue.TaskList;
import blue.task.ToDo;

public class ToDoHandler extends CommandHandler {
    public ToDoHandler(TaskList taskList) {
        super(taskList);
    }

    @Override
    public String handle(String input) throws BlueException {
        if (input.contains(" ")) {
            int index = input.indexOf(" ");
            String title = input.substring(index + 1).strip();
            ToDo toDo = new ToDo(title);
            taskList.add(toDo);
            String response = "Got it. I've added this task:\n" + toDo + "\n";
            response += "Now you have " + taskList.size() + " tasks in the list.";
            return response;
        } else {
            throw new BlueException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }
}
