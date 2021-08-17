import java.util.Scanner;

public class LaunchDuke {
    
    public static void main(String[] args) {
        System.out.println(Duke.dukeStart());
        Scanner scanner = new Scanner(System.in);
        Duke.runDukeBot(scanner);
        scanner.close();
    }
    
}
