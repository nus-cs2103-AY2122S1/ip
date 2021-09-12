# User Guide

## Setting Up Locally

### Prerequisites
* JDK 11
* Any IDE that can run Java
  * Recommended: IntelliJ IDE, update to the most recent version.



### Importing into IntelliJ

1. Open IntelliJ (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into IntelliJ as follows:
   1. Click `Git | Clone`.
   2. In the `Get from Version Control` dialog, choose GitHub on the left.
   3. Copy and paste the cloning URL of this repository into the URL field.
   4. In the `Directory` field, enter the path to the folder where your local copy will be created.
   5. Click `Clone`, then `Yes` in the confirmation dialog.
3. Configure the project to use `JDK 11 (specifically)` as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
4. Click `OK`.
5. You are done!

## Commands
1. `list` - list all the tasks you have currently.
2. `delete {task number}` - deletes the task from your list corresponding to its task number.
3. `done {task number}` - marks a task in your list as done corresponding to its task number.
4. `todo {description} p={priority level}` - adds a "todo" task to your list with a given description and priority level of H/M/L.
5. `deadline {description} /by {date and time} p={priority level}` - adds a "deadline" task to your list with a given description, deadline and priority level of H/M/L.
   * date and time should be in the following format: "dd/mm/yyyy HHMM"
6. `event {description} /at {day} p={priority level}` - adds a "event" task to your list with a given description, day and priority level of H/M/L.
   * at should be a given day, i.e Friday.
7. `find {keyword}` - Returns tasks that contain a given keyword.
8. `bye` - exits the chatbot.

## Feedback and Bug Reports
* Please feel free to give us any feedback or report any problems you faced.
  * Create a new issue under the `Issues` tab in our repo.
* If you wish to contribute customisations or extensions to our project, please feel free to create pull requests!