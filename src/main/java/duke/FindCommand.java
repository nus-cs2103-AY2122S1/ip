package duke;

public class FindCommand implements Executable {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList taskList) {
        taskList.find(keyword);
    }
}
