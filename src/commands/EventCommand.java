package commands;

import exceptions.DukeEmptyStringException;
import utils.RemoveLastSpaces;

import java.util.Arrays;
import java.util.List;

public class EventCommand {

    public String todo = "";
    public String eventAt = "";

    public EventCommand(String input) throws DukeEmptyStringException {
        List<String> array = Arrays.asList(input.split(" "));
        // TODO: make assert statements work
        assert (array.contains("/at"));
        boolean atFound = false;
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i).equals("/at")) {
                atFound = true;
                continue;
            }
            if (!atFound) {
                this.todo += (array.get(i) + " ");
            } else {
                this.eventAt += (array.get(i) + " ");
            }
        }
        RemoveLastSpaces removeLastSpaces = new RemoveLastSpaces();
        try {
            this.todo = removeLastSpaces.removeLastSpaces(this.todo);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeEmptyStringException("Event description");
        }
        try {
            this.eventAt = removeLastSpaces.removeLastSpaces(this.eventAt);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeEmptyStringException("Event date");
        }

    }
}
