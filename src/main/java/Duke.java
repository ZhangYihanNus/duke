import java.util.*;

public class Duke {

    private static Scanner userInput = new Scanner(System.in);

    private Task[] taskList = new Task[100];
    private String[] taskFileList = new String[100];

    public Duke() throws DukeCmdException, DukeFormatException {
        ReadTxt fileOpt = new ReadTxt("C:\\Users\\Min\\Desktop\\o\\NUS\\1920-Year2-sem-1\\courses\\CS2113T\\duke\\localList.txt");
        fileOpt.readFile();
        this.taskFileList = fileOpt.getLines();
        String[] rawList = this.taskFileList;

        int i=0;
        while(rawList[i] != null) {
            String thisLine = rawList[i];
            String[] lineItems = thisLine.split(" \\| ");
            if(lineItems[0].equals("T")) {
                // this is a todo
                Todo thisTd = new Todo("todo "+lineItems[2]);
                if(lineItems[1].equals("1")) thisTd.markAsDone();
                this.taskList[i] = thisTd;
                i++;
            } else if (lineItems[0].equals("E")) {
                // this is an event
                Event thisEvt = new Event("event "+lineItems[2], lineItems[3]);
                if(lineItems[1].equals("1")) thisEvt.markAsDone();
                this.taskList[i] = thisEvt;
                i++;
            } else if (lineItems[0].equals("D")) {
                // this is a deadline
                Deadline thisDdl = new Deadline("deadline "+lineItems[2], lineItems[3]);
                if(lineItems[1].equals("1")) thisDdl.markAsDone();
                this.taskList[i] = thisDdl;
                i++;
            }
        }

        String startDuke = "____________________________________________________________\n" +
                "   Hello! I'm Duke\n" +
                "   What can I do for you?\n" +
                "____________________________________________________________";

        System.out.println(startDuke);
        
        //int i = 0;
        boolean listChanged;

         while(true) {
             listChanged = false;
             String fileInputLine="";
             String cmd = userInput.nextLine();
             if(cmd.equals("bye")) {
                 String bye = "____________________________________________________________\n" +
                         "\t"+"Bye! See you next time!\n" +
                         "____________________________________________________________\n";
                 System.out.println(bye);
                 break;
             } else if(cmd.equals("list")){
                 System.out.println("____________________________________________________________\n\tHere are the tasks in the list:\n");
                 for(int j=0; j<i; j++) {
                     String index = Integer.toString(j + 1);
                     String thisCmd = this.taskList[j].toString();
                     System.out.println("\t" + index + ". " + thisCmd + "\n");
                 }
                 System.out.println("____________________________________________________________\n");
             } else if(cmd.split(" ")[0].equals("done")) {
                 if(cmd.split(" ").length != 2) {
                     //format error
                     System.out.println("____________________________________________________________\n" +
                             "Oops, there seems to be some format error!\n" +
                             "please enter again! :)\n" +
                             "____________________________________________________________\n");
                 } else {
                     int itemDone = Integer.parseInt(cmd.split(" ")[1]);
                     this.taskList[itemDone-1].markAsDone();
                     listChanged = true;
                     fileInputLine = rawList[itemDone-1];
                     String temp1 = fileInputLine.split("\\| 0 \\|")[0];
                     String temp2 = fileInputLine.split("\\| 0 \\|")[1];
                     fileInputLine = temp1 + "| 1 |" + temp2;
                     rawList[itemDone-1] = fileInputLine;
                     System.out.println("____________________________________________________________\n" +
                             "Task " + itemDone + " marked as done!\n" +
                             "____________________________________________________________\n");
                 }
             } else if(cmd.split(" ")[0].equals("deadline")){
                 String ddlItem, ddlTime;
                 if(cmd.split("/by ").length == 2) {
                     ddlTime = cmd.split("/by ")[1];
                 } else {
                     ddlTime = "";
                 }
                 String ddlFront = cmd.split(" /by ")[0];
                 if(ddlFront.split("deadline ").length == 2) {
                     ddlItem = ddlFront;
                 } else {
                     ddlItem = "";
                 }

                 Deadline thisDdl = null;
                 try {
                     thisDdl = new Deadline(ddlItem, ddlTime);
                 } catch (DukeFormatException e) {
                     System.out.println("____________________________________________________________\n" +
                             e.getMessage() +
                             "\nplease enter again! :)\n" +
                             "____________________________________________________________\n");
                     continue;
                 }
                 this.taskList[i] = thisDdl;
                 listChanged = true;
                 fileInputLine = "D | 0 | " + ddlItem.split("deadline ")[1] + " | " + ddlTime;
                 rawList[i] = fileInputLine;
                 i++;
                 String thisItem = thisDdl.toString();
                 String reply = "____________________________________________________________\n\tAdded deadline into list:\n" +
                         "\t"+thisItem+"\n" +
                         "\tCurently "+i+" items in the list"+"\n" +
                         "____________________________________________________________\n";
                 System.out.println(reply);
             } else if(cmd.split(" ")[0].equals("todo")){
                 String todoItem;
                 if(cmd.split("todo ").length == 2) {
                     todoItem = cmd;
                 } else {
                     todoItem = "";
                 }

                 Todo thisTodo = null;
                 try {
                     thisTodo = new Todo(todoItem);
                 } catch (DukeFormatException e) {
                     System.out.println("____________________________________________________________\n" +
                             e.getMessage() +
                             "\nplease enter again! :)\n" +
                             "____________________________________________________________\n");
                     continue;
                 }
                 this.taskList[i] = thisTodo;
                 listChanged = true;
                 fileInputLine = "T | 0 | " + todoItem.split("todo ")[1];
                 rawList[i] = fileInputLine;
                 i++;
                 String thisItem = thisTodo.toString();
                 String reply = "____________________________________________________________\n\tAdded todo into list:\n" +
                         "\t"+thisItem+"\n" +
                         "\tCurently "+i+" items in the list"+"\n" +
                         "____________________________________________________________\n";
                 System.out.println(reply);
             } else if(cmd.split(" ")[0].equals("event")) {
                 String evtItem, evtTime;
                 if(cmd.split("/at ").length == 2) {
                     evtTime = cmd.split("/at ")[1];
                 } else {
                     evtTime = "";
                 }
                 String evtFront = cmd.split(" /at ")[0];
                 if(evtFront.split("event ").length == 2) {
                     evtItem = evtFront;
                 } else {
                     evtItem = "";
                 }

                 Event thisEvt = null;
                 try {
                     thisEvt = new Event(evtItem, evtTime);
                 } catch (DukeFormatException e) {
                     System.out.println("____________________________________________________________\n" +
                             e.getMessage() +
                             "\nplease enter again! :)\n" +
                             "____________________________________________________________\n");
                     continue;
                 }
                 this.taskList[i] = thisEvt;
                 listChanged = true;
                 fileInputLine = "E | 0 | " + evtItem.split("event ")[1] + " | " + evtTime;
                 rawList[i] = fileInputLine;
                 i++;
                 String thisItem = thisEvt.toString();
                 String reply = "____________________________________________________________\n\tAdded event into list:\n" +
                         "\t" + thisItem + "\n" +
                         "\tCurently "+i+" items in the list"+"\n" +
                         "____________________________________________________________\n";
                 System.out.println(reply);
             } else {
                 //generate a new task, then activate exception there(not here)
                 try {
                     Task falseTask = new Task(cmd);
                 }
                 catch (DukeFormatException | DukeCmdException e) {
                     System.out.println("____________________________________________________________\n" +
                             e.getMessage() +
                             "\nplease enter again! :)\n" +
                             "____________________________________________________________\n");
                     continue;
                 }
             }

             // update list in the txt file
             if(listChanged) {
                 fileOpt.clearFile();
                 fileOpt.setLines(rawList);
                 fileOpt.writeFile();
             }
        }


    }



    public static void main(String[] args) throws DukeCmdException, DukeFormatException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        Duke theDuke = new Duke();
    }

}
