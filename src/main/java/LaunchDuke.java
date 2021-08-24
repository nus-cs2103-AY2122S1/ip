import java.io.FileNotFoundException;
import java.util.Scanner;

public class LaunchDuke {
    
    public static void main(String[] args) throws FileNotFoundException {
        Duke duke = new Duke();
        System.out.println(duke.dukeStart());
        Scanner scanner = new Scanner(System.in);
        duke.runDukeBot(scanner);
        scanner.close();
    }
}
