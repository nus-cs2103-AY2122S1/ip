import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Storage for tasks
        TaskStorage storage = new TaskStorage();

        // Taking in user input
        Scanner sc = new Scanner(System.in);
        
        DukeOperator operator = new DukeOperator(Operation.start, storage);
        operator.operate();

        while (true) {
            String input = sc.nextLine();
            
            try {
                operator = new DukeOperator(input.trim(), storage);
                if (!operator.operate()) break;
            } catch (DukeException | IllegalArgumentException e) {
                System.out.println(StringFormat.tabAndFormat(e.getMessage()));
            }
        }
        sc.close();
    }
}
