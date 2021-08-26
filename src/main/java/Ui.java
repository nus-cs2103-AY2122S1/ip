import java.util.Scanner;

public class Ui {
    private Scanner input = new Scanner(System.in);

    public String readInput() {
        return this.input.nextLine();
    }


    public void closeInput() {
        this.input.close();
    }


    public void showGreet() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Welcome! I'm Duke.");
        System.out.println("What can I do for you?\n");
    }


    public void showFarewell() {
        System.out.println("\tBye, hope to see you again!");
    }


    public void showAdd(Task task, int totalNumTask) {
        System.out.printf("\tadded:\n\t\t%s\n", task);
        System.out.printf("\tYou have %d tasks in the list.\n\n", totalNumTask);
    }


    public void showDelete(Task task, int totalNumTask) {
        System.out.println("\tI've deleted this task from the list!");
        System.out.printf("\t\t%s\n", task);
        System.out.printf("\tYou have %d tasks in the list.\n\n", totalNumTask - 1);
    }


    public void showDone(Task task) {
        System.out.println("\tI've marked this task as done!");
        System.out.printf("\t\t%s\n\n", task);
    }


    public void showList(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println("\tYou have no task in your list.\n");
            return;
        }

        System.out.println("\tHere are the tasks in your list:");
        for (int i = 1; i <= taskList.getLength(); i++) {
            System.out.println("\t" + i + ". " + taskList.get(i));
        }
        System.out.println();
    }


    public void showSavingError() {
        System.out.println("Error when saving data!");
    }


    public void showInvalidDateFormat() {
        System.out.println("\tPlease enter the start/end time in the format of <DD/MM/YY HH:MM>!\n");
    }

    public void showInvalidDateRange() {
        System.out.println("\tEnd time must be after the start time!\n");
    }


    public void showDukeException(DukeException e) {
        System.out.printf("\t%s\n\n", e);
    }
}