import java.util.Scanner;

public class ChatBot {

    static Scanner userInput = new Scanner(System.in);
    private String[] list = new String[100];


    private String welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greeting = "Why hello there! It's Duke here!\n" + "How can I help you today master?";

        return logo + greeting;
    }

    private void startInput() {
        String input = userInput.nextLine();

        if (input.equals("bye")) {
            System.out.println("See ya again later!");
            return;
        }
        System.out.println(input);
        startInput();
    }

    void welcomeSeq() {
        System.out.println(this.welcomeMessage());
        startInput();
    }

}
