public class Ui {

    public void welcomeMessage() {
        System.out.println(
                "Yo! Duke here \n"
                        + "What did you call me for? \n"
                        + "It better be something useful or else... \n"
        );
    }

    public void loadingError() {
        System.out.println("Error loading tasks");
    }

    public void emptyList() {
        System.out.println("Your list is empty.");
    }

    public void invalidIndexMessage() {
        System.out.println("Invalid index, please try again");
    }

    public void formatExceptionMessage(WrongCommandFormatException e) {
        System.out.println(e.getMessage());
    }

    public void formatUpdatedMessage() {
        System.out.println(
                "Date format has been updated to: "
                        + Duke.getFormat()
        );
    }

    public void unacceptableFormatMessage() {
        System.out.println("Not an acceptable format. Please try again");
    }

    public void currentDateFormatMessage() {
        System.out.println("Current format " + Duke.getFormat());
    }

    public void noSpecificCmdMessage() {
        System.out.println("No specific command specified. Please try again");
    }

    public void botShutdownMessage() {
        System.out.println("Good riddance! Time to continue my beauty sleep :)");
    }
}
