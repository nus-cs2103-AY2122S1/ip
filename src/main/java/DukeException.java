public class DukeException extends Exception {
    
    public DukeException(String err) {
        switch (err) {
            case "deadlineDesc":
                System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                break;
            case "eventDesc":
                System.out.println("OOPS!!! The description of an event cannot be empty.");
                break;
            case "todoDesc":
                System.out.println("OOPS!!! The description of a todo cannot be empty.");
                break;
            case "invalidInput":
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :(");
                break;
            default:
                System.out.println("Duck has run into an unspecified error!");
                break;
        }
    }
    
}
