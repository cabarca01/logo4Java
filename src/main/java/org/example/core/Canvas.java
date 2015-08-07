package org.example.core;

/**
 * Created by cabarca on 06/08/15.
 */
public class Canvas {
    private Turtle turtle;
    private int[][] canvas;
    private boolean showGrid;

    public Canvas( int canvasWidth, int canvasHeight) {
        // initiate canvas
        canvas = new int[canvasWidth + 1][canvasHeight + 1];
        showGrid = false;
        // initiate turtle in canvas at position 0,0
        turtle = new Turtle(0, 0, "*", canvasWidth, canvasHeight);
    }

    private void showCanvas() {
        String pen;

        for (int[] row : canvas) {
            for (int cell : row ) {

                if (showGrid && cell == 0) {
                    pen = "0";
                } else if (!showGrid && cell == 0) {
                    pen = " ";
                } else {
                    pen = turtle.getPenStyle();
                }

                System.out.printf("%2s", pen);
            }
            System.out.printf("%n");
        }
    }

    private void drawOnCanvas( int distance) {
        // restriction: The turtle only moves vertical or horizontal
        int turtlePenStatus = turtle.getPenStatus();
        int actualColumn = turtle.getPosX();
        int actualRow = turtle.getPosY();

        turtle.moveTurtle(distance);

        if (turtlePenStatus == 1) {
            // rows are Y and cols are X
            // check if position in X changed
            if (actualColumn != turtle.getPosX() || turtle.getWentAround() != AroundCanvas.NO) {
                //check if it went around the canvas multiple times
                if (turtle.getWentAround() == AroundCanvas.MULTIPLE) {
                    for (int pos = 0; pos < canvas[0].length; pos++) {
                        canvas[actualRow][pos] = 1;
                    }
                } else if (turtle.getWentAround() == AroundCanvas.ONCE) {
                    // if the direction is LEFT discount positions in columns
                    if (turtle.getDirection() == TurtleDirection.LEFT) {
                        for (int pos = actualColumn; pos >= 0; pos--) {
                            canvas[actualRow][pos] = 1;
                        }
                        for (int pos = canvas[0].length -1; pos >= turtle.getPosX(); pos--) {
                            canvas[actualRow][pos] = 1;
                        }
                    } else {
                        for (int pos = actualColumn; pos < canvas[0].length; pos ++) {
                            canvas[actualRow][pos] = 1;
                        }
                        for (int pos = 0; pos <= turtle.getPosX(); pos++) {
                            canvas[actualRow][pos] = 1;
                        }
                    }
                } else {
                    if (turtle.getDirection() == TurtleDirection.LEFT) {
                        for (int pos = actualColumn; pos >= turtle.getPosX(); pos--) {
                            canvas[actualRow][pos] = 1;
                        }
                    } else {
                        for (int pos = actualColumn; pos <= turtle.getPosX(); pos++) {
                            canvas[actualRow][pos] = 1;
                        }
                    }
                }
            }
            // check for changes in vertical position
            if (actualRow != turtle.getPosY() || turtle.getWentAround() != AroundCanvas.NO) {
                //check if it went around the canvas multiple times
                if (turtle.getWentAround() == AroundCanvas.MULTIPLE) {
                    for (int pos = 0; pos < canvas.length; pos++) {
                        canvas[pos][actualColumn] = 1;
                    }
                } else if (turtle.getWentAround() == AroundCanvas.ONCE) {
                    // if the direction is UP discount positions in columns
                    if (turtle.getDirection() == TurtleDirection.UP) {
                        for (int pos = actualRow; pos >= 0; pos--) {
                            canvas[pos][actualColumn] = 1;
                        }
                        for (int pos = canvas.length -1; pos >= turtle.getPosY(); pos--) {
                            canvas[pos][actualColumn] = 1;
                        }
                    } else {
                        for (int pos = actualRow; pos < canvas.length; pos ++) {
                            canvas[pos][actualColumn] = 1;
                        }
                        for (int pos = 0; pos <= turtle.getPosY(); pos++) {
                            canvas[pos][actualColumn] = 1;
                        }
                    }
                } else {
                    if (turtle.getDirection() == TurtleDirection.UP) {
                        for (int pos = actualRow; pos >= turtle.getPosY(); pos--) {
                            canvas[pos][actualColumn] = 1;
                        }
                    } else {
                        for (int pos = actualRow; pos <= turtle.getPosY(); pos++) {
                            canvas[pos][actualColumn] = 1;
                        }
                    }
                }
            }

        }
    }

    public void processCommand( int command, int distance) {
        switch (command) {
            case 1:
                turtle.setPenStatus(0);
                break;
            case 2:
                turtle.setPenStatus(1);
                break;
            case 3:
                turtle.rotateTurtle("R");
                break;
            case 4:
                turtle.rotateTurtle("L");
                break;
            case 5:
                drawOnCanvas( distance );
                break;
            case 7:
                showGrid = true;
                break;
            case 8:
                showGrid = false;
                break;
            case 6:
            default:
                showCanvas();
                break;
        }
    }

    public void showTurtlePosition() {
        System.out.printf("The Turtle is at %d, %d%n", turtle.getPosX(), turtle.getPosY());
    }
}
