import org.example.core.Canvas;

/**
 * Created by cabarca on 06/08/15.
 */
public class TestProgress {

    public static void main (String[] args) {
        Canvas canvas = new Canvas(20, 20);
        // draw in the canvas and print it
        canvas.processCommand("PD",0);
        canvas.processCommand("RT",0);
        canvas.processCommand("FD",10);
        canvas.processCommand("RT",0);
        canvas.processCommand("FD",10);
        canvas.processCommand("RT",0);
        canvas.processCommand("FD",10);
        canvas.processCommand("RT",0);
        canvas.processCommand("FD",10);
        canvas.showTurtlePosition();
        // draw canvas
        canvas.processCommand("PAINT",0);
    }
}
