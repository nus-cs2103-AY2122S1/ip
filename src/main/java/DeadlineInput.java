public class DeadlineInput extends InputHandler {

    public DeadlineInput(Messages ui, ToDoList toDoList) throws DukeException {
        super(ui, toDoList);
    }

    @Override
    public String handle(String input) throws EmptyDescriptionException {
        if (input.length() == 8) {
            throw new EmptyDescriptionException("error" );
        }
        int charIndex = input.indexOf("/" );
        int byIndex = charIndex + 4;
        String by = input.substring(byIndex);
        String task = input.substring(9, charIndex - 1);
        Task deadlineTask = new Deadline(task, by);
        toDoList.add(deadlineTask);
        return ui.addedTask(deadlineTask);
    }
}
