public class Ui {

    /**
     * Method for Petal to say goodbye. In the case saveTasks() throws an error,
     * Petal does not save any of the tasks.
     */
    public void goodBye() {
        output(Responses.GOODBYE);
    }

    /**
     * Method to add the indentation to the message
     * @param message Message to be printed
     */
    public void output(Responses message) {
        System.out.println(Responses.LINE + "\n" + message.toString() + "\n" + Responses.LINE);
    }

    /**
     * Method to add the indentation to the message
     * @param message Message to be printed
     */
    public void output(String message) {
        System.out.println(Responses.LINE + "\n" + message + "\n" + Responses.LINE);
    }

}
