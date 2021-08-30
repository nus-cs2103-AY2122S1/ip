public class Commands {

    public Commands() {}

    public void doneTask(String input, Storage storage, TaskList ls) throws DukeException {
        if (input.equals("done") || input.equals("done ")) {
            throw new DukeException("An index must follow after the command word 'done'.");
        } else {
            int arrIndex = Integer.valueOf(input.substring(5)) - 1;
            if (arrIndex < 0 || arrIndex >= ls.getSize()) {
                throw new DukeException("Item does not exist in the list.");
            } else {
                Task task = ls.getTask(arrIndex);
                task.markAsDone();
                storage.rewriteFile(ls);
            }
        }
    }
    public void doneTask(String input, Storage storage, TaskList ls) throws DukeException {
        if (input.equals("delete") || input.equals("delete ")) {
            throw new DukeException("An index must follow after the command word 'delete'.");
        } else {
            int arrIndex = Integer.valueOf(input.substring(7)) - 1;
            if (arrIndex < 0 || arrIndex >= ls.getSize()) {
                throw new DukeException("Item does not exist in the list.");
            } else {
                ls.removeTask(arrIndex);
                storage.rewriteFile(ls);
            }
        }
    }

}
