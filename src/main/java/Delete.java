public class Delete extends Responses {

    private static String removeFromList(int taskIndex) throws DukeException {
        try {
            Task t = Responses.list.get(taskIndex - 1);
            Responses.list.remove(t);
            return t.toString();
        } catch (NullPointerException e) {
            throw new DukeException("Array Index Out of Bounds");
        }
    }

    public static void chat(String dResponse) throws DukeException {
        try {
            String[] dResponseList = dResponse.split(" ");
            int taskToDelete = Integer.parseInt(dResponseList[1]);
            Responses.interact(String.format("\tNoted. I've removed this task:\n\t %s\n\tNow you have %d tasks in the list\n", removeFromList(taskToDelete), Responses.list.size()));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Array Index Out of Bounds");
        }
    }

}
