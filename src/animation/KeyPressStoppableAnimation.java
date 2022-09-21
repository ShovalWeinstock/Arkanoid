package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * An animation that stops when pressing a selected key.
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 19.6.21
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * The constructor of "KeyPressStoppableAnimation".
     *
     * @param sensor the keyboard sensor.
     * @param key The key that should be pressed to stop the animation.
     * @param animation The animation that runs until "key" is pressed.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;

    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyboardSensor.isPressed(this.key)) {
            if (isAlreadyPressed) {
                return;
            }
            this.stop = true;
        } else {
            isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
