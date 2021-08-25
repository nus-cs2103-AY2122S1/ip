public class Done extends Responses{

    private static String checkOffFromList(int taskIndex) throws DukeException {
        try {
            Task t = Responses.list.get(taskIndex - 1);
            t.finishTask();
            return t.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Array Index Out of Bounds");
        }
    }

    public static void chat(String dResponse) throws DukeException {
        try {
            String[] dResponseList = dResponse.split(" ");
            int taskToDelete = Integer.parseInt(dResponseList[1]);
            Responses.interact(String.format("\tNice! I've marked this task as done:\n\t %s\n", checkOffFromList(taskToDelete)));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Array Index Out of Bounds");
        }
    }

}
