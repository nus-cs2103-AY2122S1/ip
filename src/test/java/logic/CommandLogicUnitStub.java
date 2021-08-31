package logic;

import model.Command;

import java.util.Map;

/**
 * Logic Unit Stub that does not process any command.
 */
public class CommandLogicUnitStub implements ICommandLogicUnit {
    @Override
    public void processCommand(Command command, Map<String, String> arguments) {
        // do nothing
    }
}
