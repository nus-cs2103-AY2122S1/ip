
package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This class encapsulates the local storage functions of Duke.
 */
public class DukeStorageManager {

    /** The local storage file. */
    private File saveFile = null;

    /** The Document (XML) file after the saveFile is loaded. */
    private Document xmlSaveFileDoc = null;

    /** ArrayList to contain loaded tasks before sending over to ListManager. */
    private ArrayList<BaseTask> loadedTasks = new ArrayList<>();

    /**
     * Loads storage file from the provided path. If failed then calls the method to create a new blank file instead.
     *
     * @param savePath the path to the save file.
     * @throws DukeExceptionBase when there are errors while loading the save file.
     */
    public DukeStorageManager(Path savePath) throws DukeExceptionBase {
        try {
            this.saveFile = savePath.toFile();
            System.out.println("The save file: " + this.saveFile);
            if (!this.saveFile.exists()) {
                createBlankSaveFile();
                this.xmlSaveFileDoc = null;
                return;
            }

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            this.xmlSaveFileDoc = documentBuilder.parse(this.saveFile);

        } catch (ParserConfigurationException parserE) {
            this.failedToLoadSaveFile();
        } catch (SAXException e) {
            this.failedToLoadSaveFile();
        } catch (IOException ioException) {
            this.failedToLoadSaveFile();
        }

        if (this.xmlSaveFileDoc == null) {
            throw new DukeExceptionBase("Failed to load saved XML File.");
        }
    }

    /**
     * Creates a new blank file for use as storage instead of loading.
     */
    public DukeStorageManager() {
        this.createBlankSaveFile();
    }

    /**
     * Runs when an exception is thrown while trying to load or parse the XML Save File.
     *
     * @throws DukeExceptionBase as the Save File cannot be loaded.
     */
    private void failedToLoadSaveFile() throws DukeExceptionBase {
        throw new DukeExceptionBase("Failed to load saved XML File.");
    }

    /**
     * Creates a new blank save file if there is no save file already present in local storage.
     */
    private void createBlankSaveFile() {
        this.saveFile = new File("dukeDocs" + File.separator + "listSave1.xml");
        if (this.saveFile.exists()) {
            System.out.println("Unusable save file already exists at save file location.");
        } else {
            try {
                // Create parent directory if it does not exist yet.
                File dukeDocsFolder = this.saveFile.getParentFile();
                if (!dukeDocsFolder.exists()) {
                    dukeDocsFolder.mkdirs();
                }

                this.saveFile.createNewFile();
            } catch (IOException e) {
                System.out.println("UNABLE TO CREATE NEW SAVE FILE!! (IOException)");
            }
        }
    }

    /**
     * Reloads the save data from the XML doc obj into Duke's List Manager.
     */
    public void reloadSaveFromXmlDoc() {
        if (this.xmlSaveFileDoc == null) {
            System.out.println("No save file to load from.");
            return;
        }

        Element xmlRoot = xmlSaveFileDoc.getDocumentElement();
        NodeList taskList = xmlRoot.getElementsByTagName("taskList");

        // There should be only 1 taskList node
        if (taskList.getLength() > 1) {
            System.out.println("Loaded XML file contains more than 1 task list."
                      + " Only first one will be loaded.");
        }

        Node firstTaskList = taskList.item(0);
        NodeList taskAssetList = firstTaskList.getChildNodes();

        for (int id = 0; id < taskAssetList.getLength(); id++) {
            Node currTaskAsset = taskAssetList.item(id);

            this.processTaskNode(currTaskAsset);
        }

        // Send the loaded task list into the list manager.
        Duke.getCurrDuke().getCurrListMgr().loadTaskList(this.loadedTasks);

    }

    /**
     * Processes individual Task Node from XML Save File.
     *
     * @param currTaskAsset the relevant Task Node Object.
     */
    private void processTaskNode(Node currTaskAsset) {
        if (currTaskAsset.getNodeName().equals("taskAsset")) {
            // Confirms that this taskAsset has contents
            if (currTaskAsset.getNodeType() == Node.ELEMENT_NODE) {
                loadTaskAssetFromNode((Element) currTaskAsset);

            } else {
                System.out.println("Current loaded Task Asset is empty.");
            }
        } else {
            System.out.println("Non-TaskAsset found in taskList: " + currTaskAsset.getNodeName());
        }

    }

    private void loadTaskAssetFromNode(Element currTaskAsset) {
        // Convert to DOM Element so that we can get elements by Tag Name
        Element currTaskAssetElement = currTaskAsset;

        // Gets various contents common to all task types
        Node taskTypeNode = getFirstNodeByTagName(currTaskAssetElement, "taskType");
        Node taskDataNode = getFirstNodeByTagName(currTaskAssetElement, "taskData");
        Node taskCompletedNode = getFirstNodeByTagName(currTaskAssetElement, "taskCompleted");

        boolean isCurrTaskCompleted = taskCompletedNode.getTextContent().equals("true");

        processCurrLoadedTaskNode(currTaskAssetElement, taskTypeNode, taskDataNode, isCurrTaskCompleted);
    }

    private void processCurrLoadedTaskNode(Element currTaskAssetElement, Node taskTypeNode, Node taskDataNode,
                                           boolean isCurrTaskCompleted) {
        // Initialise Variables for Switch Statement
        BaseTask createdTask = null;

        // Get extra info for task if needed and create task
        BaseTask.TaskType currTaskType = BaseTask.convertTaskLetterToEnum(taskTypeNode.getTextContent());
        switch (currTaskType) {
        case TODO:
            createdTask = processCurrLoadedTodoTaskNode(taskDataNode, isCurrTaskCompleted);
            break;
        case DEADLINE:
            createdTask = processCurrLoadedDeadlineTaskNode(currTaskAssetElement, taskDataNode, isCurrTaskCompleted);
            break;
        case EVENT:
            createdTask = processCurrLoadedEventTaskNode(currTaskAssetElement, taskDataNode, isCurrTaskCompleted);
            break;
        case NONE:
            // Fallthrough
        default:
            System.out.println("Unknown Task Type Loaded: "
                    + currTaskType + " with data: " + taskDataNode.getTextContent());
            createdTask = null;
            break;
        }

        // In case of error while creating task - Don't add it to the list
        if (createdTask != null) {
            this.loadedTasks.add(createdTask);
        }
    }

    private BaseTask processCurrLoadedEventTaskNode(Element currTaskAssetElement, Node taskDataNode,
                                                    boolean isCurrTaskCompleted) {
        Node taskExtraInfoNode;
        BaseTask createdTask;
        taskExtraInfoNode = getFirstNodeByTagName(currTaskAssetElement, "taskExtraInfo");

        createdTask = new EventTask(taskDataNode.getTextContent(),
                taskExtraInfoNode.getTextContent(), isCurrTaskCompleted);
        return createdTask;
    }

    private BaseTask processCurrLoadedTodoTaskNode(Node taskDataNode, boolean isCurrTaskCompleted) {
        BaseTask createdTask;
        createdTask = new ToDosTask(taskDataNode.getTextContent(), isCurrTaskCompleted);
        return createdTask;
    }

    private BaseTask processCurrLoadedDeadlineTaskNode(Element currTaskAssetElement, Node taskDataNode,
                                                       boolean isCurrTaskCompleted) {
        Node taskExtraInfoNode;
        BaseTask createdTask;
        taskExtraInfoNode = getFirstNodeByTagName(currTaskAssetElement, "taskExtraInfo");

        try {
            createdTask = new DeadlineTask(taskDataNode.getTextContent(),
                    taskExtraInfoNode.getTextContent(), isCurrTaskCompleted);
        } catch (DukeExceptionBase e) {
            // If date format of this Deadline Task is wrong, then don't load it.
            createdTask = null;
        }
        return createdTask;
    }

    /**
     * Gets the first node in the XML heirarchy with the given tag name.
     *
     * @param parentEle the Parent Element Node to search.
     * @param tagName the tag to search for.
     * @return the first Node found in the Parent Element Node with the corresponding tagName.
     */
    private Node getFirstNodeByTagName(Element parentEle, String tagName) {
        NodeList tagNodeList = parentEle.getElementsByTagName(tagName);
        if (tagNodeList.getLength() == 0) {
            System.out.println("Node with tag: " + tagName + " not found.");
            return null;
        } else {
            return tagNodeList.item(0);
        }
    }




    /**
     * Triggers saving of the current state of the task list as a XML file.
     *
     * @param listOfTasks The ArrayList containing the tasks to be saved.
     */
    public void saveCurrentTasks(ArrayList<BaseTask> listOfTasks) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();

            // Create new XML File
            Document newXmlDoc = documentBuilder.newDocument();
            // Overwrite the current XML Save file in the Storage Manager
            this.xmlSaveFileDoc = newXmlDoc;
            // Create saveFile root tag
            Element saveFileRoot = newXmlDoc.createElement("saveFile");
            newXmlDoc.appendChild(saveFileRoot);
            // Create taskList container
            Element taskListElement = newXmlDoc.createElement("taskList");
            saveFileRoot.appendChild(taskListElement);

            int counter = 0;
            for (BaseTask currTask : listOfTasks) {
                Element convertedTaskAsset = this.createTaskAssetElement(currTask);
                convertedTaskAsset.setAttribute("id", Integer.toString(counter));
                taskListElement.appendChild(convertedTaskAsset);

                counter++;
            }
            // Now write the file to local storage
            this.writeCurrXmlDocToDisk();
        } catch (ParserConfigurationException e) {
            System.out.println("Document builder cannot be created. (List not saved)");
        }
    }

    /**
     * Converts a given Task into a format storable in the XML save file.
     *
     * @param currTask the respective task in question.
     * @return the task converted into an XML Element.
     */
    private Element createTaskAssetElement(BaseTask currTask) {
        BaseTask.TaskType currTaskType = currTask.getTaskType();
        Element createdTaskElement;

        String taskTypeStr = currTask.getTaskTypeStringHeader().substring(1, 2);
        String taskName = currTask.getTaskName();
        boolean isTaskDone = currTask.isTaskDone();

        // Create General Task Element
        createdTaskElement = this.xmlSaveFileDoc.createElement("taskAsset");

        // Create General Task Data Elements
        Element taskTypeElement = this.xmlSaveFileDoc.createElement("taskType");
        Element taskDataElement = this.xmlSaveFileDoc.createElement("taskData");
        Element taskCompletedElement = this.xmlSaveFileDoc.createElement("taskCompleted");

        taskTypeElement.setTextContent(taskTypeStr);
        taskDataElement.setTextContent(taskName);
        taskCompletedElement.setTextContent(Boolean.toString(isTaskDone));

        createdTaskElement.appendChild(taskTypeElement);
        createdTaskElement.appendChild(taskDataElement);
        createdTaskElement.appendChild(taskCompletedElement);

        createdTaskElement = createTaskXmlElement(currTask, currTaskType, createdTaskElement);

        return createdTaskElement;
    }

    private Element createTaskXmlElement(BaseTask currTask, BaseTask.TaskType currTaskType,
                                         Element createdTaskElement) {
        switch (currTaskType) {
        case TODO:
            // Do nothing extra
            break;
        case DEADLINE:
            // Fallthrough
        case EVENT:
            // Extra Task Data Processing
            Element taskExtraInfoElement = this.xmlSaveFileDoc.createElement("taskExtraInfo");

            taskExtraInfoElement.setTextContent(currTask.getTaskExtraInfo());
            createdTaskElement.appendChild(taskExtraInfoElement);
            break;
        case NONE:
            // Fallthrough
        default:
            System.out.println("Unidentified task type expected,"
                    + " cannot save this task: " + currTask.toString());
            createdTaskElement = null;
        }
        return createdTaskElement;
    }

    /**
     * Starts writing the current XML file in the Document thing to Disk.
     */
    private void writeCurrXmlDocToDisk() {
        if (this.xmlSaveFileDoc == null) {
            System.out.println("XML Document is null.");
        }

        try {
            // Use Transformer Factory to save files to local storage
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource xmlToWriteSource = new DOMSource(this.xmlSaveFileDoc);
            FileWriter currFileWriter = new FileWriter(this.saveFile);
            StreamResult currStreamResult = new StreamResult(currFileWriter);

            transformer.transform(xmlToWriteSource, currStreamResult);

        } catch (TransformerConfigurationException e) {
            System.out.println("Unable to create Transformer.");
        } catch (IOException e) {
            System.out.println("IO Exception");
        } catch (TransformerException e) {
            System.out.println("Error while using transformer.transform");
        }
    }

}
