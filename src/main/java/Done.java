public class Done extends Responses{

    private static String removeFromList(int taskIndex) {
        Task t = Responses.list[taskIndex - 1];
        t.finishTask();
        return t.toString();
    }

    public static void chat(String dResponse) {
        String[] dResponseList = dResponse.split(" ");
        int taskToDelete = Integer.parseInt(dResponseList[1]);
        Responses.interact(String.format("\tNice! I've marked this task as done: \n\t%s\n", removeFromList(taskToDelete)));
    }

}
