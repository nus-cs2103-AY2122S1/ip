import java.util.Scanner;

public class Duke {

    private static void run() {
        Ui.greet();
        TaskList taskList = Storage.load(); 
        Scanner input = new Scanner(System.in);
        while (true) {
            String newInput = input.nextLine();
            if (newInput.equals("bye")) break; 
            try {
                Parser.parse(newInput, taskList).execute(taskList);
            } catch (DukeExcpetion e) {
                Ui.printString(e.toString());
            }
        }
        input.close();
        Ui.exit();
    }

    public static void main(String[] args) {
        run();
    }
}