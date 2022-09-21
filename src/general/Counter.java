package general;

/**
 * A counter object.
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 28.5.21
 */
public class Counter {
    // The current number counted.
    private int count;

    /**
     * Constructor.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Add the given number to current count.
     *
     * @param number The given number to add.
     */
    public void increase(int number) {
        this.count = this.count + number;
    }

    /**
     * Subtract the given number to current count.
     *
     * @param number The given number to subtract.
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }
    // get current count.

    /**
     * Get the current number counted.
     *
     * @return The number counted.
     */
    public int getValue() {
        return this.count;
    }
}
