import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandDone extends Command {
    public static final String KEYWORD = "done";
    private static final String ARG_FORMAT = "\\d";
    private ArrayList<String> arguments;


    public CommandDone(ArrayList<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public boolean isArgumentValid() {
        if (arguments.size() == 1) {
            Pattern pattern = Pattern.compile(ARG_FORMAT);
            Matcher matcher = pattern.matcher(arguments.get(0));
            return matcher.matches();
        } else {
            return false;
        }
    }

    @Override
    public void execute(TaskList tl, Storage st, Ui ui) {
        if (isArgumentValid()) {
            int number = Integer.parseInt(arguments.get(0)) - 1;
            if (number + 1 <= tl.numberOfTasks() && number + 1 > 0) {
                tl.markAsDone(number);
                ui.printout("Nice, I've marked this task as done!\n" + tl.getTaskString(number));
            } else {
                throw new DukeException("That task does not exist!");
            }
        } else {
            throw new DukeException("Invalid argument for command: done");
        }
    }

}
