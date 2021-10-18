package duke.commands;

import duke.exceptions.NoSuchTaskException;
import duke.gui.Ui;
import duke.tasks.TaskList;

public class ExitCommand extends Command {

    @Override
    public String executeCommand(TaskList taskList) throws NoSuchTaskException {
        return closeApplicationAfterMessage();
    }

    /**
     * Closes the GUI of the Application after 1.25 seconds.
     * Prints the goodbye message on the GUI before closing.
     *
     * @return A String representing the goodbye message.
     */
    private String closeApplicationAfterMessage() {
        new Thread(() -> {
            try {
                Thread.sleep(1250);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.exit(0);
            }
        }).start();

        return Ui.exit();
    }
}
