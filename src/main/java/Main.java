import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        while(loop) {
            String input = scanner.nextLine();
            loop = duke.process(input);
        }

    }
}
