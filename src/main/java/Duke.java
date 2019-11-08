/**
 * <h>Duke</h>
 * <p>
 * This is the Tasks List Scheduler/Handler console based Application.
 * </p>
 *
 * @author BearTeddy
 * @version 1.0
 * @since 2019-Nov-01
 */

import MyClasses.storage.Storage;
import MyClasses.ui.Process;
import MyClasses.ui.Utility;

import java.io.FileNotFoundException;

public class Duke {


    /**
     * Main Method of the Project, uses Utility Class and Storage Class
     *
     * @param args
     * @throws FileNotFoundException
     */

    public static void main(String[] args) throws FileNotFoundException {
        Utility.WelcomeMessage();
        Storage.LoadFile();
        while (Process.Task(Utility.ReadText())) ;
    }

}
