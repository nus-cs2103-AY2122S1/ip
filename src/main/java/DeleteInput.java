public class DeleteInput extends InputHandler {

    public DeleteInput(Messages ui, ToDoList toDoList) {
        super(ui, toDoList);
    }

    @Override
    public String handle(String input) throws EmptyDescriptionException {
        if (input.length() == 6) {
            throw new EmptyDescriptionException("error");
        }

        char taskIndex = input.charAt(7);
        int index = Integer.parseInt(String.valueOf(taskIndex));

        Task taskAtIndex = toDoList.getTask(index);

        toDoList.removeTask(index);
        return ui.deletedTask(taskAtIndex);
    }
}
