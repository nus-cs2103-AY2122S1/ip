import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String border = "____________________________________________________________";
        Printer printer = new Printer(border);
        printer.PrintIntro();

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            String[] splitInput = input.split(" ", 2);
            if (splitInput[0].equals("done")) {
                int index = Integer.parseInt(splitInput[1]) - 1;
                String returnString = tasks.get(index).markDone();
                printer.PrintMessage(returnString);
            } else if (input.equals("list")) {
                printer.PrintList(tasks);
            } else if (splitInput[0].equals("todo")) {
                try {
                    if (splitInput.length < 2 || splitInput[1].equals("")) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    tasks.add(new Todo(splitInput[1]));
                    printer.PrintSpecialTasks(tasks.get(tasks.size() - 1).toString(), tasks.size());
                } catch (DukeException e) {
                    printer.PrintMessage(e.getMessage());
                }
            } else if (splitInput[0].equals("deadline")) {
                String[] furtherSplits = splitInput[1].split("/by");
                tasks.add(new Deadline(furtherSplits[0], furtherSplits[1]));
                printer.PrintSpecialTasks(tasks.get(tasks.size() - 1).toString(), tasks.size());
            } else if (splitInput[0].equals("event")) {
                String[] furtherSplits = splitInput[1].split("/at");
                tasks.add(new Event(furtherSplits[0], furtherSplits[1]));
                printer.PrintSpecialTasks(tasks.get(tasks.size() - 1).toString(), tasks.size());
            } else {
                try {
                    if (input.equals("blah")) {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                    tasks.add(new Task(input));
                    printer.PrintMessage(String.format("added: %s", input));
                } catch (DukeException e) {
                    printer.PrintMessage(e.getMessage());
                }
            }
            input = sc.nextLine();
        }
        printer.PrintMessage("Bye. Hope to see you again soon!");
    }
}
