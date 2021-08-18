package inputs;

public class DoneInput extends InputHandler {

    public DoneInput(Messages ui, ToDoList toDoList) throws DukeException {
        super(ui, toDoList);
    }

    @Override
    public String handle (String input) throws EmptyDescriptionException {
        if (input.length() == 4) {
            throw new EmptyDescriptionException("error" );
        }

        char taskIndex = input.charAt(5);
        int index = Integer.parseInt(String.valueOf(taskIndex));

        Task taskAtIndex = toDoList.getTask(index);
        taskAtIndex.markAsDone();

        return ui.doneTask(taskAtIndex);
    }

}
