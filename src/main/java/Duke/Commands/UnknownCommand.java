package Duke.Commands;

import Duke.Duke;

class UnknownCommand extends Command {
    @Override
    public void run(Duke duke, Duke.UserInput input) {
        duke.say(input.getRaw());
    }

    @Override
    protected String getKeyword() {
        return null;
    }
}
