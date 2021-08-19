package main.java;

public class TodoCommand extends Command {

    protected String input;

    protected Task[] list;

    protected int index;

    TodoCommand(String input, int index, Task[] list) {
        this.input = input;
        this.list = list;
        this.index = index;
    }

    public String reply() throws DukeException {

        String newTask = input.substring(5);
        if (newTask.length() == 0) {
            throw new DukeException("The description of a todo cannot be empty. Please try again!");
        } else {
            list[index] = new TodoTask(newTask);
            return addTask(newTask, 1, index, "");
        }
    }
}
