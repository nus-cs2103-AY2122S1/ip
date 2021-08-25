package duke;

import java.util.*;

public class Chatbot {
    /**
     * Chatbot is the main obj that is imported to any runner as an application
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

    enum TaskCommands {
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

    enum ChatContinue {
        CONTINUE,
        END,
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
            case BYE:
                return this.farewell();
            case LIST:
                return this.taskList.list(this.ui);
            default:
                this.ui.showNotSupported();
                return ChatContinue.END;
        }
    }

    private ChatContinue farewell() {
        this.ui.showFarewell();
        return ChatContinue.END;
    }
}
