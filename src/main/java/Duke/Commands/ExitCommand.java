package Duke.Commands;

import Duke.Duke;

class ExitCommand extends Command {
    private static final String KEYWORD = "bye";

    @Override
    public void run(Duke duke, Duke.UserInput input) {
        duke.stop();
    }

    @Override
    protected String getKeyword() {
        return KEYWORD;
    }
}
