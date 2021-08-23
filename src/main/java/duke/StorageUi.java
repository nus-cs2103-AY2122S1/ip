package duke;

public class StorageUi implements Ui {
    private FormatAdapter adapter;
    public StorageUi() {
        this.adapter = new FormatAdapter();
    }

    public void fileAlreadyExistMessage() {
        System.out.println(adapter.formatMessage("This file already exists!\n"));
    }

    public void directoryAlreadyExistMessage() {
        System.out.println(adapter.formatMessage("This directory already exists!\n"));
    }

    public void unexpectedErrorMessage() {
        System.out.println(adapter.formatMessage("An unknown error has occurred.\n"));
    }

    public void saveNotFoundMessage() {
        System.out.println(adapter.formatMessage("Saved data not found, a new data file created.\n"));
    }

    public void invalidFilePathMessage() {
        System.out.println(adapter.formatMessage("Invalid file path detected, please try again.\n"));
    }

    public void saveSuccessfulMessage() {
        System.out.println(adapter.formatMessage("Save successfully.\n"));
    }

    public void loadSuccessfulMessage() {
        System.out.println(adapter.formatMessage("Load successfully.\n"));
    }

}
