public class Done extends Responses{

    private static String removeFromList(int taskIndex) throws DukeException {
        try {
            Task t = Responses.list[taskIndex - 1];
            t.finishTask();
            return t.toString();
        } catch (NullPointerException e) {
            throw new DukeException("Array Index Out of Bounds");
        }
    }

    public static void chat(String dResponse) throws DukeException {
        try {
            String[] dResponseList = dResponse.split(" ");
            int taskToDelete = Integer.parseInt(dResponseList[1]);
            Responses.interact(String.format("\tNice! I've marked this task as done: \n\t%s\n", removeFromList(taskToDelete)));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Array Index Out of Bounds");
        }
    }

}
