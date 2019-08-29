import java.util.*;

public class Duke {

    static Scanner userInput = new Scanner(System.in);

    public Duke() {
        String startDuke = "____________________________________________________________\n" +
                "   Hello! I'm Duke\n" +
                "   What can I do for you?\n" +
                "____________________________________________________________";

        System.out.println(startDuke);

         while(true) {
             String cmd = userInput.nextLine();
             if(cmd.equals("bye")) {
                 String bye = "____________________________________________________________\n" +
                         "\t"+"Bye! See you next time!\n" +
                         "____________________________________________________________";
                 System.out.println(bye);
                 break;
             }
             String userCmd = "____________________________________________________________\n" +
                    "\t"+cmd+"\n" +
                    "____________________________________________________________";
             System.out.println(userCmd);
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
