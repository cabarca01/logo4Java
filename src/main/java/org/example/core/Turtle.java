package org.example.core; /**
 * Created by cabarca on 06/08/15.
 */
import java.lang.String;
import java.util.Arrays;

public class Turtle {

    private int posX;
    private int posY;
    private int penStatus;
    private String penStyle;

    private TurtleDirection direction;
    private AroundCanvas wentAround;

    private int canvasX;
    private int canvasY;

    public Turtle(int x, int y, String style, int sizeX, int sizeY) {
        posX = x;
        posY = y;
        penStatus = 0;
        penStyle = style;
        direction = TurtleDirection.UP;
        wentAround = AroundCanvas.NO;
        // set canvas dimensions
        canvasX = sizeX;
        canvasY = sizeY;
    }

    private void moveHorizonal (int x) {
        posX = posX + x;
        // check to see if it goes around the canvas
        if (posX > canvasX || posX < 0){
            int timesAround = 0;
            // if position exceeds canvas size
            while (posX > canvasX) {
                posX = posX - canvasX;
                timesAround++;
            }
            // if position is negative
            while (posX < 0) {
                posX = canvasX + posX;
                timesAround++;
            }
            if (timesAround > 1) {
                wentAround = AroundCanvas.MULTIPLE;
            } else {
                wentAround = AroundCanvas.ONCE;
            }
        } else {
            wentAround = AroundCanvas.NO;
        }
    }

    private void moveVertical (int y) {
        posY = posY + y;
        // check to see if turtle goes around the canvas
        if (posY > canvasY || posY < 0) {
            int timesAround = 0;
            // if position exceeds canvas size
            while (posY > canvasY) {
                posY = posY - canvasY;
                timesAround++;
            }
            // if position is negative
            while (posY < 0) {
                posY = canvasY + posY;
                timesAround++;
            }
            if (timesAround > 1) {
                wentAround = AroundCanvas.MULTIPLE;
            } else {
                wentAround = AroundCanvas.ONCE;
            }
        } else {
            wentAround = AroundCanvas.NO;
        }
    }

    public void moveTurtle (int distance) {
        switch (direction) {
            case UP :
                moveVertical(-distance);
                break;
            case RIGHT :
                moveHorizonal(distance);
                break;
            case DOWN :
                moveVertical(distance);
                break;
            case LEFT :
            default:
                moveHorizonal(-distance);
                break;
        }
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getPenStatus() {
        return penStatus;
    }

    public String getPenStyle() {
        return penStyle;
    }

    public TurtleDirection getDirection() {
        return direction;
    }

    public AroundCanvas getWentAround() {
        return wentAround;
    }

    public void setPenStatus(int penStatus) {
        this.penStatus = penStatus;
    }

    public void setPenStyle(String penStyle) {
        this.penStyle = penStyle;
    }

    public void rotateTurtle(String rotation) {
        TurtleDirection[] directionsList = TurtleDirection.values();

        int indexIncrement;
        if (rotation.equalsIgnoreCase("R")) {
            indexIncrement = 1;
        } else {
            indexIncrement = -1;
        }

        int currentIndex = Arrays.binarySearch(directionsList, direction);

        if (currentIndex == 0 && indexIncrement < 0) {
            this.direction = directionsList[directionsList.length - 1];
        } else if (currentIndex == directionsList.length -1 && indexIncrement > 0) {
            this.direction = directionsList[0];
        } else {
            this.direction = directionsList[currentIndex + indexIncrement];
        }
    }
}
