import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.Scanner;

public class Chatbot {
    private enum ChatCommands {
        BYE("bye");

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

    public Chatbot() {
        scanner = new Scanner(System.in);

        System.out.println("Hey there, I'm Duke! Nice to meet you!");
        System.out.println("I'm a bot build by SpdPnd98, let me know how I can help you!");
        System.out.println("Here are some things I can do now:");
        System.out.println("1. Bye");
        System.out.println("I'll repeat after you if I don't understand you :)");
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
        if (command == null) {
            System.out.println(input);
            return ChatContinue.CONTINUE;
        }
        switch (command) {
            case BYE:
                return farewell();
            default:
                System.out.println("How did you get here?");
                return ChatContinue.END;
        }
    }

    // boolean to indiacte end of chat
    private ChatContinue farewell() {
        System.out.println("Bye. Hope to see you again soon!");
        return ChatContinue.END;
    }
}
