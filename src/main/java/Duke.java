import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Starting message
        String start = "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                + "Hello! My name is LHWBot!\n"
                + "What can I do for you today?\n"
                + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";
        System.out.println(start);

        // Array of String to store user inputs
        String[] StringArray = new String[100];
        int index = 0;

        // Define the scanner to read user inputs
        Scanner reader = new Scanner(System.in);

        // Continuously listen for user inputs
        while(true) {
            String input = reader.nextLine();
            if (input.equals("bye")) {
                // If the user types "bye", end bot
                String bye = "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                        + "Bye... Hope to see you again soon!\n"
                        + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";
                System.out.println(bye);
                break;
            } else if (input.equals("list")) {
                String list = "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";
                for (int i = 0; i < index; i++) {
                    list = list
                            + String.valueOf(i + 1)
                            + ". "
                            + StringArray[i]
                            + "\n";
                }
                list += "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";
                System.out.println(list);
            } else {
                // If the user types a command, store the command
                StringArray[index] = input;
                index++;

                // After storing it, repeat it back to the user
                String cmd = "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                        + "Command Added: "
                        + input
                        + "\n"
                        + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";
                System.out.println(cmd);
            }
        }
    }
}
