public class Action extends Command {

    private final Parser.Type type;
    private final String[] words;

    Action(Parser.Type type, String[] words) {
        this.type = type;
        this.words = words;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (this.words.length != 2) {
            throw new InvalidFormatException("`" + this.type.toString().toLowerCase() + " ${i}`");
        } else {
            int index;
            try {
                index = Integer.parseInt(this.words[1]);
            } catch (NumberFormatException ex) {
                throw new InvalidIntegerException();
            }
            if (index < 1 || index > taskList.size()) {
                throw new InvalidTaskNumberException();
            } else {
                if (this.type == Parser.Type.DONE) {
                    Task t = taskList.get(index - 1);
                    t.markAsDone();
                    ui.print("Nice, I've marked this task as done!\n   " +
                            t.toString());
                } else if (this.type == Parser.Type.DELETE) {
                    Task t = taskList.remove(index - 1);

                    String plurality = " task";
                    if (taskList.size() != 1) {
                        plurality += "s";
                    }

                    ui.print("Noted, I've removed this task:\n   " +
                            t.toString() + "\nNow you have " + taskList.size()
                            + plurality + " in the list.");
                }
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
