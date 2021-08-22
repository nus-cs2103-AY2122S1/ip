package abyss.command;

public class FindCommand implements Command {
    private String keyword;

    protected FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return this.keyword;
    }
}
