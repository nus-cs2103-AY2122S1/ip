import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        levelSeven();
    }

    public static void levelSeven() {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        while (duke.isLive()) {
            String command = sc.nextLine();
            try {
                duke.processCommand(command);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }
}