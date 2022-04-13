package logic;

import model.Command;

/**
 * Logic Unit Stub that does not process any command.
 */
public class CommandLogicUnitStub implements ICommandLogicUnit {
    @Override
    public void processCommand(Command command, CommandArgument arguments) {
        // do nothing
    }
}
