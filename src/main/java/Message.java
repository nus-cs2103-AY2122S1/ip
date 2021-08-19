public class Message {
    public void greet() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Jani, your personal chat bot.\n" +
                "    How may I assist you today?");
        System.out.println("    ____________________________________________________________");
    }

    public void exit() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Goodbye! Please visit me again soon :(");
        System.out.println("    ____________________________________________________________");
    }

    public void addTask(String input) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    added: " + input);
        System.out.println("    ____________________________________________________________");
    }

    public void list() {
        System.out.println("    ____________________________________________________________");
        int id = 1;
        for (String task : Duke.tasks) {
            System.out.println("    " + id + ". " + task);
            id++;
        }
        System.out.println("    ____________________________________________________________");
    }
}
