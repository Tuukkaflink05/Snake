package gameUtils;
import utils.Sound;
import utils.highScore;
import utils.input.InputHandler;
import utils.input.StringInputHandler;

public class Game {

    public int height;
    public int width;
    public int score;
    private Snake snake;
    private Apple apple;
    private Sound eatSound;
    private Sound startSound;
    private Sound deathSound;

    private Sound levelUp;

    public Game(int height, int width) {

        this.eatSound = new Sound("sounds/2-iteration-blob.wav");
        this.startSound = new Sound("sounds/4-iteration-konng.wav");
        this.deathSound = new Sound("sounds/3-iteratoin-mmph.wav");

        this.levelUp = new Sound("sounds/item-obtained.wav");

        startSound.play();

        this.height = height;
        this.width = width;
        this.score = 0;

        int[][] startBody = { { 10, 5 }, { 9, 5 }, { 8, 5 }, { 7, 5 } };
        String startDirection = "d";

        this.snake = new Snake(startBody, startDirection);
        this.apple = new Apple();

    }

    public void run() {

        InputHandler inputHandler = new InputHandler(snake);
        Thread inputThread = new Thread(inputHandler);
        inputThread.start();
        boolean gameOn = true;
        boolean levelupplayed = false;
        int threadSleepTime = 220;

        while (gameOn) {

            snake.takeStep();

            if (snake.hitSelf() || wallHit()) {
                deathSound.play();
                gameOn = false;
                break;
            }

            if (eatApple()) {
                eatSound.play();
                placeApple();
                snake.addLength();
                snake.addLength();
                snake.addLength();
                snake.addLength();
                this.score++;

            }

            if (this.score == 10 && !levelupplayed) {
                levelUp.play();
                levelupplayed = true;
                threadSleepTime = 120;
            }

            Render();
            try {
                Thread.sleep(threadSleepTime);
            } catch (InterruptedException e) {
            }
        }

        inputHandler.quit();
        System.out.println("Game Over! :(");
        System.out.println("Score: " + this.score);
        System.out.println("press enter to quit!");
        try {
            inputThread.join();
        } catch (InterruptedException e) {
        }

        checkHighScore();

    }

    public void checkHighScore() {

        String[][] scoreArray = highScore.getScoreArray();
        int scoreToBeat = Integer.parseInt(scoreArray[9][1]);

        if (this.score > scoreToBeat) {
            System.out.println("You Got A New HighScore");
            System.out.println("enter a 3 long name to save score");
            String name = StringInputHandler.getInput();
            scoreArray[9][1] = String.valueOf(this.score);
            scoreArray[9][0] = name;

            try {
                highScore.sortScoreArray(scoreArray);
            } catch (Exception e) {
            }
            ;
        }

    }

    public boolean wallHit() {
        return ((snake.body[0][0] == this.width - 2) || (snake.body[0][0] == -1)
                || (snake.body[0][1] == this.height - 2) || (snake.body[0][1] == -1));
    }

    public void placeApple() {

        boolean appleInSnake;

        do {
            // pick new random location
            int appleXCo = (int) (Math.random() * (width - 2 - 1)) + 1;
            int appleYCo = (int) (Math.random() * (height - 2 - 1)) + 1;

            appleInSnake = false;

            // check if apple is inside snake
            for (int i = 0; i < snake.body.length; i++) {
                if (snake.body[i][0] == appleXCo && snake.body[i][1] == appleYCo) {
                    appleInSnake = true;
                    break;
                }
            }

            // if not inside snake → set location
            if (!appleInSnake) {
                apple.location[0] = appleXCo;
                apple.location[1] = appleYCo;
            }

        } while (appleInSnake);
    }

    public boolean eatApple() {
        return (snake.body[0][0] == apple.location[0] && snake.body[0][1] == apple.location[1]);
    }

    public String[][] BoardMatrix() {

        String[][] board = new String[this.height][this.width];
        return board;
    }

    public void Render() {

        String[][] matrix = BoardMatrix();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < matrix.length; i++) {
            sb.append("\n");
            for (int j = 0; j < matrix[i].length; j++) {

                boolean isSnake = false;
                int xCo;
                int yCo;

                boolean isApple = apple.location[0] == j - 1 && apple.location[1] == i - 1;

                boolean isHead = snake.body[0][0] == j - 1 && snake.body[0][1] == i - 1;

                for (int k = 0; k < snake.body.length; k++) {

                    xCo = snake.body[k][0];
                    yCo = snake.body[k][1];

                    isSnake = xCo == j - 1 && yCo == i - 1;

                    if (isSnake) {
                        break;
                    }
                }

                if (isHead) {
                    sb.append("\u001B[42;93mX\u001B[0m");
                }

                if (isSnake && !isHead) {
                    sb.append("\u001B[42;94m■\u001B[0m");
                }

                if (isApple && !isHead && !isSnake) {
                    sb.append("\u001B[42;91m@\u001B[0m");
                }

                if (!isSnake && !isHead && !isApple) {
                    if (i == 0 || i == matrix.length - 1) {
                        sb.append("░");
                    } else if (j == 0 || j == matrix[i].length - 1) {
                        sb.append("░");
                    } else {
                        sb.append("\u001B[42m \u001B[0m");
                    }
                }
            }
        }

        String result = sb.toString();
        System.out.print("\u001b[H");
        System.out.print("Score: " + score);
        System.out.println(result);

    }
}
