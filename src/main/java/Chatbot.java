import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.List;

public class Chatbot {
    private enum ChatCommands {
        BYE("bye"),
        LIST("list"),
        DONE("done");

        private final String command;

        ChatCommands(final String command) {
            this.command = command;
        }

        @Nullable
        public static ChatCommands toEnum (String str) {
            String[] splitCommand = str.split(" ", 2);
            for(ChatCommands chatCommand: ChatCommands.values()) {
                if (chatCommand.name().equalsIgnoreCase(splitCommand[0])) {
                    return chatCommand;
                }
            }
            return null;
        }
    }

    private enum ChatContinue {
        CONTINUE,
        END,
    }

    private Scanner scanner;
    private List<Task> memory;

    public Chatbot() {
        this.scanner = new Scanner(System.in);
        this.memory = new LinkedList<>();

        System.out.println("Hey there, I'm Duke! Nice to meet you!");
        System.out.println("I'm a bot build by SpdPnd98, let me know how I can help you!");
        System.out.println("Here are some things I can do now:");
        System.out.println("1. Bye");
        System.out.println("I'll learn what you're saying and " +
                           "repeat it to you next time you say it if it's something new!");
    }

    public void chat() {
        boolean keepChatting = true;
        while (keepChatting) {
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("How can I help you?");
            System.out.print("User: ");
            keepChatting = interpret() == ChatContinue.CONTINUE;
        };
    }

    private ChatContinue interpret () {
        String input = scanner.nextLine();
        String[] inputArgs = input.split(" ", 2);
        if (inputArgs.length == 1) {
            inputArgs = new String[] {inputArgs[0], ""};
        }
        ChatCommands command = ChatCommands.toEnum(input);
        return command == null
                ? customItems(new Task(input)) :
                builtInCommands(command, inputArgs[1]);
    }

    private ChatContinue customItems(Task input) {
        if (this.memory.contains(input)) {
            System.out.println("Here is your task: " + input);
        } else {
            this.memory.add(input);
            System.out.println("added: " + input);
        }
        return ChatContinue.CONTINUE;
    }

    private ChatContinue builtInCommands(ChatCommands command, String input) {
        switch (command) {
            case BYE:
                return this.farewell();
            case LIST:
                return this.list();
            case DONE:
                return this.markDone(input);
            default:
                System.out.println("How did you get here?");
                return ChatContinue.END;
        }
    }

    private ChatContinue farewell() {
        System.out.println("Bye. Hope to see you again soon!");
        return ChatContinue.END;
    }

    private ChatContinue list() {
        if (memory.size() == 0) {
            System.out.println("You haven't asked me to store anything yet... Good! :)");
        } else {
            System.out.println("Here are the tasks in the list:");
            Iterator<Task> itr = memory.iterator();
            for (int i = 1; i <= memory.size(); i++) {
                System.out.println(i + ". " + itr.next());
            }
        }
        return ChatContinue.CONTINUE;
    }

    private ChatContinue markDone(String args) {
        System.out.println(args);
        Task targetTask = new Task(args);
        Task task = memory.stream().filter(t -> t.equals(targetTask)).findAny().get();
        task.markDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        return ChatContinue.CONTINUE;
    }
}
