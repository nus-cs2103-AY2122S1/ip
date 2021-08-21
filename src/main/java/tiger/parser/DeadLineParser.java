package tiger.parser;

import tiger.exceptions.inputs.TigerEmptyStringException;
import tiger.exceptions.inputs.TigerInvalidInputException;
import tiger.utils.RemoveSpaces;

import java.util.Arrays;
import java.util.List;

public class DeadLineParser extends Parser {

    public String todo = "";
    public String dateLine = "";

    // TODO: ban users from entering semicolons!

    public DeadLineParser(String input) throws TigerInvalidInputException {
        super(input);
        RemoveSpaces removeSpaces = new RemoveSpaces();
        List<String> array =
                Arrays.asList(removeSpaces.removeBackAndFrontSpaces(input).split(" "));
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
        RemoveSpaces removeLastSpaces = new RemoveSpaces();
        try {
            this.todo = removeLastSpaces.removeLastSpaces(this.todo);
        } catch (StringIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("Deadline description");
        }
        try {
            this.dateLine = removeLastSpaces.removeLastSpaces(this.dateLine);
        } catch (StringIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("Deadline date");
        }
    }
}
