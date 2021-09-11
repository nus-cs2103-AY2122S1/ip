package duke.ui;

// This interface was created to allow the Dynamic Invocation Handler to function

/**
 * Interface to encapsulate the Ui functionality.
 */
public interface UiInterface {
    void print(String message);
    void showWelcome(String welcomeMessage);
    void showBye(String endMessage);
    void showLoadingError(String error);
    void showError(String error);
    String readCommand();
}
