package duke.commands;

import duke.DukeException;
import duke.Item;
import java.util.LinkedList;

public class AddTodoCommand extends Command {
    private String content;

    @Override
    public void parseArg(String args) {
        if (args.length() <= 5) {
            throw new DukeException("Argument cannot be empty.");
        } else {
            this.content = args.substring(5);
        }
    }

    @Override
    public void execute(LinkedList<Item> itemList) {
        System.out.println("Invoked AddTodoCommand");
        
    }
}
