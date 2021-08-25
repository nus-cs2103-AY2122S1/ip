package duke.command;

public class CommandHelp extends Command {

    public CommandHelp() {
        this.commandName = "help";
        this.description = "displays a list of possible commands";
    }

    @Override
    public void execute() {
        displayHelp();
    }


    /**
     * Display list of possible commands
     */
    private void displayHelp() {
        //TODO Refactor to display command info instead
        System.out.println("Duke-san welcomes you! Here are a list of the available commands:\n" +
                "_________________________________________________________________________________________\n" +
                "todo [description]\t- Make a new todo event\n" +
                "deadline [description] /by DD/MM/YYYY XXXX\t- Make a new Deadline (Optional time)\n" +
                "event [description] /at DD/MM/YYYY XXXX\t- Make a new Event (Optional time)\n" +
                "-----------------------------------------------------------------------------------------\n" +
                "list\t- List out your events\n" +
                "done [index]\t- Complete task at index specified in list\n" +
                "delete [index]\t- Deletes the task from the list\n" +
                "-----------------------------------------------------------------------------------------\n" +
                "help\t- Display this text\n" +
                "gubbai\t- Exit duke.Duke UwU\n");
    }
}
