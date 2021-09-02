package Duke.Commands;

import Duke.Duke;
import Duke.Ui.Ui;
import Duke.Ui.UserInput;
import javafx.application.Platform;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ExitCommand extends Command {
    private static final Set<String> KEYWORDS = new HashSet<>(List.of("exit", "bye", "quit"));

    @Override
    public void run(Duke duke, UserInput input) {
        duke.say(Ui.EXIT_MESSAGE);
        // Call Platform.exit() after a 3-second delay
        Executors.newSingleThreadScheduledExecutor()
                .schedule(Platform::exit, 3, TimeUnit.SECONDS);
    }

    @Override
    protected Set<String> getKeywords() {
        return KEYWORDS;
    }
}
