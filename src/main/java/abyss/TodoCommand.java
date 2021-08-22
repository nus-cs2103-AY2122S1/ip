package abyss;

public class TodoCommand extends Command {
    private String description;

    protected TodoCommand(String description) {
        super(Type.TODO);
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
