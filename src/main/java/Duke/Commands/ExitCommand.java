package Duke.Commands;

import Duke.Duke;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class ExitCommand extends Command {
    private static final Set<String> KEYWORDS = new HashSet<>(List.of("exit", "bye", "quit"));

    @Override
    public void run(Duke duke, Duke.UserInput input) {
        duke.stop();
    }

    @Override
    protected Set<String> getKeywords() {
        return KEYWORDS;
    }
}
