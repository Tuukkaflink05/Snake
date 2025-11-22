package utils;

import java.io.Console;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Title {



    private Sound song;

    public Title() throws Exception {
        Console c = System.console();

         this.song = new Sound("sounds/waiting-time.wav");

         song.play();
         String content = "";


        try {
            content = Files.readString(Paths.get("texts/title.txt"));

        } catch (Exception e) {

        }
        System.out.print("\u001B[93m" + content + "\u001B[0m");


        String input = c.readLine();

            if (input.equals("highscore")) {
                highScore.showHighScore();
            }
        song.stop();
    }
}
