package Model;

/**
 * list of commands available for dude chatbot
 * <ol>
 *
 *  <li>
 *      BYE : to end the chat, no arguments requires, any extra arguments would be ignored, invoked using "bye"
 *  </li>
 *  <li>
 *      LIST : list the entire tasks array
 *  </li>
 *  <li>
 *      ADD : add the sentence to the storage
 *  </li>
 *  <li>
 *      INVALID : any other commands other than bye, the whole sentence would be echoed back instead
 *  </li>
 * </ol>
 */
public enum Command {
	BYE,
	LIST,
	ADD,
	DONE,
	INVALID // special command for invalid input
}
