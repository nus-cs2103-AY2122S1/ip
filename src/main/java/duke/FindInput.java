package duke;

public class FindInput extends InputHandler {
    public FindInput(Ui ui, TaskList taskList) {
        super(ui, taskList);
    }

    @Override
    public String handle(String input) throws EmptyDescriptionException {
        if (input.length() == 4) {
            throw new EmptyDescriptionException("error");
        }

        String keyword = input.substring(5);

        return ui.findTask(keyword);
    }
}
