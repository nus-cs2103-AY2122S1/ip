package Duke.Commands;

import Duke.Task;

public class AddCommand extends Command {
    @Override
    public void execute() {

    }

    private String command;
    private Task.Type type;

    public AddCommand(String command, Task.Type type) {
        super(command);
        this.type = type;
        this.command = command;
    }
}
