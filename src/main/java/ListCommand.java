public class ListCommand extends Command {
    public static final String COMMAND_WORD = "LIST";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as a list with index numbers.";

    public ListCommand(TaskHandler th, Storage str) {
        super(th, str);
    }

    @Override
    public void execute(String cmd) {
        taskHandler.printList();
    }
}
