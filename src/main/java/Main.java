import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        levelFive();
    }

    public static void levelFive() {
        Duke duke = new Duke();
        duke.greet();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            try {
                duke.processCommand(command);
            } catch (DukeException e) {
                System.out.println(e);
            }
            command = sc.nextLine();
        }
        duke.bye();
    }
}