import java.time.LocalDateTime;
import java.util.Scanner;

public class Ui {
    private TaskList taskList;

    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    public void readInput() throws DukeException {
        String nextLine;
        Scanner input = new Scanner(System.in);
        while (!(nextLine = input.nextLine()).equals("bye")) {
            Parser parser = new Parser(nextLine);
            String command = parser.getCommand();
            String description = parser.getDescription();
            LocalDateTime time = parser.getTime();

            switch (command) {
                case (""):  // Nothing is typed
                    throw new DukeException("Please enter a command!");
                case ("list"):
                    System.out.println("Here are the tasks in your list:");
                    System.out.println(taskList.toString());
                    break;
                case ("done"):
                    try {
                        int index = Integer.parseInt(description) - 1;
                        taskList.markAsDone(index);
                    } catch (NumberFormatException nfe) {
                        throw new DukeException("Please follow command 'done' with an integer!");
                    } catch (IndexOutOfBoundsException oob) {
                        throw new DukeException("Invalid task number!");
                    }
                    break;
                default:
                    throw new DukeException("Command not recognised!");
            }
        }
    }
}
