package duke.command;

public abstract class Command {

    // method to execute the command
    public abstract void execute();

    // method to check if we are exiting the program
    public boolean isExit() {
        return false;
    }
}
