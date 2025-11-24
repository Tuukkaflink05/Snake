package gameUtils;

/**
 * class to handle snake values.
 * <p> handles snake position, direction, moving , eating, and selfhitting
 */
public class Snake {
    public int[][] body;
    public int[] direction;


    /**
     * constructor method to initialize the snake position and direction
     * @param body initial body coordinates on a 2d array
     * @param direction initial direction on an array
     */
    public Snake (int[][] body, String direction) {

        this.body = body;
        this.direction = setDirection(direction);
    }

    /**
     * moves the snake one block to the specified direction
     */
    public void takeStep () {
        //make a new snake body array that is the same size as the original snake
        int[][] newBody = new int[this.body.length][2];

        //loop through the snake array
        for (int i = 0; i < newBody.length; i++) {
                if (i == 0) {
                    // if we are at the head location move it one block to the direction specified by the direction
                    newBody[i][0] = this.body[0][0] + this.direction[0];
                    newBody[i][1] = this.body[0][1] + this.direction[1];
                } else {
                    newBody[i][0] = this.body[i-1][0];
                    newBody[i][1] = this.body[i-1][1];
                }
        }
        this.body = newBody;
    }

    /**
     * adds one length to the end of the original snake body
     * when it eats an apple
     */
    public void addLength() {
        int[][] newBody = new int[this.body.length + 1][2];

        for (int i = 0; i < this.body.length; i++) {

            newBody[i][0] = this.body[i][0];
            newBody[i][1] = this.body[i][1];
        }
        newBody[newBody.length-1][0] = this.body[this.body.length-1][0];
        newBody[newBody.length-1][1] = this.body[this.body.length-1][1];

        this.body = newBody;
    }

    /**
     *
     * @param direction
     * @return
     */
    public int[] setDirection(String direction) {
        return
            direction.equals("w") ? new int[]  {0, -1} :
            direction.equals("s") ? new int[]  {0, 1}  :
            direction.equals("a") ? new int[]  {-1, 0} :
            direction.equals("d") ? new int[]  {1, 0}  :
            null;
    }



    /**
     * method to check if the snake is hitting it self currently
     * @return true if snake is hitting it self
     */
    public boolean hitSelf() {

        for (int i = 1; i < this.body.length; i++) {

            if (body[0][0] == body[i][0] && body[0][1] == body[i][1]) {
                return true;
            }
        }
        return false;
    }
}
