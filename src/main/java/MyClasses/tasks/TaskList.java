/**
 * Parent Class of TaskList Object class define the type of TaskList
 * Normal TaskList
 */

package MyClasses.tasks;


public class TaskList {
    private static final String HoriLine = "--------------------------------------------------\n";
    protected String taskList;
    protected Boolean taskStatus;
    private String Type = "Task";

    //Constructor when the task is specified
    public TaskList(String Task) {
        this.taskList = Task;
        this.taskStatus = false;
        System.out.println("Added : " + Task + "\n" + HoriLine);
    }

    //Constructor when the task is specified including the status of the task
    public TaskList(String Task, Boolean Status) {
        this.taskList = Task;
        this.taskStatus = Status;
    }

    //to return the task when requested
    public void ListTask(int i) {
        String temp = this.taskStatus ? "\u2713" : "\u2613";
        System.out.println(i + 1 + ".[" + Type.charAt(0) + "]" + "[" + temp + "] " + this.taskList);
    }

    //To format the task into saving format
    public String Save() {
        return Type.trim() + " | " + this.taskStatus + " | " + this.taskList.trim();
    }

    //To mark the task as done
    public void DoneTask() {
        this.taskStatus = true;
        System.out.println(" Nice! I've marked this task as done : ");
        System.out.println("[\u2713] " + this.taskList);
        System.out.println("\n" + HoriLine);
    }

    public boolean FindTask(String searchCriteria,String type){
        String field = searchCriteria.substring(searchCriteria.indexOf("<") + 1, searchCriteria.indexOf(">"));
        if(type.contains("T")){
            return this.taskList.contains(field);
        }
        return false;
    }
}