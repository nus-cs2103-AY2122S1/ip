import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Intro printIntro = new Intro();
        Farewell printFarewell = new Farewell();

        printIntro.printIntro();

        // Starts reading user input
        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (!command.toLowerCase().equals("bye")) {
            command = scanner.nextLine();
            // Processes user input
            FeatureMain feature = new FeatureMain(command);
            feature.echoCommand();
        }

        printFarewell.printFarewell();

    }
}
