package duke;

import java.util.Scanner;


/**
 * @Chatbot represents a chatbot.
 * @author spdpnd98.
 */
public class Chatbot {
    /**
     * @ChatCommands are enumerations used for chatbot commands.
     */
    private enum ChatCommands {
        CHAT_COMMAND_BYE("bye"),
        CHAT_COMMAND_LIST("list"),
        CHAT_COMMAND_FIND("find");

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
        TASK_COMMAND_DONE("done"),
        TASK_COMMAND_DELETE("delete"),
        TASK_COMMAND_TODO("todo"),
        TASK_COMMAND_DEADLINE("deadline"),
        TASK_COMMAND_EVENT("event");

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
    public enum ChatContinue {
        CHAT_CONTINUE,
        CHAT_END,
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
            this.fileDB = new FileDB(this.taskList);
        } catch (DukeIOException e) {
            // File already exists
            System.out.println(e.getMessage());
        } catch (DukeDateParseException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @chat initiates a chat with the user, and checks for handled exceptions.
     *
     * Only used in CLI version.
     */
    public void chat() {
        boolean isChatting = true;
        while (isChatting) {
            ui.showChatting();
            try {
                isChatting = interpret() == ChatContinue.CHAT_CONTINUE;
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
        return guiInterpret(input);
    }

    /**
     * @guiInterpret contains the logic to understand user inputs.
     *
     * @return ChatContinue enum to indicate if the chat should continue or terminate.
     * @throws DukeIOException thrown by TaskList.addTask method, if fails to store in FileDB.
     * @throws DukeDateParseException thrown by TaskList.addTask method, if fails to parse the date.
     * @throws DukeArgumentException if not enough arguments are given to TaskCommand methods.
     */
    public ChatContinue guiInterpret(String input) throws DukeIOException, DukeDateParseException, DukeArgumentException {
        String[] parseInput = input.split(" ", 2);
        ChatCommands command = ChatCommands.toEnum(parseInput[0]);
        if (command != null) {
            return builtInCommands(command, parseInput.length == 1 ? "" : parseInput[1]);
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
    private ChatContinue builtInCommands(ChatCommands command, String argument) throws DukeIOException{
        switch (command) {
        case CHAT_COMMAND_BYE:
            return this.farewell();
        case CHAT_COMMAND_LIST:
            return this.taskList.list(this.ui);
        case CHAT_COMMAND_FIND:
            TaskList findTaskList = this.taskList.findTasks(argument);
            System.out.println(findTaskList.list(this.ui));
            return ChatContinue.CHAT_CONTINUE;
        default:
            this.ui.showNotSupported();
            return ChatContinue.CHAT_END;
        }
    }

    /**
     * Terminates the chat session with user.
     *
     * @return ChatContinue enums to indicate if a chat should continue or terminnate.
     */
    private ChatContinue farewell() throws DukeIOException{
        this.ui.showFarewell();
        this.taskList.saveAll(this.fileDB);
        return ChatContinue.CHAT_END;
    }
}
