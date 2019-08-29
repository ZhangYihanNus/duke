import java.util.*;

public class Duke {

    static Scanner userInput = new Scanner(System.in);

    public Duke() {
        String startDuke = "____________________________________________________________\n" +
                "   Hello! I'm Duke\n" +
                "   What can I do for you?\n" +
                "____________________________________________________________";

        System.out.println(startDuke);
        Task[] lines = new Task[100];
        int i = 0;

         while(true) {
             String cmd = userInput.nextLine();
             if(cmd.equals("bye")) {
                 String bye = "____________________________________________________________\n" +
                         "\t"+"Bye! See you next time!\n" +
                         "____________________________________________________________";
                 System.out.println(bye);
                 break;
             } else if(cmd.equals("list")){
                 System.out.println("____________________________________________________________\n");
                 for(int j=0; j<i; j++) {
                     String index = new Integer(j+1).toString();
                     String thisCmd = lines[j].getDescription();
                     String thisState = lines[j].getStatusIcon();
                     System.out.println("\t" + index + ". [" + thisState + "] " + thisCmd + "\n");
                 }
                 System.out.println("____________________________________________________________\n");
             } else if((cmd.split(" ").length == 2) && (cmd.split(" ")[0].equals("done"))){
                 String cmdTarget = cmd.split(" ")[1];
                 int intTarget = Integer.parseInt(cmdTarget);
                 lines[intTarget-1].markAsDone();
                 String thisCmd = lines[intTarget-1].getDescription();
                 String thisState = lines[intTarget-1].getStatusIcon();
                 String userCmd = "____________________________________________________________\n" +
                         "Nice! This task is done!\n" +
                         "\t["+thisState+"] "+thisCmd+"\n" +
                         "____________________________________________________________";
                 System.out.println(userCmd);
             } else {
                 Task thisTask = new Task(cmd);
                 lines[i] = thisTask;
                 i++;
                 String userCmd = "____________________________________________________________\n" +
                         "\tadded: "+cmd+"\n" +
                         "____________________________________________________________";
                 System.out.println(userCmd);
             }


        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke theDuke = new Duke();
    }

}
