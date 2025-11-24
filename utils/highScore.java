package utils;

import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


/**
 * utility class not neccerily needed for running the game
 *
 * <p> class containing methods pertaining to saving, showing and sorting
 * game highscores </p>
 */
public class highScore {

    /**
     * method for printing the highscore screen
     * reads main text from a textfile. uses an array which holds the name and score
     * and prints it to the screen along with main score title
     */

    public static void showHighScore() {
        Console c = System.console();

        String content = "";
        String[][] scoreArray = getScoreArray();
        //empty string to easier center text on screen.
        String textCentering = "                ";

        //get main score title from a file and and it to content variable
        try {
            content = Files.readString(Paths.get("texts/highScores/highScoresTitle.txt"));

        } catch (Exception e) {}

        //get player name and score from score array, add it into the content variable
        for (int i = 0; i < scoreArray.length; i++) {

            content += ("\n");
            content += textCentering;
            content += scoreArray[i][0];
            content += (" ");
            content += scoreArray[i][1];
        }

        //add new lines to text
        content += ("\n");
        content += ("\n");
        content += ("\n");
        content += textCentering;
        content += textCentering;

        content += ("PRESS ENTER TO START!");
        // clear the screen and print the content in yellow color
        System.out.print("\u001b[2J\u001b[H");
        System.out.print("\u001B[93m" + content + "\u001B[0m");

        // add readline to stop advancing the app until user presses enter
        c.readLine();
    }



    /**
     * method for adding score names and values to an array to use it later.
     *
     * @return an array containing the top player name and score
     */
    public static String[][] getScoreArray() {


        // load player names from file
        File nameObj = new File("texts/highScores/highScoresName.txt");
        //initialize a 2d array in wchich to add names and scores side by side
        String[][] scoreArray = new String[10][2];
        int i = 0;

        //read line from name file and add to the first segment of each array line
        try (Scanner myReader = new Scanner(nameObj)) {
            while (myReader.hasNextLine()) {
                scoreArray[i][0] = myReader.nextLine();
                i++;
            }
        } catch (Exception e) {}

        // read one line from highscore value file and add to array to the same spot as the name of the player
        File numObj = new File ("texts/highScores/highScoresNum.txt");
        i = 0;

        try (Scanner myReader = new Scanner(numObj)) {
            while (myReader.hasNextLine()) {
                scoreArray[i][1] = myReader.nextLine();

                i++;
            }
        } catch (Exception e) {}

        return scoreArray;

    }

    /**
     * method for sorting a 2d array where the second row of each line is a number and first is a String
     * @param scoreArray a 2d array with 2 rows where second row is a number
     * @throws IOException throws an exception if problem with the file
     */

    public static void sortScoreArray(String[][] scoreArray) throws IOException {

        //uses selection sort to sort from biggest to smallest according to the numbers on the second row
        for (int j = 0; j < scoreArray.length -1; j++) {

            int maxIdx = j;

            for (int k = j + 1; k < scoreArray.length; k++) {
                if (Integer.parseInt(scoreArray[k][1]) > Integer.parseInt(scoreArray[maxIdx][1])) {

                    maxIdx = k;
                }
            }

            // do the needed swaps for the number portion and the string portion of the array based on the number values.
            int tmp = Integer.parseInt(scoreArray[j][1]);
            scoreArray[j][1] = scoreArray[maxIdx][1];
            scoreArray[maxIdx][1] = String.valueOf(tmp);

            String temp = scoreArray[j][0];
            scoreArray[j][0] = scoreArray[maxIdx][0];
            scoreArray[maxIdx][0] = temp;
        }



            //loop through sorted array and add nimes to one file and scores to another.
            FileWriter nameWriter = new FileWriter("texts/highScores/highScoresName.txt");
            FileWriter numWriter = new FileWriter("texts/highScores/highScoresNum.txt");
            for (int m = 0; m < scoreArray.length; m++) {
                nameWriter.write(scoreArray[m][0]);
                nameWriter.write("\n");
                numWriter.write(scoreArray[m][1]);
                numWriter.write("\n");

        }
            nameWriter.close();  // must close manually
            numWriter.close();
    }
}
