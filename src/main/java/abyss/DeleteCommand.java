package abyss;

public class DeleteCommand extends Command {
    private static final String DELETE_REGEX = "^\\d*$";

    private int index;

    protected DeleteCommand(String content) throws InvalidCommandException {
        super(Type.DELETE);
        if (!content.matches(DELETE_REGEX)) {
            throw new InvalidCommandException("Command 'delete' should be followed by " +
                    "the index of the task piece.");
        }

        if (Abyss.getNumberOfTasks() == 0) {
            throw new InvalidCommandException("The Abyss is empty.");
        }


        int i = Integer.parseInt(content);
        if (i < 1 || i > Abyss.getNumberOfTasks()) {
            throw new InvalidCommandException("Index should be positive and not more than " +
                    Abyss.getNumberOfTasks());
        }

        this.index = i;
    }

    public int getIndex() {
        return this.index;
    }
}

