package botto.command;

import java.util.Timer;
import java.util.TimerTask;

import botto.util.Dialog;
import botto.util.Storage;
import botto.util.TaskList;


/**
 * Command for stopping the bot.
 */
public class ExitCommand implements Command {

    /**
     * Print goodBye message.
     *
     * @param taskList the task list involved.
     * @param dialog the ui of the Botto bot.
     * @param storage storage of the Botto bot.
     */
    @Override
    public void execute(TaskList taskList, Dialog dialog, Storage storage) {
        dialog.sayGoodBye();

        // quit the programme 2s after saying goodbye
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 2000);
    }

    /**
     * Return true.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
