package commands;

import exceptions.DukeEmptyStringException;
import utils.RemoveLastSpaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeadLineCommand extends Command {

    public String todo = "";
    public String dateLine = "";

    public DeadLineCommand(String input) {
        List<String> array = Arrays.asList(input.split(" "));
        // TODO: make assert statements work
        assert (array.contains("/by"));
        boolean byFound = false;
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i).equals("/by")) {
                byFound = true;
                continue;
            }
            if (!byFound) {
                this.todo += (array.get(i) + " ");
            } else {
                this.dateLine += (array.get(i) + " ");
            }
        }
        RemoveLastSpaces removeLastSpaces = new RemoveLastSpaces();
        try {
            this.todo = removeLastSpaces.removeLastSpaces(this.todo);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeEmptyStringException("Deadline description");
        }
        try {
            this.dateLine = removeLastSpaces.removeLastSpaces(this.dateLine);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeEmptyStringException("Deadline date");
        }
    }
}
