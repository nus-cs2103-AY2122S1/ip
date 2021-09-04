package abyss.command;

import abyss.Abyss;

public class FindCommand implements Command {
    private String keyword;

    protected FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command.
     *
     * @return Response from executing the find command.
     */
    public String execute() {
        return Abyss.getTaskManager().find(keyword);
    }
}
