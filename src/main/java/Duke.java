package main.java;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    /**
     * Main function for the Chatbot.
     *
     * @param args ...
     */
    public static void main(String[] args) throws DukeException {
        // Starting message
        String start = "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                + "Hello! My name is LHWBot!\n"
                + "What can I do for you today?\n"
                + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";
        System.out.println(start);

        // Array of String to store user inputs
        ArrayList<Task> list = Storage.load();

        // Define the scanner to read user inputs
        Scanner reader = new Scanner(System.in);
        String input = "";

        // Define the Command handler to handle user inputs
        CommandHandler handler = new CommandHandler(list);

        // Continuously listen for user inputs
        while (!input.equals("bye")) {

            // Get the next input from the user
            input = reader.nextLine();

            try {

                // Continuously get the CommandHandler to handle new inputs from user
                handler.handle(input);

            } catch (DukeException e) {

                System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                        + e.getMessage()
                        +"\n"
                        + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n");

            }
        }

        // If the user types "bye", print out the relevant text, close the scanner, and exit the bot.
        ByeCommand command = new ByeCommand(input);
        System.out.println(command.reply());
        reader.close();
    }
}