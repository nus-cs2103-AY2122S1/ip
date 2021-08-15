import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Command {
    protected String instruction;
    protected ArrayList<String> parameters;
    protected String[] instructions = {"list", "done", "bye"};
    protected boolean isInstruction;

    public Command(String input) {
        this.parameters = new ArrayList<>();
        String[] inputValues = input.split(" ");
        isInstruction = Arrays.asList(instructions).contains(inputValues[0]);

        if (isInstruction) {
            this.instruction = inputValues[0];
            Collections.addAll(parameters, inputValues);
        } else {
            this.instruction = input;
        }

    }

    public String getInstruction() {
        return this.instruction;
    }

    public String getParameter(int index) {
        return this.parameters.get(index);
    }
}
