package sprites;

import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;

/**
 * The object "Framed Block" - a block with a black frame.
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 25.4.21
 */
public class FramedBlock extends Block {
    /**
     * instructor of "NoFrameBlock" - a block with no frame.
     *
     * @param shape The shape (rectangle) of the block.
     * @param color The color of the block.
     */
    public FramedBlock(Rectangle shape, Color color) {
        super(shape, color);
    }

    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.getShape().getUpperLeft().getX(), (int) this.getShape().getUpperLeft().getY(),
                (int) this.getShape().getWidth(), (int) this.getShape().getHeight());

    }
}
