import java.util.ArrayList;

public class TaskHandler {
    private ArrayList<Task> list;
    private final DukeStorage storage;

    public TaskHandler(ArrayList<Task> list, DukeStorage storage) {
        this.list = list;
        this.storage = storage;
    }

    public void execute(String command) {
        String line = "    ____________________________________________________________";
        String spacing = "     ";
        if (command.toLowerCase().equals("list")) {
            System.out.println(spacing + "Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(String.format(spacing + "%d.%s", (i + 1), list.get(i).toString()));
            }
        } else if (command.length() > 3 && command.toLowerCase().substring(0, 4).equals("done")) {
            if (command.length() == 4) {
                throw new DukeException("     ☹ OOPS!!! There must be something to be completed");
            }
            Task item = list.get(Integer.parseInt(command.substring(5)) - 1);
            item.markAsDone();
            System.out.println(spacing + "Nice! I've marked this task as done:");
            System.out.println(spacing + "  " + item.toString());
            storage.updateFile(list);
            // if(item num more than list length then throw error)
        } else if (command.length() > 3 && command.toLowerCase().substring(0, 4).equals("todo")) {
            if (command.length() == 4) {
                throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.");
            }
            Todo t = new Todo(command.substring(5));
            list.add(t);
            System.out.println("     Got it. I've added this task:");
            System.out.println(spacing + "  " + t.toString());
            System.out.println(String.format("     Now you have %d tasks in the list.", list.size()));
            storage.writeFile(t);
        } else if (command.length() > 7 && command.toLowerCase().substring(0, 8).equals("deadline")) {
            if (command.length() == 8) {
                throw new DukeException("     ☹ OOPS!!! The description and timing of a deadline cannot be empty.");
            }
            String[] infoArray = command.substring(9).split("/by ", 2);
            Deadline d = new Deadline(infoArray[0], infoArray[1]);
            list.add(d);
            System.out.println("     Got it. I've added this task:");
            System.out.println(spacing + "  " + d.toString());
            System.out.println(String.format("     Now you have %d tasks in the list.", list.size()));
            storage.writeFile(d);
        } else if (command.length() > 4 && command.toLowerCase().substring(0, 5).equals("event")) {
            if (command.length() == 5) {
                throw new DukeException("     ☹ OOPS!!! The description and timing of a event cannot be empty.");
            }
            String[] infoArray = command.substring(6).split("/at ", 2);
            Event e = new Event(infoArray[0], infoArray[1]);
            list.add(e);
            System.out.println("     Got it. I've added this task:");
            System.out.println(spacing + "  " + e.toString());
            System.out.println(String.format("     Now you have %d tasks in the list.", list.size()));
            storage.writeFile(e);
        } else if (command.length() > 5 && command.toLowerCase().substring(0, 6).equals("delete")) {
            if (command.length() == 6) {
                throw new DukeException("     ☹ OOPS!!! There must be something to be deleted");
            }
            Task item = list.get(Integer.parseInt(command.substring(7)) - 1);
            System.out.println("     Noted. I've removed this task:");
            System.out.println(spacing + "  " + item.toString());
            list.remove(Integer.parseInt(command.substring(7)) - 1);
            System.out.println(String.format("     Now you have %d tasks in the list.", list.size()));
            storage.updateFile(list);
        } else {
            throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
