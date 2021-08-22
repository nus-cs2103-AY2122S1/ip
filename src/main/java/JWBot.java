import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for the chat bot
 *
 * @author  Yim Jaewon
 */
public class JWBot {

    public static final String txtPath = "jwbot.txt";

    public static void updateTxt(List<Task> items) throws IOException {
        FileWriter fw = new FileWriter(txtPath);
        for (Task task : items) {
            fw.write(task.toString() + "\n");
        }
        fw.close();
    }

    public static void readTxt(List<Task> items) throws IOException {
        File file = new File(txtPath);
        if (!file.exists()) {
            file.createNewFile();
        }
        try {
            FileReader filereader = new FileReader(file);
            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while((line = bufReader.readLine()) != null){
                if (line.charAt(1) == 'T') {
                    Todo todo = new Todo(line.substring(7));
                    if (line.charAt(4) == 'X') {
                        todo.markAsDone();
                    }
                    items.add(todo);
                } else if (line.charAt(1) == 'E') {
                    String desAndAt = line.substring(7);
                    String[] separated = desAndAt.split(" \\(at: ");
                    Event event = new Event(separated[0], separated[1].substring(0, separated[1].length() - 1));
                    if (line.charAt(4) == 'X') {
                        event.markAsDone();
                    }
                    items.add(event);
                } else if (line.charAt(1) == 'D') {
                    String desAndBy = line.substring(7);
                    String[] separated = desAndBy.split(" \\(by: ");
                    Deadline deadline = new Deadline(separated[0], separated[1].substring(0, separated[1].length() - 1));
                    if (line.charAt(4) == 'X') {
                        deadline.markAsDone();
                    }
                    items.add(deadline);
                }
            }
            bufReader.close();
        } catch(IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws JWBotException, IOException {
        Scanner sc = new Scanner(System.in);

        String greeting = "Wassup bro! I'm JWBot\n"
                + "How can I help you?\n";
        String byeMessage = "You leaving already? See you soon bro!";
        List<Task> items = new ArrayList<>();

        System.out.println(greeting);
        readTxt(items);

        String input = "";

        while(!input.equals("bye")) {
            try {
                input = sc.nextLine();
                if (input.equals("bye")) {
                    System.out.println(byeMessage);
                } else if (input.equals("list")) {
                    System.out.println("OK bro, the tasks in your list are: ");
                    for (int i = 1; i < items.size() + 1; i++) {
                        System.out.println(i + ". " + items.get(i - 1));
                    }
                    System.out.println("Bro, now you have " + items.size() + " task(s) stored in the list!");
                } else if (input.startsWith("done ")) {
                    try {
                        String[] separated = input.split(" ");
                        int index = Integer.parseInt(separated[1]);
                        items.get(index - 1).markAsDone();
                        updateTxt(items);
                        System.out.println("OK Bro, I noted you've done this task:\n" +
                                items.get(index - 1));
                    } catch (Exception e) {
                        throw new JWBotException("Sorry bro, I think you chose an incorrect index number to mark done!");
                    }
                } else if (input.startsWith("deadline ")) {
                    try {
                        String content = input.split(" ", 2)[1];
                        String[] separated = content.split(" /by ");
                        Deadline deadline = new Deadline(separated[0], separated[1]);
                        items.add(deadline);
                        updateTxt(items);
                        System.out.println("OK bro, I just added: " + deadline);
                    } catch (Exception e) {
                        throw new JWBotException("Sorry bro, I think you made an error with the deadline format!");
                    }
                } else if (input.startsWith("todo ")) {
                    String content = input.split(" ", 2)[1];
                    Todo todo = new Todo(content);
                    items.add(todo);
                    updateTxt(items);
                    System.out.println("OK bro, I just added: " + todo);
                } else if (input.startsWith("event ")) {
                    try {
                        String content = input.split(" ", 2)[1];
                        String[] separated = content.split(" /at ");
                        Event event = new Event(separated[0], separated[1]);
                        items.add(event);
                        updateTxt(items);
                        System.out.println("OK bro, I just added: " + event);
                    } catch (Exception e) {
                        throw new JWBotException("Sorry bro, I think you made an error with the event format!");
                    }
                } else if (input.startsWith("delete ")) {
                    try {
                        String[] separated = input.split(" ");
                        int index = Integer.parseInt(separated[1]);
                        Task task = items.remove(index - 1);
                        updateTxt(items);
                        System.out.println("OK Bro, I noted you've deleted this task:\n" +
                                task);
                        System.out.println("So bro, now you have " + items.size() + " tasks stored in the list!");
                    } catch (Exception e) {
                        throw new JWBotException("Sorry bro, I think you chose an incorrect index number to delete!");
                    }
                } else {
                        throw new JWBotException("Sorry bro, I don't understand what you mean!");
                }
            } catch (JWBotException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
