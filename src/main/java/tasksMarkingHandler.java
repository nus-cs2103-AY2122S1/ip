import java.util.HashMap;
import java.util.Map;
import commands.Command;
import commands.DoneCommand;

import java.util.ArrayList;

public class tasksMarkingHandler {
    
    public tasksMarkingHandler() {}

    ArrayList<Command> markUp(HashMap<Command,Boolean> commandInputs) {
        ArrayList<Command> outputCommands = new ArrayList<>();
        for (Map.Entry<Command, Boolean> entry : commandInputs.entrySet()) {
            if (entry.getValue()) {
                outputCommands.add(DoneCommand.markAsComplete(entry.getKey()));
                continue;
            }
            outputCommands.add(entry.getKey());
        }
        return outputCommands;
    }

}
