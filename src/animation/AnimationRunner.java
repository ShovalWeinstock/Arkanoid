package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import general.HelpingMethods;

/**
 * A moving object
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 19.6.21
 */
public class AnimationRunner {

    static final int MILLISECONDS = 1000;

    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * The constructor of "Animation Runner".
     * @param framesPerSecond the number of frames per second.
     */
    public AnimationRunner(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        this.gui = new GUI("", HelpingMethods.GUI_WIDTH, HelpingMethods.GUI_HEIGHT);
        this.sleeper = new Sleeper();
    }

    /**
     * @return The gui of the animation runner.
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * @param animation - an animation to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = MILLISECONDS / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            // Draw the objects.
            animation.doOneFrame(d);
            this.gui.show(d);
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
