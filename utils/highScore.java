package utils;

import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class highScore {



    public static void showHighScore() {
        Console c = System.console();

        String content = "";
        String[][] scoreArray = getScoreArray();
        String textCentering = "                ";


        try {
            content = Files.readString(Paths.get("texts/highScores/highScoresTitle.txt"));

        } catch (Exception e) {}


        for (int i = 0; i < scoreArray.length; i++) {

            content += ("\n");
            content += textCentering;
            content += scoreArray[i][0];
            content += (" ");
            content += scoreArray[i][1];
        }


        content += ("\n");
        content += ("\n");
        content += ("\n");
        content += textCentering;
        content += textCentering;

        content += ("PRESS ENTER TO START!");

        System.out.print("\u001b[2J\u001b[H");
        System.out.print("\u001B[93m" + content + "\u001B[0m");

        c.readLine();
    }


    public static String[][] getScoreArray() {


        File nameObj = new File("texts/highScores/highScoresName.txt");
        String[][] scoreArray = new String[10][2];
        int i = 0;

        try (Scanner myReader = new Scanner(nameObj)) {
            while (myReader.hasNextLine()) {
                scoreArray[i][0] = myReader.nextLine();
                i++;
            }
        } catch (Exception e) {}


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

    public static void sortScoreArray(String[][] scoreArray) throws IOException {

        for (int j = 0; j < scoreArray.length -1; j++) {

            int maxIdx = j;


            for (int k = j + 1; k < scoreArray.length; k++) {
                if (Integer.parseInt(scoreArray[k][1]) > Integer.parseInt(scoreArray[maxIdx][1])) {

                    maxIdx = k;
                }
            }

            int tmp = Integer.parseInt(scoreArray[j][1]);
            scoreArray[j][1] = scoreArray[maxIdx][1];
            scoreArray[maxIdx][1] = String.valueOf(tmp);

            String temp = scoreArray[j][0];
            scoreArray[j][0] = scoreArray[maxIdx][0];
            scoreArray[maxIdx][0] = temp;
        }




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
