package duke.command;

abstract class AddCommand implements Command {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AddCommand)) {
            return false;
        }
        return true;
    }
}
