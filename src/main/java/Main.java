import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        enumVersion();
    }

    public static void enumVersion() {
        Duke duke = new Duke();
        duke.greet();
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