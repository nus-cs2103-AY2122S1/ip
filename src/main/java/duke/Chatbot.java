package duke;

import java.util.Scanner;

public class Chatbot {
    private enum ChatCommands {
        CHAT_COMMAND_BYE("bye"),
        CHAT_COMMAND_LIST("list");

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

    enum TaskCommands {
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

    enum ChatContinue {
        CHAT_CONTINUE,
        CHAT_END,
    }

    private Scanner scanner;
    private FileDB fileDB;
    private Ui ui;
    private TaskList taskList;

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

    private ChatContinue interpret() throws DukeIOException, DukeDateParseException {
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



    private ChatContinue builtInCommands(ChatCommands command) {
        switch (command) {
        case CHAT_COMMAND_BYE:
            return this.farewell();
        case CHAT_COMMAND_LIST:
            return this.taskList.list(this.ui);
        default:
            this.ui.showNotSupported();
            return ChatContinue.CHAT_END;
        }
    }

    private ChatContinue farewell() {
        this.ui.showFarewell();
        return ChatContinue.CHAT_END;
    }
}
