package gameUtils;

public class Snake {
    public int[][] body;
    public int[] direction;


    public Snake (int[][] body, String direction) {

        this.body = body;
        this.direction = setDirection(direction);
    }

    public void takeStep () {

        int[][] newBody = new int[this.body.length][2];

        for (int i = 0; i < newBody.length; i++) {
                if (i == 0) {
                    newBody[i][0] = this.body[0][0] + this.direction[0];
                    newBody[i][1] = this.body[0][1] + this.direction[1];
                } else {
                    newBody[i][0] = this.body[i-1][0];
                    newBody[i][1] = this.body[i-1][1];
                }
        }
        this.body = newBody;
    }

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

    public int[] setDirection(String direction) {
        return
            direction.equals("w") ? new int[]  {0, -1} :
            direction.equals("s") ? new int[]  {0, 1}  :
            direction.equals("a") ? new int[]  {-1, 0} :
            direction.equals("d") ? new int[]  {1, 0}  :
            null;
    }

    public void getInput() {

    }


    public boolean hitSelf() {

        for (int i = 1; i < this.body.length; i++) {

            if (body[0][0] == body[i][0] && body[0][1] == body[i][1]) {
                return true;
            }
        }
        return false;
    }
}
