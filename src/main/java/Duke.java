
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" +
                "To add a Todo, type -> todo <Description> \n" +
                "To add a Deadline, type -> deadline <Description> /by <deadline>\n" +
                "To add an Event, type -> event <Description> /at <details>\n" +
                "To mark as done, type -> done <task list index>\n" +
                "To see all of your tasks, type -> list\n" +
                "To end session, type -> bye\n" +
                "What can I do for you today?");


        Recieve recieve = new Recieve();
        recieve.run();


    }
}
