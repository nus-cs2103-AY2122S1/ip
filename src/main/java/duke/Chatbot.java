package duke;

import java.util.*;


/**
 * @Chatbot represents a chatbot.
 * @author spdpnd98.
 */
public class Chatbot {
    /**
     * @ChatCommands are enumerations used for chatbot commands.
     */
    private enum ChatCommands {
        BYE("bye"),
        LIST("list");

        private final String command;

        ChatCommands(final String command) {
            this.command = command;
        }

        public static ChatCommands toEnum (String str) {
            for(ChatCommands chatCommand: ChatCommands.values()) {
                if (chatCommand.command.equalsIgnoreCase(str)) {
                    return chatCommand;
                }
            }
            return null;
        }
    }

    /**
     * @TaskCommands are enumerations used to identify individual tasks and actions to be taken.
     */
    protected enum TaskCommands {
        DONE("done"),
        DELETE("delete"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event");

        private final String command;

        TaskCommands(final String command) {
            this.command = command;
        }

        public static TaskCommands toEnum (String str) {
            String[] splitCommand = str.split(" ", 2);
            for(TaskCommands chatCommand: TaskCommands.values()) {
                if (chatCommand.command.equalsIgnoreCase(splitCommand[0])) {
                    return chatCommand;
                }
            }
            return null;
        }
    }

    /**
     * @ChatContinue are enumerations to indicate if a chat should continue or terminate.
     */
    protected enum ChatContinue {
        CONTINUE,
        END,
    }

    private Scanner scanner;
    private FileDB fileDB;
    private Ui ui;
    private TaskList taskList;

    /**
     * Creates a Chatbot instance.
     */
    public Chatbot() {
        this.scanner = new Scanner(System.in);
        this.ui = new Ui();
        this.taskList = new TaskList();
        try {
            this.ui.showLoadingFile();
            this.fileDB = new FileDB();
        } catch (DukeIOException e) {
            // File already exists
            System.out.println(e.getMessage());
        }
    }

    /**
     * @chat initiates a chat with the user, and checks for handled exceptions.
     */
    public void chat() {
        boolean keepChatting = true;
        while (keepChatting) {
            ui.showChatting();
            try {
                keepChatting = interpret() == ChatContinue.CONTINUE;
            } catch (DukeArgumentException e) {
                System.out.println(e.getMessage());
            } catch (DukeTaskException e) {
                System.out.println(e.getMessage());
            } catch (DukeIOException e) {
                System.out.println(e);
            } catch (DukeDateParseException e) {
                System.out.println(e);
            }
        };
    }

    /**
     * @interpret contains the logic to understand user inputs.
     *
     * @return ChatContinue enum to indicate if the chat should continue or terminate.
     * @throws DukeIOException thrown by TaskList.addTask method, if fails to store in FileDB.
     * @throws DukeDateParseException thrown by TaskList.addTask method, if fails to parse the date.
     * @throws DukeArgumentException if not enough arguments are given to TaskCommand methods.
     */
    private ChatContinue interpret() throws DukeIOException, DukeDateParseException, DukeArgumentException {
        String input = scanner.nextLine();
        ChatCommands command = ChatCommands.toEnum(input);
        if (command != null) {
            return builtInCommands(command);
        }
        TaskCommands taskCommand = TaskCommands.toEnum(input);
        if (taskCommand != null) {
            String[] arguments = input.split(" ", 2);
            if (arguments.length == 1) {
                throw new DukeArgumentException("No items specified for '" + taskCommand.command + "'!");
            }
            return this.taskList.addTask(taskCommand, arguments[1], this.ui, fileDB);
        }
        throw new DukeArgumentException("Looks like I don't support those commands yet...");
    }

    /**
     * Executes any no argument commands provided to chatbot.
     *
     * @param command the user's input.
     * @return ChatContinue enums to indicate if a chat should continue or terminnate.
     */
    private ChatContinue builtInCommands(ChatCommands command) {
        switch (command) {
            case BYE:
                return this.farewell();
            case LIST:
                return this.taskList.list(this.ui);
            default:
                this.ui.showNotSupported();
                return ChatContinue.END;
        }
    }

    /**
     * Terminates the chat session with user.
     *
     * @return ChatContinue enums to indicate if a chat should continue or terminnate.
     */
    private ChatContinue farewell() {
        this.ui.showFarewell();
        return ChatContinue.END;
    }
}
