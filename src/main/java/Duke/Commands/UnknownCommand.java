package Duke.Commands;

import Duke.Duke;

class UnknownCommand extends Command {
    private static final String HELP_MESSAGE = "Sorry I didn't understand what you meant by: %s";

    @Override
    public void run(Duke duke, Duke.UserInput input) {
        duke.say(String.format(HELP_MESSAGE, input.getRaw()));
    }

    @Override
    protected String getKeyword() {
        return null;
    }
}
