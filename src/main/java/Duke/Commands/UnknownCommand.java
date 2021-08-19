package Duke.Commands;

import Duke.Duke;

import java.util.Set;

class UnknownCommand extends Command {
    private static final String HELP_MESSAGE = "Sorry I didn't understand what you meant by: %s";

    @Override
    public void run(Duke duke, Duke.UserInput input) {
        duke.say(String.format(HELP_MESSAGE, input.getRaw()));
    }

    @Override
    protected Set<String> getKeywords() {
        return null;
    }
}
