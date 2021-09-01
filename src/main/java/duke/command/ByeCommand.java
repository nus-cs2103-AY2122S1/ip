package duke.command;

import duke.Duke;
import duke.util.Storage;
import duke.util.ToDoList;

/**
 * ByeCommand is a Command that encapsulates the attributes and behaviour of a command to stop.
 */
public class ByeCommand extends Command {

    private Duke chatBot;
    private ToDoList tdl;
    private Storage storage;

    /**
     * Creates an instance of ByeCommand.
     *
     * @param chatBot The instance of chat bot itself.
     * @param tdl Instance of ToDoList currently in use.
     * @param storage Instance of Storage currently in use.
     */
    public ByeCommand(Duke chatBot, ToDoList tdl, Storage storage) {
        this.chatBot = chatBot;
        this.tdl = tdl;
        this.storage = storage;
    }

    @Override
    public String execute() {
        this.chatBot.stopRunning();
        this.storage.save();
        String response = "Wow! I can get off work now :D\n";
        response += "Saved your work by the way!\n";
        return response;
    }
}
