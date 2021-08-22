package duke.commands;

import duke.Duke;
import duke.DukeException;
import duke.Item;
import duke.ToDo;
import java.util.ArrayList;
import java.util.LinkedList;

public class AddTodoCommand extends Command {
    private String content;

    @Override
    public void parseLine(String line) throws DukeException {
        if (line.length() <= 5) {
            throw new DukeException("Argument cannot be empty.");
        } else {
            this.content = line.substring(5);
        }
    }

    @Override
    public void execute(LinkedList<Item> itemList) {
        ArrayList<String> printBuffer = new ArrayList<>();
        printBuffer.add("Got it. I've added this task:");
        ToDo toAdd = new ToDo(this.content);
        itemList.add(toAdd);
        printBuffer.add("  " + toAdd.toString());
        printBuffer.add(String.format("Now you have %d tasks in the list.", itemList.size()));
        System.out.println(Duke.styleResponse(printBuffer));
    }
}
