package abyss;

public class DoneCommand extends Command {
    private static final String DONE_REGEX = "^\\d*$";

    private int index;

    protected DoneCommand(String content) throws InvalidCommandException {
        super(Type.DONE);
        if (!content.matches(DONE_REGEX)) {
            throw new InvalidCommandException("Command 'done' should be followed by " +
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
