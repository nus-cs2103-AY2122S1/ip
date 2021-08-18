package view;

import exception.LogicException;
import exception.ParserException;
import model.Storage;
import model.task.Task;
import parser.QueryParser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// This class defines how information is presented to the user
public class Response {
    private static void hLine() {
        System.out.println("\t----------------------------------------------");
    }
    private static void display(String arg) { System.out.println("\t" + arg);}
    private final QueryParser parser = new QueryParser();

    public static void respond(String reply) {
        hLine();
        display(reply);
        hLine();
    }

    public static void respond(String[] reply) {
        hLine();
        for (int i = 0; i < reply.length - 1; i++) {
            display(reply[i]);
        }
        display(reply[reply.length - 1]);
        hLine();
    }

    public void respond(String query, Storage storage) {
        String[] queryArr;
        try {
            queryArr = parser.parse(query);
        } catch (ParserException e){
            respond(e.getMessage());
            return;
        }

        String command = queryArr[0];
        String[] reply;
        try {
            switch (command) {
                case "list":
                    List<String> initialReply = new ArrayList<String>();
                    initialReply.add("Here are the tasks in your list:");
                    Task[] toShow = storage.getAllTasks();
                    initialReply.addAll(Stream.<Integer>iterate(1, x -> x + 1)
                                        .limit(toShow.length)
                                        .map(num -> num.toString() + "." + toShow[num - 1])
                                        .collect(Collectors.toList()));
                    reply = initialReply.toArray(new String[0]);
                    break;
                case "bye":
                    reply = new String[] {"Bye! Hope to see you again soon!"};
                    break;
                case "done":
                    Task markedTask = storage.markDone(Integer.parseInt(queryArr[1]));
                    reply = new String[] {"Nice! I've marked this task as done:", "  " + markedTask.toString()};
                    break;
                case "delete":
                    Task deletedTask = storage.deleteTaskByIdx(Integer.parseInt(queryArr[1]));
                    reply = new String[] {"Noted. I've removed this task:",
                                            "  " + deletedTask.toString(),
                                            "Now you have " + storage.numTasks() + " tasks in the list"};
                    break;
                default:
                    Task addedTask = storage.push(queryArr);
                    reply = new String[] {"Got it. I've added this task:",
                            "  " + addedTask.toString(),
                            "Now you have " + storage.numTasks() + " tasks in the list"};
                    break;
            }
            respond(reply);
        } catch (LogicException e) {
            respond(e.getMessage());
        }
    }
}
