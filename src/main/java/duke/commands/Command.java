package duke.commands;

abstract class Command {

    abstract void execute(int i);

    public boolean isExit() {
        return false;
    }
}
