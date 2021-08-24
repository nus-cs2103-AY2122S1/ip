public class Parser {
    private String splitRegex;

    public Parser(String splitRegex){
        this.splitRegex = splitRegex;
    }

    public Command parse(String input) throws DukeException{
        String words[] = input.split(this.splitRegex);
        if (words[0].equals("list")) {
            System.out.println("Here are the tasks in your list:");
            return new ListCommand();
        } else if(words[0].equals("done")){
            int doneIndex = Integer.parseInt(words[1]);
            return new MarkAsDoneCommand(doneIndex - 1);
        } else if(words[0].equals("delete")){
            int deleteIndex = Integer.parseInt(words[1]);
            return new DeleteCommand(deleteIndex - 1);
        } else {
            Task task = new Task();
            if(words[0].equals("todo")){
                if(words.length <= 1){
                    throw new DukeException("The description of a todo " +
                            "cannot be empty.");
                }
                task = new ToDo(input.substring(5));
            }else if (words[0].equals("deadline")) {
                if(words.length <= 1 || words[1].equals("/by")){
                    throw new DukeException("The description of deadline task " +
                            "cannot be empty.");
                }
                if(input.indexOf("/by") < 0){
                    throw new DukeException("Please enter '/by' followed by a task deadline.");
                }
                String content = input.substring(9, input.indexOf("/by") - 1);
                if(input.indexOf("/by") + 4 >= input.length()){
                    throw new DukeException("Please enter a deadline.");
                }
                String by = input.substring(input.indexOf("/by") + 4);
                task = new Deadline(content, by);
            } else if (words[0].equals("event")) {
                if(words.length <= 1 || words[1].equals("/at")){
                    throw new DukeException("The description of event " +
                            "cannot be empty.");
                }
                if(input.indexOf("/at") < 0){
                    throw new DukeException("Please enter '/at' followed by an event time.");
                }
                String content = input.substring(6, input.indexOf("/at") - 1);
                if(input.indexOf("/at") + 4 >= input.length()){
                    throw new DukeException("Please provide the event time.");
                }
                String at = input.substring(input.indexOf("/at") + 4);
                task = new Event(content, at);
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
            return new AddCommand(task);
        }
    }
}
