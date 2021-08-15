// Class to handle formatting of responses in text ui
public class Response {
    private String content;
    
    public Response(String s) {
        this.content = s;
    }

    public static Response added(String item) {
        String msg = "added: " + item;
        return new Response(msg);
    }

    public static Response start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";

        return new Response(logo + "Hello there, I'm Duke!\nWhat can I do for you?");
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
