import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;

public class Brain {
    public Brain() {}

    public void listItems(DataStore dataStore, Ui userInt) {
        userInt.say("Here are your tasks: ");
        for (int i = 0; i < dataStore.length(); i++) {
            Task task = dataStore.get(i);

            if (task.getStatus()) {
                userInt.say(i+1 + ". " + task.toString());
            } else {
                userInt.say(i+1 + ". " + task.toString());
            }
        }
    }

    /**
     * 
     * Returns the parsed LocalDate object using the given string date.
     * 
     * @param date The date to be parsed in String format.
     * @return the LocalDate representation of the string date.
     */
    public String processDate(String date) {
        date = date.strip();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime datetime = LocalDateTime.parse(date, formatter);
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        String finalTime = datetime.format(newFormatter);
        System.out.println(finalTime);
        
        return finalTime;
    }

    public void createTodo(String input, DataStore dataStore, Ui userInt) {
        String query = input.split(" ", 2)[1].strip();
        ToDo todo = new ToDo(query);
        dataStore.add(todo);

        userInt.say("Got it. I've added this task: ");
        userInt.say("\t" + todo.toString());
        userInt.say("Now you have " + dataStore.length() + " tasks in the list.");
    }

    public void createDeadline(String input, DataStore dataStore, Ui userInt) {
        input = input.split(" ", 2)[1].strip();
        String query = input.split("/by")[0];
        String limit = input.split("/by")[1];
        String procLimit = processDate(limit);

        Deadline deadlineTask = new Deadline(query, procLimit);
        dataStore.add(deadlineTask);

        userInt.say("Got it. I've added this task: ");
        userInt.say("\t" + deadlineTask.toString());
        userInt.say("Now you have " + dataStore.length() + " tasks in the list.");        
    }

    public void createEvent(String input, DataStore dataStore, Ui userInt) {
        input = input.split(" ", 2)[1].strip();
        String query = input.split("/at")[0];
        String datetime = input.split("/at")[1];
        String procDate = processDate(datetime);

        Deadline deadlineTask = new Deadline(query, procDate);
        dataStore.add(deadlineTask);

        userInt.say("Got it. I've added this task: ");
        userInt.say("\t" + deadlineTask.toString());
        userInt.say("Now you have " + dataStore.length() + " tasks in the list.");        
    }

    public void completeTask(String input, DataStore dataStore, Ui userInt, String warning) throws BotException {
        int idx = Integer.parseInt(input.split(" ")[1]);
            
        // check if index is within appropriate range
        if (idx < 0 || idx > dataStore.length()) {
            throw new BotException(warning);
        } else {
            Task task = dataStore.get(idx-1);
            task.setDone();
            userInt.say("Nice! I've marked this task as done: ");
            userInt.say("\t" + task.toString());
        }        
    }

    public void shutdownBot(Ui userInt, DataStore dataStore, MemoryBuffer memBuff) {
        // save current task log 
        try {
            memBuff.writeFile(dataStore);
        } catch (IOException e) {
            userInt.say("OOPS!!! There was a problem saving your tasks.");
        }

        userInt.say("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    public void deleteTask(String input, DataStore dataStore, Ui userInt) {
        int idx = Integer.parseInt(input.split(" ")[1]);
        Task task = dataStore.get(idx-1);
        
        userInt.say("Noted. I've removed this task:");
        userInt.say("\t" + task.toString());
        dataStore.remove(idx-1); // actual deletion
        userInt.say("Now you have " + (dataStore.length()) + " tasks in the list.");
    }
}
