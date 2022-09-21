package general;

import java.util.Random;

/**
 * General helpful methods.
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 11.4.20
 */
public class HelpingMethods {
    // Colors
    public static final java.awt.Color BLUE = new java.awt.Color(70, 70, 185);
    public static final java.awt.Color LIGHT_BLUE = new java.awt.Color(51, 128, 204);
    public static final java.awt.Color GREEN = new java.awt.Color(57, 198, 163);
    public static final java.awt.Color PURPLE = new java.awt.Color(128, 89, 166);
    public static final java.awt.Color PINK = new java.awt.Color(191, 64, 96);
    public static final java.awt.Color ORANGE = new java.awt.Color(185, 99, 70);
    public static final java.awt.Color CREAM = new java.awt.Color(230, 204, 179);
    public static final java.awt.Color BROWN = new java.awt.Color(77, 38, 0);
    public static final java.awt.Color LIGHT_GREEN = new java.awt.Color(102, 255, 153);
    public static final java.awt.Color LIGHT_PINK = new java.awt.Color(251, 208, 208);
    public static final java.awt.Color YELLOW = new java.awt.Color(255, 224, 102);

    // Sizes
    public static final int GUI_WIDTH = 800;
    public static final int GUI_HEIGHT = 600;
    public static final int FRAME_THICKNESS = 32;

    /**
     * Check if the given string represents an integer.
     *
     * @param str The given strings array.
     * @return "true" if the given string represents an integer, and "false" otherwise.
     */
    public static boolean isInteger(String str) {
        try {
            int num = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Check if the given string represents a non-negative integer
     * (greater than or equal to zero).
     *
     * @param str the given string.
     * @return "true" if the given string represents a non-negative integer,
     * and "false" otherwise.
     */
    public static boolean isNonNegativeInt(String str) {
        return (isInteger(str) && (Integer.parseInt(str) >= 0));
    }

    /**
     * Check if the given string represents a double.
     *
     * @param str The given strings array.
     * @return "true" if the given string represents a double, and "false" otherwise.
     */
    public static boolean isDouble(String str) {
        try {
            Double num = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Get a random number between min and max.
     *
     * @param min lower bound.
     * @param max upper bound.
     * @return a random number between min to max.
     */
    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(Math.abs(max - min)) + min;
    }

    /**
     * Get a random color.
     *
     * @return Random color.
     */
    public static java.awt.Color getRandomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new java.awt.Color(r, g, b);
    }
}
