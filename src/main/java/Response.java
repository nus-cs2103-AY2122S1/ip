// Class to handle String responses in text ui
public class Response {
    private String content;
    
    private Response(String s) {
        this.content = s;
    }

    private Response(Tasklist list) {
        this.content = list.toString();
    }

    public static Response added(Task item) {
        String msg = "added: " + item.getDescription();
        Response response = new Response(msg);
        System.out.println(response);
        return response;
    }

    public static Response completed(Task item) {
        String msg = "Nice! I've marked this task as done:\n" + item.toString();
        Response response = new Response(msg);
        System.out.println(response);
        return response;
    }

    public static Response listAllItems(Tasklist list) {
        Response response = new Response("Here are the tasks in your list:\n" + list.toString());
        System.out.println(response);
        return response;
    }
    
    public static Response start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";

        Response response = new Response(logo + "Hello there, I'm Duke!\nWhat can I do for you?");
        System.out.println(response);
        return response;
    }

    public static Response exit() {
        Response response = new Response("Bye! Come back again!");
        System.out.println(response);
        return response;
    }

    @Override
    public String toString() {
        String linebreak = "\t_______________________________________________\n";
        
        String[] splitByLines = this.content.split("\n");
        String rawContent = "";

        for (int i = 0; i < splitByLines.length; i++) {
            rawContent += ("\t" + splitByLines[i] + "\n"); 
        }
        
        String result = (linebreak + rawContent + linebreak);

        return result;
    }
}
