package duke;

public class FindCommand extends Command {
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(TaskList t, Ui u, Storage storage) {
        u.displayFoundList(t.findTasks(keyword));
    }
}
