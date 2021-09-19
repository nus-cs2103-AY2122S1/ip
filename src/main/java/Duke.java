import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        int count = 0;
        Ui ui = new Ui();
        ui.greetUser();

        Storage storage = new Storage("data");

        Scanner myObj = new Scanner(System.in);
        while (true) {
            String input = myObj.nextLine();
            Parser.parse(input, storage);
        }
    }
}


