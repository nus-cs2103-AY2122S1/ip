import static java.lang.Integer.parseInt;

public class Ui {

    public void printWelcome() {
        System.out.println(
                "Hello! I'm Amped :) \n"
                        + "Type: \n"
                        + "1. A task (todo/deadline/event) followed by description to add tasks \n"
                        + "   e.g \"deadline submit homework /by Sunday 12 pm\" \n"
                        + "2. \"list\" to see the list of tasks \n"
                        + "3. \"done [number]\" to mark a particular task as done \n"
                        + "4. \"delete [number]\" to delete a particular task \n"
                        + "5. \"bye\" to exit"
        );
    }

    public void printBye() {
        System.out.println("Good Bye. Have a nice day!");
    }

    public void printDone() {
        System.out.println("Nice! I've marked this task as done: ");
    }

    public void printRemove() {
        System.out.println("Noted. I've removed this task: ");
    }

    public void printAddTask(TaskList ls) {
        ls.printAddTask();
    }

    public void displayList(TaskList ls) {
        ls.displayList();
    }

    public void printCurrentTask(TaskList ls, int index) {
        System.out.println(ls.getTask(index).toString());
    }
}
