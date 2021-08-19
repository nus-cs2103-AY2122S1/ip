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

    public void echo(String input) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + input);
        System.out.println("    ____________________________________________________________");
    }
}
