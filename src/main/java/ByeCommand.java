import java.util.ArrayList;

public class ByeCommand extends Command {
    public ByeCommand() {
        super("bye");
    }

    /**
     * The execute() method in ByeCommand prints a goodbye message.
     */
    @Override
    public void execute(String des, TaskList tList) {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
