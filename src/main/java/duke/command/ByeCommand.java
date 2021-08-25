package duke.command;

import duke.Duke;
import duke.Storage;
import duke.ToDoList;
import duke.Ui;

public class ByeCommand extends Command {
    private Duke chatBot;
    private Ui ui;
    private ToDoList tdl;
    private Storage storage;

    public ByeCommand(Duke chatBot, Ui ui, ToDoList tdl, Storage storage) {
        this.chatBot = chatBot;
        this.ui = ui;
        this.tdl = tdl;
        this.storage = storage;
    }

    @Override
    public void execute() {
        this.chatBot.stopRunning();
        System.out.println("========== " + Duke.getName() + " ===========");
        System.out.println("Wow! I can get off work now :D");
        this.storage.save();
        System.out.println("Saved your work by the way!");
        System.out.println("========== " + Duke.getName() + " ===========\n");
    }
}
