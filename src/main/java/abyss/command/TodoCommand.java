package abyss.command;

public class TodoCommand implements Command {
    private String description;

    protected TodoCommand(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
