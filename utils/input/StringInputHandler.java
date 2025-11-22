package utils.input;

import java.io.Console;

public class StringInputHandler {


    public static String getInput() {

        Console c = System.console();
        String name = "";

        while (true) {

            try {
                name = c.readLine();

                if (name.length() != 3) {
                    throw new ArithmeticException("name has to be 3 long");
                }

                break;

            }
            catch (ArithmeticException e) {
                System.out.println("name has to be 3 long");
                continue;
            }

        }

        return name.toUpperCase();



    }

}
