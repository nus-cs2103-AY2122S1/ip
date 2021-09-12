package sora;

import java.io.IOException;

import sora.exception.SoraException;
import sora.task.TaskList;
import sora.util.Message;
import sora.util.Parser;
import sora.util.Storage;
import sora.util.Ui;

public class Sora {
    private final Storage storage;
    private final Ui ui;
    private TaskList taskList;

    /**
     * Initializes each component of the program.
     * Loads any data available from filePath.
     *
     * @param isCli Enable cli output
     */
    public Sora(boolean isCli) {
        storage = new Storage("data.txt");
        ui = isCli ? new Ui() : null;

        if (isCli) {
            ui.printMessage(Message.WELCOME);
        }

        try {
            taskList = new TaskList(storage.load());
        } catch (SoraException | IOException e) {
            taskList = new TaskList();
            if (isCli) {
                ui.printMessage(e.getMessage());
            }
        }
    }

    /**
     * Initializes and starts the program in CLI interface.
     *
     * @param args Not used
     */
    public static void main(String[] args) {
        new Sora(true).run();
    }

    /** Run the program. */
    public void run() {
        String input;

        // Program exits only if user inputs "bye"
        while (!(input = ui.readCommand()).equals("bye")) {
            try {
                // Interpret and execute the command input by user
                String message =
                        input.equals("help")
                        ? Message.HELP.toString()
                        : Parser.interpretCommand(input).execute(taskList);
                ui.printMessage(message);

                // Update storage file
                storage.save(taskList.getTasks());
            } catch (SoraException | IOException e) {
                ui.printMessage(e.getMessage());
            }
        }

        // Exit Ui
        ui.exit();
    }

    /**
     * Handles command from user and returns corresponding message from executing the command.
     * Used by JavaFX.
     *
     * @param command Command input by user
     * @return Response message of the command or error message
     */
    public String getResponse(String command) {
        // Exit is handled separately
        if (command.equals("bye")) {
            return Message.EXIT.toString();
        }

        if (command.equals("help")) {
            return Message.HELP.toString();
        }

        String message;
        try {
            // Interpret and execute the command by user
            message = Parser.interpretCommand(command).execute(taskList);

            // Update storage file
            storage.save(taskList.getTasks());
        } catch (SoraException | IOException e) {
            message = e.getMessage();
        }

        return message;
    }
}
