import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        levelFour();

    }

    public static void levelFour() {
        Duke duke = new Duke();
        duke.greet();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            duke.processCommand(command);
            command = sc.nextLine();
        }
        duke.bye();
    }
}