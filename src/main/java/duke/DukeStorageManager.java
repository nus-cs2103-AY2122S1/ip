
package duke;

import duke.Tasks.BaseTask;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * This class encapsulates the local storage functions of Duke.
 */
public class DukeStorageManager {

    /** The local storage file. */
    File saveFile;

    /** The Document (XML) file after the saveFile is loaded */
    Document xmlSaveFileDoc;

    /**
     * Loads storage file from the provided path.
     * If failed then calls the method to create a new blank file instead.
     * @param savePath the path to the save file.
     */
    public DukeStorageManager(Path savePath) throws DukeExceptionBase {
        try {
            this.saveFile = savePath.toFile();
//        System.out.println(saveFile.exists());
//        System.out.println(saveFile.getAbsolutePath());

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            // Setting the error handler for the xml parser makes it such that when there is an
            // error in the XML file, instead of a line saying "Fatal Error" printing in the console
            // along with the SAXParseException being thrown, only the Exception is thrown.

            // This stops "Fatal Error" from printing in console when a XML file with invalid contents is used
            // because Duke is able to handle invalid XML files by creating a new file, thus printing the
            // "Fatal Error" is not needed since this error has been foreseen and can be automatically handled.
            // The SAXParseException is still thrown and caught as shown in the lines below,
            // and when it happens, a new save file will be created instead.
            documentBuilder.setErrorHandler(null);

            this.xmlSaveFileDoc = documentBuilder.parse(this.saveFile);


        } catch (ParserConfigurationException parserE) {
            this.failedToLoadSaveFile();
        } catch (SAXException e) {
            this.failedToLoadSaveFile();
        } catch (IOException ioException) {
            this.failedToLoadSaveFile();
        } finally {
            if (this.xmlSaveFileDoc == null) {
                throw new DukeExceptionBase("Failed to load saved XML File.");
            } else {
                return;
            }
        }
    }

    /**
     * Creates a new blank file for use as storage instead of loading.
     */
    public DukeStorageManager() {
        this.createBlankSaveFile();
    }

    private void failedToLoadSaveFile() throws DukeExceptionBase {
        throw new DukeExceptionBase("Failed to load saved XML File.");
    }

    private void createBlankSaveFile() {

    }



    /**
     * Triggers saving of the current state of the task list as a XML file.
     *
     * @param listOfTasks The ArrayList containing the tasks to be saved.
     */
    public void saveCurrentTasks(ArrayList<BaseTask> listOfTasks) {

    }

}