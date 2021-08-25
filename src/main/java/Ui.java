public class Ui {

    String greetingMessage = "\tHola! I'm Blitz :)";
    String goodbyeMessage = "Adiós. Hope to see you again soon!";

    public void printLine() {
        System.out.print('\t');
        for (int i = 0; i < 100; i++) {
            System.out.print('_');
        }
        System.out.println("");
    }

    public void printFormatted(String str) {
        printLine();
        System.out.println("\t" + str);
        printLine();
    }

    public void printList(TaskList tasks, String str) {
        int ctr = 1;
        System.out.println("\t" + str);
        //System.out.println("\tHere are the tasks in your list:");
        if(tasks.size() == 0) {
            System.out.println("\t---No items stored yet---");
        } else {
            tasks.printList();
        }
    }

    public void printGreeting() {
        printLine();
        System.out.println(this.greetingMessage);
    }

    public void printGoodbye() {
        printFormatted(this.goodbyeMessage);
    }

    public void showLoadingError() {
        System.out.println("in loading erro method!");
        System.err.print("Error loading contents from file!!");
    }

}
