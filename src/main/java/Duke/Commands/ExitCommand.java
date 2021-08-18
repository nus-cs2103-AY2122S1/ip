package Duke.Commands;

import Duke.Duke;

class ExitCommand extends Command {
    private static final String KEYWORD = "exit";

    @Override
    public void run(Duke duke, String arg) {
        duke.stop();
    }

    @Override
    protected String getKeyword() {
        return KEYWORD;
    }
}
