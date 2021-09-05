public class ListCommand extends Command {

    public ListCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String list = "";
        for (int i = 0; i < tasks.getSize(); i++) {
            list += " " + (i + 1) + "." + tasks.getTask(i);
            if (i != tasks.getSize() - 1) {
                list += System.lineSeparator();
            }
        }
        ui.printTemplate(list);
    }
}
