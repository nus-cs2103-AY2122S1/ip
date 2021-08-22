package util.commands;


public class ExitCommand implements Command {

    public static boolean isExit = false;

    public ExitCommand() {}

    @Override
    public void execute() {this.isExit = true;}


}
