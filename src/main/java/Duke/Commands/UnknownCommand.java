package Duke.Commands;

import Duke.Duke;

class UnknownCommand extends Command {
    @Override
    public void run(Duke duke, String arg) {
        duke.say(arg);
    }

    @Override
    protected String getKeyword() {
        return null;
    }
}
