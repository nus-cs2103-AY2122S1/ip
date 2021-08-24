import java.io.IOException;

public class TodoCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String command = ui.getCommand();
        String[] inputValues = command.split(" ");
        if (inputValues.length == 1) {
            //catch empty to-do
            ui.showError("     Error! Description cannot be empty.");
        } else {
            try {
                String description = command.substring(inputValues[0].length() + 1);
                Task toDo = new ToDo(description);
                taskList.add(toDo);
                storage.save(taskList);
            } catch (IOException exception) {
                ui.showSavingError();
            }
        }
    }
}
