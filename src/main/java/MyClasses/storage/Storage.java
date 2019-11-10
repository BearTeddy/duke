/**
 * Class for saving the tasks into file and reading the tasks from file
 *
 * @author BearTeddy
 */

package MyClasses.storage;

import MyClasses.tasks.DeadLines;
import MyClasses.tasks.Events;
import MyClasses.tasks.TaskList;
import MyClasses.ui.Process;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Storage {

    //Filepath of the txt file we are saving the tasklists
    private static final String FILEPATH = "/Users/HtunHtetMyat/IdeaProjects/duke/src/main/java/data/duke.txt";

    //To save the tasks into the file in specified file path
    public static void SaveFile(ArrayList<TaskList> tasks) {
        try {
            FileWriter fw = new FileWriter(FILEPATH);
            for (TaskList task : tasks) {
                fw.write(task.Save() + "\n");
            }
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //To load the saved tasks from the file into the tasklist array
    public static void LoadFile() {
        try {
            FileInputStream fis = new FileInputStream(FILEPATH);
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                LoadCommand(sc.nextLine());
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //To process the String Line and split into tasktypes into the tasklist array
    private static void LoadCommand(String s) {
        ArrayList<String> commands = new java.util.ArrayList<String>(Arrays.asList(s.split(" ", 7)));
        TaskList task = null;
        try {
            if (commands.get(0).equals("Task")) {
                task = new TaskList(commands.get(4), Boolean.parseBoolean(commands.get(2)));
            } else if (commands.get(0).equals("Deadline")) {
                if (commands.size() >= 5) {
                    task = new DeadLines(commands.get(4), commands.get(6), Boolean.parseBoolean(commands.get(2)));
                } else {
                    task = new DeadLines(commands.get(4), Boolean.parseBoolean(commands.get(2)));
                }
            } else if (commands.get(0).equals("Event")) {
                if (commands.size() >= 5) {
                    task = new Events(commands.get(4), commands.get(6), Boolean.parseBoolean(commands.get(2)));
                } else {
                    task = new Events(commands.get(4), Boolean.parseBoolean(commands.get(2)));
                }
            }
            Process.AddTask(task);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



