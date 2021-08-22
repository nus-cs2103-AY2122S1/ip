import Duke.Duke;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Duke duke = new Duke();
        duke.gettingStart();
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        String command = sc.next().trim();

        while (!duke.isExitCommand(command)) {

            duke.processCommand(command);
            command = sc.next().trim();
        }

        sc.close();
        duke.exitProgram();
    }
}
