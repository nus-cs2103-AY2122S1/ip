package duke.command;

public class WrongCommand extends Command {

    public WrongCommand() {}

    @Override
    public String getResponse(String input) {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
