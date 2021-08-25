public class List extends Responses{

    private static String printList(String date) {
        String response = "\tHere are the tasks in your list:\n";
        for (int i = 0; i < Responses.list.size(); i++) {
            Task t = Responses.list.get(i);
            if (!date.isEmpty() && !t.toString().contains(date)) {
                break;
            } 
            response += String.format("\t%d. %s\n", i + 1, t);
        }
        return response;
    }

    public static void chat(String uResponse) {
        String description = uResponse.replaceAll("list", "").trim();
        if (description.isEmpty()) {
            Responses.interact(printList(""));
        } else {
            Responses.interact(printList(description));
        }

    }

}
