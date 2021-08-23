public class ToDoCommand extends Command {
    public ToDoCommand() {
        setCommandString("todo");
    }

    @Override
    public void parse(String input) throws DukeException {
        if (input.length() <= getCommandLength()) {
            throw new DukeException("Please input the todo's name!");
        }

        String name = input.substring(getCommandLength()).strip();

        if (name.equals("")) {
            throw new DukeException("Please input the todo's name!");
        }

        Task task = new ToDo(name);
        Duke.taskList.addTask(task);
    }
}
