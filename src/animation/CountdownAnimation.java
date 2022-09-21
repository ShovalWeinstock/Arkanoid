package animation;

import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprites.SpriteCollection;

/**
 * Animation of countdown.
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 19.6.21
 */
public class CountdownAnimation implements Animation {

    static final int FONT_SIZE = 70;

    private double numOfSeconds;
    private int countFrom;
    private int currentCount;
    private SpriteCollection gameScreen;
    private boolean stop;
    private Sleeper sleeper;

    /**
     * The constructor of "CountdownAnimation".
     *
     * @param numOfSeconds seconds per number.
     * @param countFrom The number to countdown from.
     * @param gameScreen The screen of the level.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.currentCount = countFrom;
        this.gameScreen = gameScreen;
        this.numOfSeconds = numOfSeconds;
        this.stop = false;
        this.sleeper = new Sleeper();
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.currentCount <= 0) {
            this.stop = true;
        }
        // add the background
        gameScreen.drawAllOn(d);
        // add the countdown
        d.setColor(Color.GRAY);
        d.drawText((d.getWidth() / 2) - 18, d.getHeight() / 2,
                   (Integer.valueOf(this.currentCount)).toString(), FONT_SIZE);
        if (this.currentCount != this.countFrom) {
            // pause on the current number for (numOfSeconds / countFrom) seconds
            this.sleeper.sleepFor((long) ((this.numOfSeconds / this.countFrom) * 1000));
        }
        this.currentCount--;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}