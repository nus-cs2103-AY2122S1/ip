public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "TODO";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "Adds a todo task to the list";

    public ToDoCommand(TaskHandler th, Storage str) {
        super(th, str);
    }

    @Override
    public void execute(String cmd) {
        try {
            if (cmd.length() < 6) {
                throw new DukeException(Ui.emptyDescription("todo"));
            } else {
                ToDo toDo = new ToDo(cmd.substring(5));
                taskHandler.addToDo(toDo);
                taskHandler.printNoOfTasks();
                storage.updateFile(taskHandler.formatTaskToSave());
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
