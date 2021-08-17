package view;

import exception.CommandArityException;
import exception.InvalidCommandException;
import model.Storage;
import model.task.Task;
import parser.ReplyParser;

// This class defines how information is presented to the user
public class Response {
    private static void hLine() {
        System.out.println("\t----------------------------------------------");
    }
    private static void display(String arg) { System.out.println("\t" + arg);}
    private static void display(Task arg) { System.out.println("\t  " + arg);}

    public static void respond(String reply) {
        hLine();
        System.out.println("\t" + reply);
        hLine();
    }

    public static void respond(String[] reply) {
        hLine();
        for (int i = 0; i < reply.length - 1; i++) {
            System.out.println("\t" + reply[i]);
        }
        System.out.println("\t" + reply[reply.length - 1]);
        hLine();
    }

    public static void respond(String query, Storage storage) {
        hLine();
        String[] queryArr;
        try {
            queryArr = ReplyParser.parse(query);
        } catch (InvalidCommandException | CommandArityException e){
            display(e.getMessage());
            return;
        }

        String command = queryArr[0];
        switch (command) {
            case "list":
                display("Here are the tasks in your list:");
                Task[] toShow = storage.getStorage();
                for (int i = 0; i < toShow.length; i++) {
                    Task entry = toShow[i];
                    int num = i + 1;
                    display(num + "." + entry);
                }
                break;
            case "bye":
                display("Bye! Hope to see you again soon!");
                break;
            case "done":
                Task markedTask;
                try {
                    markedTask = storage.markDone(Integer.parseInt(queryArr[1]));
                } catch (InvalidCommandException e) {
                    display(e.getMessage());
                    break;
                }
                display("Nice! I've marked this task as done:");
                display(markedTask);
                break;
            case "delete":
                Task deletedTask;
                try {
                    deletedTask = storage.deleteTaskByIdx(Integer.parseInt(queryArr[1]));
                } catch (InvalidCommandException e) {
                    display(e.getMessage());
                    break;
                }
                display("Noted. I've removed this task:");
                display(deletedTask);
                display("Now you have " + storage.numTasks() + " tasks in the list");
                break;
            default:
                Task addedTask = storage.push(queryArr);
                display("Got it. I've added this task:");
                display(addedTask);
                display("Now you have " + storage.numTasks() + " tasks in the list");
                break;
        }
        hLine();
    }
}
