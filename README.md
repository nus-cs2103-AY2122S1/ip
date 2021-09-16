# Carrot Task Manager (CS2103T Duke project) 
> If today is not the due day, today is not the do day 😊
> 
Welcome to Carrot Task Manager! 
Have you ever forgotten a deadline for homework or missed a test because you thought it was supposed to be on another day?
If `haveExperiencedAboved == true`, Carrot Task Manager is here to save you!


## The ultimate todo list 😄
Carrot Task Manager helps you keep track of your tasks and deadlines! Your tasks are put into a todo list and ultimately saved to a *file.txt* file on your device, so your list won't be gone even if you exit the app! 
Here are several functions of Carrot Task Manager and commands you can use:
* See what is in your todo list:
   * **list**
* Add a task to your list:
   * **todo** **task description**
   * **event** *task description* **/at** *time description*
   * **deadline** *task description* **/by** *time description*
* Delete a task from your list:
   * **delete** *task number*
* Mark a task as done:
   * **done** *task number*
* Close Duke:
   * **bye**
   
## Setting up Carrot Task Manager
All you need to do is:
1. download it from [here](https://github.com/chingh20/ip).
2. start adding your tasks!

## About the tech
Carrot Task Manager is a project done with 
- [X] Java
- [X] JavaFX 
   

If you are a Java programmer, you can use it to practice Java! Here's the main method:
```javascript
public class Main {
    public static void main(String[] args) {
        Application.launch(MainApp.class, args);
    }
}
```
