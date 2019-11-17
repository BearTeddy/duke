/**
 * Main Process of the program that deals with listing of Tasks
 *
 * @author BearTeddy
 */
package MyClasses.ui;

import MyClasses.storage.Storage;
import MyClasses.tasks.DeadLines;
import MyClasses.tasks.Events;
import MyClasses.tasks.TaskList;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

public class Process {

    //Temporary ArrayList to store Task List Objects
    private static ArrayList<TaskList> tasks = new ArrayList<>();

    //To Add task into tasks array if the parameter task is not null
    public static void AddTask(TaskList task) {
        if (task != null) tasks.add(task);
    }

    /**
     * To Figure out what type of tasks it represents
     * Task -t = normal tasks
     * Deadline -d = Deadline tasks
     * Events -e = Event tasks
     *
     * @param commands
     */
    public static void AllocateTaskType(ArrayList<String> commands) {
        if (commands.size() > 2 && commands.get(2) != null && !(commands.get(2).trim().isBlank())) {
            if (commands.get(1).equals("-t") || commands.get(1).equals("task")) {
                AddTask(new TaskList(commands.get(2)));
            }
            else if (commands.get(1).equals("-d") || commands.get(1).equals("deadline")) {
                String[] taskTime = commands.get(2).split("/by");

                try{
                    taskTime[1] = Utility.ProcessTime(taskTime[1]);
                    if(taskTime[1].equals("null"))
                    {
                        System.out.println("Incorrect Time format.\n Please see sample format by typing 'help time'");
                        Utility.PrintHL();
                    }else{
                        try {
                            AddTask(new DeadLines(taskTime[0], taskTime[1]));
                        } catch (IndexOutOfBoundsException e) {
                            AddTask(new DeadLines(taskTime[0]));
                        }
                    }
                }catch (IndexOutOfBoundsException e){
                    try {
                        AddTask(new DeadLines(taskTime[0], taskTime[1]));
                    } catch (IndexOutOfBoundsException err) {
                        AddTask(new DeadLines(taskTime[0]));
                    }
                }

            }
            else if (commands.get(1).equals("-e") || commands.get(1).equals("event")) {
                String[] taskTime = commands.get(2).split("/at");
                try{
                taskTime[1] = Utility.ProcessTime(taskTime[1]);
                if(taskTime[1].equals("null")){
                    System.out.println("Incorrect Time format.\n Please see sample format by typing 'help time'");
                    Utility.PrintHL();
                }else{
                    try {
                        AddTask(new Events(taskTime[0], taskTime[1]));
                    } catch (IndexOutOfBoundsException e) {
                        AddTask(new Events(taskTime[0]));
                    }
                }
                }catch (IndexOutOfBoundsException err){
                    try {
                        AddTask(new Events(taskTime[0], taskTime[1]));
                    } catch (IndexOutOfBoundsException e) {
                        AddTask(new Events(taskTime[0]));
                    }
                }
            }
        } else {
            System.out.println("OOPS!! Please type descriptions");
            Utility.PrintHL();
        }
    }

    //TO Exit the Duke process by exiting Infinite Loop in Duke.java -> Main
    public static boolean ExitProcess() {

        Storage.SaveFile(tasks);
        System.out.println("I have saved the current tasks. \n Bye Bye. Hope to see you again soon!");
        Utility.PrintHL();
        return false;
    }

    //To List out all the tasks avalible in temp tasklist
    public static void ListTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).ListTask(i);
        }
        Utility.PrintHL();
    }

    //To mark the specific task as done
    private static void MarkTest(ArrayList<String> commands) {
        try {
            if (Utility.isNumeric(commands.get(1))) {
                tasks.get((Integer.parseInt(commands.get(1)) - 1)).DoneTask();
            } else {
                System.out.println("Please provide the index number");
                Utility.PrintHL();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Item at the specified index does not exist");
        }
    }

    //To Remove the specific tasks at the specified index
    private static void RemoveTask(ArrayList<String> commands) {
        System.out.println("Noted! I have removed this task:");
        try {
            if (Utility.isNumeric(commands.get(1))) {
                tasks.get(Integer.parseInt(commands.get(1)) - 1).ListTask(0);
                tasks.remove(Integer.parseInt(commands.get(1)) - 1);
            } else {
                System.out.println(("Please type numeric only!"));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS! the task number you specified does not exists");
        }
    }

    /**
     * to find the tasks,deadlines and event using two types of input inside <> and ().
     * need <b>on</b> Keyword when trying to find date.
     * <> = text that trying to find
     * () = date/time trying to find
     * for example command will be = find <Hello> on (10-10-2019)
     * @param commands
     */
    private  static void FindTask(ArrayList<String> commands){
        ArrayList<TaskList> foundTasks = new ArrayList<>();
        if (commands.size() > 1 && commands.get(1) != null && !(commands.get(1).trim().isBlank())) {
            if (commands.get(1).equals("-t") || commands.get(1).equals("task")) {
                //Find inside tasks
                try{
                    for (int i = 0; i < tasks.size(); i++) {
                        if(tasks.get(i).FindTask(commands.get(2),"T")){
                            foundTasks.add(tasks.get(i));
                        }
                    }
                }catch (IndexOutOfBoundsException e){
                     System.out.println("Unknown Command");
                     Utility.PrintHL();
                }
            } else if (commands.get(1).equals("-d") || commands.get(1).equals("deadline")) {
                //Find inside deadline
                try{
                    for (int i = 0; i < tasks.size(); i++) {
                        if(tasks.get(i).FindTask(commands.get(2),"D")){
                            foundTasks.add(tasks.get(i));
                        }
                    }
                }catch (IndexOutOfBoundsException e){
                    System.out.println("Unknown Command");
                    Utility.PrintHL();
                }
            } else if (commands.get(1).equals("-t") || commands.get(1).equals("event")) {
                //find inside event
                try{
                    for (int i = 0; i < tasks.size(); i++) {
                        if(tasks.get(i).FindTask(commands.get(2),"E")){
                            foundTasks.add(tasks.get(i));
                        }
                    }
                }catch (IndexOutOfBoundsException e){
                    System.out.println("Unknown Command");
                    Utility.PrintHL();
                }
            } else if (commands.get(1).equals("on") || commands.get(1).equals("at")) {
                //find inside deadline and event
                try{
                    for (int i = 0; i < tasks.size(); i++) {
                        if(tasks.get(i).FindTask(commands.get(2),"DE")){
                            foundTasks.add(tasks.get(i));
                        }
                    }
                }catch (IndexOutOfBoundsException e){
                    System.out.println("Unknown Command");
                    Utility.PrintHL();
                }
            }else{
                try{
                    for (int i = 0; i < tasks.size(); i++) {
                        if(tasks.get(i).FindTask(commands.get(1)+commands.get(2),"TDE")){
                            foundTasks.add(tasks.get(i));
                        }
                    }
                }catch (IndexOutOfBoundsException e){
                    for (int i = 0; i < tasks.size(); i++) {
                        if(tasks.get(i).FindTask(commands.get(1),"TDE")){
                            foundTasks.add(tasks.get(i));
                        }
                    }
                }
            }
            for (int i = 0; i < foundTasks.size(); i++) {
                foundTasks.get(i).ListTask(i);
            }
            Utility.PrintHL();
        } else {
            System.out.println("Please type something to find");
            Utility.PrintHL();
        }
    }

    //Main process to make sense of what the command is trying to say
    public static boolean Task(String Cmmd) {

        ArrayList<String> commands = Utility.ReadCommand(Cmmd);
        switch (commands.get(0).toLowerCase()) {
            case "bye":
            case "quit":
            case "close":
            case "exit":
                return ExitProcess();
            case "list":
                ListTasks();
                break;
            case "done":
            case "finish":
                MarkTest(commands);
                break;
            case "add":
                AllocateTaskType(commands);
                break;
            case "find":
            case "search":
                    FindTask(commands);
                    break;
            case "remove":
            case "delete":
                RemoveTask(commands);
                break;
            case "save":
            case "save file":
                Storage.SaveFile(tasks);
                break;
            case "help time":
            case "time":
                Utility.TimeFormats();
                break;
            default:
                System.out.println("OOPS!! I'm Sorry. I do not know what you meant by \" " + Cmmd + " \" (╥﹏╥) .");
                Utility.PrintHL();
        }
        return true;
    }


}
