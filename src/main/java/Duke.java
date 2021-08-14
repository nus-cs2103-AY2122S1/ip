import java.util.Random;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo =
                          "██    ██ ██  ██████ ████████  ██████  ██████  \n"
                        + "██    ██ ██ ██         ██    ██    ██ ██   ██ \n"
                        + "██    ██ ██ ██         ██    ██    ██ ██████  \n"
                        + " ██  ██  ██ ██         ██    ██    ██ ██   ██ \n"
                        + "  ████   ██  ██████    ██     ██████  ██   ██ \n";
        Duke victor = new Duke();
        victor.greet();
        Command command = victor.listen();
        while (!command.isGoodBye()) {
            victor.echo(command);
            command = victor.listen();
        }
        victor.bye();
    }

    private void greet() {
        String[] greetings = {"Hey there, friend.", "Howdy, pardner! Might I say, you're looking fit as a fiddle.",
                "Howdy, pardner! So, when do the rustlers show up?", "Howdy, pardner!"};
        String question = "Is there anything old Vic can do you for?";
        Random rand = new Random();
        int random_int = rand.nextInt(greetings.length);
        String greeting = greetings[random_int];
        formatPrint(greeting, question);
    }

    private Command listen() {
        Scanner scanner = new Scanner(System.in);
        String instruction = scanner.nextLine();
        Command command = new Command(instruction);
        return command;
    }

    private void echo(Command command) {
        formatPrint(command.getInstruction());
    }

    private void bye() {
        String[] goodbye = {"Be seeing you.", "'Til our trails cross again, pardner.",
                "Well, happy trails, then!", "See ya round, buckaroo."};
        Random rand = new Random();
        int random_int = rand.nextInt(goodbye.length);
        String bye = goodbye[random_int];
        formatPrint(bye);
    }

    private void formatPrint(String... lines) {
        System.out.println("\n    ____________________________________________________________");
        for (String line : lines) {
            System.out.printf("     %s\n", line);
        }
        System.out.println("    ____________________________________________________________\n");
    }
}
