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

import com.sun.tools.jconsole.JConsoleContext;
import org.apache.commons.lang3.*;
import org.apache.commons.lang3.time.DateParser;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class Duke {


    /**
     * Main Method of the Project, uses Utility Class and Storage Class
     *
     * @param args
     * @throws FileNotFoundException
     */

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        Utility.WelcomeMessage();
        Storage.LoadFile();
        while (Process.Task(Utility.ReadText())) ;
    }
}
