public class InvalidInputException extends DukeException {
    private String errorMessage;

    public InvalidInputException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    @Override
    void printErrorMessage() {
        if (errorMessage.equals("invalid task number entered")) {
            System.out.println("☹ OOPS!!! Please key in a valid task number within the list.");
        } else if (errorMessage.equals("error")) {
            System.out.println("☹ OOPS!!! Error occurred.");
        } else {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
