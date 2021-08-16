import java.util.Objects;
import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        System.out.println("Howwwwwwdy! I'm Bob");
        System.out.println("What do you want?\n");

        Scanner scanner = new Scanner(System.in);

        String response = scanner.nextLine();

        while (!Objects.equals(response, "bye")) {
            System.out.println(response + "\n");
            response = scanner.nextLine();
        }

        System.out.println("Bye! Shoo!");

    }
}
