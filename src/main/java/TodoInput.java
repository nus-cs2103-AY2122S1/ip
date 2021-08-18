package inputs;

public class TodoInput extends InputHandler {

    public TodoInput(Messages ui, ToDoList toDoList) throws DukeException {
        super(ui, toDoList);
    }

    public String handle(String input) throws EmptyDescriptionException {
        if (input.length() == 4) {
            throw new EmptyDescriptionException("error");
        }

        String task = input.substring(5);
        Task toDoTask = new Todo(task);

        toDoList.add(toDoTask);
        return ui.addedTask(toDoTask);
    }
}
