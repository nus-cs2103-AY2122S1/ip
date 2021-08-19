import org.jetbrains.annotations.Nullable;

import javax.sound.midi.SysexMessage;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

public class Chatbot {
    private enum ChatCommands {
        BYE("bye"),
        LIST("list");

        private final String command;

        ChatCommands(final String command) {
            this.command = command;
        }

        @Nullable
        public static ChatCommands toEnum (String str) {
            for(ChatCommands chatCommand: ChatCommands.values()) {
                if (chatCommand.name().equalsIgnoreCase(str)) {
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
    private List<String> memory;

    public Chatbot() {
        scanner = new Scanner(System.in);
        memory = new LinkedList<>();

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
        ChatCommands command = ChatCommands.toEnum(input);
        return command == null ? customMessages(input) : builtInCommands(command);
    }

    private ChatContinue customMessages(String input) {
        if (memory.contains(input)) {
            System.out.println("Repeating what you said: " + input);
        } else {
            memory.add(input);
            System.out.println("added: " + input);
        }
        return ChatContinue.CONTINUE;
    }

    private ChatContinue builtInCommands(ChatCommands command) {
        switch (command) {
            case BYE:
                return farewell();
            case LIST:
                return list();
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
            System.out.println("You haven't said anything I don't know yet... Good! :)");
        } else {
            System.out.println("You said these phrases before:");
            Iterator<String> itr = memory.iterator();
            for (int i = 1; i <= memory.size(); i++) {
                System.out.println(i + ". " + itr.next());
            }
        }
        return ChatContinue.CONTINUE;
    }
}
