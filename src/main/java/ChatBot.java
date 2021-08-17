import java.util.Scanner;

public class ChatBot {

    static Scanner userInput = new Scanner(System.in);
    private String[] list = new String[100];
    private int lastIndex = 0;


    private String welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greeting = "Why hello there! It's Duke here!\n" + "How can I help you today master?";

        return logo + greeting;
    }

    private boolean listEmpty() {
        boolean empty = true;
        for (String str : list) {
            if (str != null) {
                empty = false;
                break;
            }
        }
        return empty;
    }

    private void startInput() {
        String input = userInput.nextLine();

        if (input.equals("bye")) {
            System.out.println("See ya again later!");
            return;
        }
        if (input.equals("list")) {
            if (this.listEmpty()) {
                System.out.println("You haven't added anything yet!");
            } else {
                for (int i = 0; i < list.length; i++) {
                    if (list[i] != null) {
                        System.out.println(i + 1 + ". " + list[i]);
                    }
                }
            }
        } else {
            list[lastIndex] = input;
            lastIndex++;
            System.out.println("added: " + input);
        }
        startInput();
    }

    void welcomeSeq() {
        System.out.println(this.welcomeMessage());
        startInput();
    }

}
