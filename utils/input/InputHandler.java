package utils.input;

import java.io.Console;

import gameUtils.Snake;
import utils.Sound;

public class InputHandler implements Runnable {

    private Sound wSound;
    private Sound aSound;
    private Sound sSound;
    private Sound dSound;

    private Snake snake;
    private volatile boolean running = true;


    public InputHandler(Snake snake) {
        this.snake = snake;

        this.wSound = new Sound("sounds/w-sound.wav");
        this.aSound = new Sound("sounds/a-sound.wav");
        this.sSound = new Sound("sounds/s-sound.wav");
        this.dSound = new Sound("sounds/d-sound.wav");



    }

    public void quit() {
        running = false;
    }

    @Override
    public void run() {

        while(running) {

            Console c = System.console();
            String input = c.readLine();

            boolean validInput = input.equals("w") || input.equals("a") || input.equals("s") || input.equals("d");

            if (validInput) {

                int[] newDirection = snake.setDirection(input);

                boolean opposites = (newDirection[0] == snake.direction[0] * -1) && (newDirection[1] == snake.direction[1] * - 1);

                if (!opposites) {
                    snake.direction = snake.setDirection(input);

                    if (input.equals("w")) {
                        wSound.play();
                    } else if (input.equals("a")) {
                        aSound.play();
                    } else if (input.equals("s")) {
                        sSound.play();
                    } else if (input.equals("d")) {
                        dSound.play();
                    }
                }
            }
        }
    }
}
