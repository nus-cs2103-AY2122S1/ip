package Duke.Commands;

import Duke.Duke;

class ListTasksCommand extends Command {
    private static final String KEYWORD = "list";

    @Override
    public void run(Duke duke, Duke.UserInput input) {
        duke.say(duke.getTaskList().toString());
    }

    @Override
    protected String getKeyword() {
        return KEYWORD;
    }
}
