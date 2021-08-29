public class DoneInput extends InputHandler {

    public DoneInput(Ui ui, TaskList taskList) throws DukeException {
        super(ui, taskList);
    }

    @Override
    public String handle (String input) throws EmptyDescriptionException {
        if (input.length() == 4) {
            throw new EmptyDescriptionException("error");
        }

        char taskIndex = input.charAt(5);
        int index = Integer.parseInt(String.valueOf(taskIndex));

        Task taskAtIndex = taskList.getTask(index);
        taskAtIndex.markAsDone();

        return ui.doneTask(taskAtIndex);
    }

}
