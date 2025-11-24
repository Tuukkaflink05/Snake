package utils;

import java.io.Console;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * utility class not neccerily needed for running the game
 * loads title screen from text file and starts playing start song
 */
public class Title {



    private Sound song;

    /**
     * main title method for printing the title screen
     * @throws Exception if problem with loading title screen txt file.
     */
    public Title() throws Exception {
        //clears the screen well not actually but it looks like it
        System.out.print("\u001b[2J\u001b[H");

        Console c = System.console();

        //loads and plays the screen song
        this.song = new Sound("sounds/waiting-time.wav");

         song.play();
         String content = "";

        //load the title screen from a text file
        try {
            content = Files.readString(Paths.get("texts/title.txt"));

        } catch (Exception e) {

        }
        //set title screen to yellow
        System.out.print("\u001B[93m" + content + "\u001B[0m");


        String input = c.readLine();

            if (input.equals("highscore")) {
                highScore.showHighScore();
            }
        song.stop();
    }
}
