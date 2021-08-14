package commands;

import utils.RemoveLastSpaces;

import java.util.Arrays;
import java.util.List;

public class EventCommand {

    public String todo = "";
    public String eventAt = "";

    public EventCommand(String input) {
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
        this.todo = removeLastSpaces.removeLastSpaces(this.todo);
        this.eventAt = removeLastSpaces.removeLastSpaces(this.eventAt);
    }
}
