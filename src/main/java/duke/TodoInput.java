package duke;

public class TodoInput extends InputHandler {

    public TodoInput(Ui ui, TaskList taskList) throws DukeException {
        super(ui, taskList);
    }

    public String handle(String input) throws EmptyDescriptionException {
        if (input.length() == 4) {
            throw new EmptyDescriptionException("error");
        }

        String task = input.substring(5);
        Task toDoTask = new Todo(task);

        taskList.add(toDoTask);
        return ui.addedTask(toDoTask);
    }
}
