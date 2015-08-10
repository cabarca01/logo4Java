import org.example.core.Canvas;
import org.example.core.LogoCommands;

import java.util.Scanner;

/**
 * Created by cabarca on 10/08/15.
 */
public class Main {
    public static void main(String[] args) {
        Scanner usrInput = new Scanner(System.in);
        String command = null;
        Canvas canvas = new Canvas(20, 20);

        System.out.println("Welcome to Logo4Java!");
        System.out.println("The following commands are available so far:");
        System.out.println("RT, LT : Turn right or left 90 degrees");
        System.out.println("FD XX: Move forward XX positions (XX is an integer value)");
        System.out.println("PU, PD: Put the pen up or down");
        System.out.println("SG, HG: Show or hide grid");
        System.out.println();
        System.out.println("EXIT: To quit the program");
        System.out.println();

        String[] cmdArray;
        do {
            System.out.print("LOGO>> ");
            command = usrInput.nextLine();
            cmdArray = command.trim().split(" ");
            if ("EXIT".equalsIgnoreCase(cmdArray[0])) {
                continue;
            } else if (cmdArray.length > 1)  {
                try {
                    canvas.processCommand(cmdArray[0], Integer.valueOf(cmdArray[1]));
                } catch (NumberFormatException e) {
                    continue;
                }
            } else {
                canvas.processCommand(cmdArray[0], 0);
            }
            canvas.processCommand("PAINT",0);
            canvas.showTurtlePosition();
        } while (!"EXIT".equalsIgnoreCase(command));
    }
}
