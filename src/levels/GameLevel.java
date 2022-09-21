package levels;

import animation.Animation;
import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamelogic.Collidable;
import gamelogic.GameEnvironment;
import general.Counter;
import general.HelpingMethods;
import general.Velocity;
import geometry.Point;
import geometry.Rectangle;
import observers.BallRemover;
import observers.BlockRemover;
import observers.ScoreTrackingListener;
import sprites.Ball;
import sprites.Block;
import sprites.FramedBlock;
import sprites.Paddle;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The object "Game".
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 25.4.21
 */
public class GameLevel implements Animation {
    // The thickness of the frame.
    static final int FRAME_THICKNESS = HelpingMethods.FRAME_THICKNESS;
    static final int FRAME_BEGINNING_Y = 25;
    // The balls
    static final int BALL_RADIUS = 5;
    // The middle of the paddle.
    static final int BALL_START_X = 400;
    static final int BALL_START_Y = 540;
    static final int FINISH_LEVEL_SCORE = 100;
    static final int GUI_HEIGHT = HelpingMethods.GUI_HEIGHT;
    static final int GUI_WIDTH = HelpingMethods.GUI_WIDTH;

    // The sprites objects in the game.
    private SpriteCollection sprites;
    // The game environment the game.
    private GameEnvironment environment;
    // A counter for the number of remained blocks
    private Counter blocksCounter;
    private int initialBlocksNum;
    // A counter for the number of remained balls
    private Counter ballsCounter;
    // Score counter
    private Counter score;
    // Animation
    private AnimationRunner runner;
    private boolean running;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation level;
    // The blocks of the frame.
    private final Block topBlock = new Block(new Rectangle(new Point(FRAME_THICKNESS, FRAME_BEGINNING_Y),
            GUI_WIDTH - FRAME_THICKNESS, FRAME_THICKNESS), Color.gray);
    private final Block rightBlock = new Block(new Rectangle(new Point(0, FRAME_BEGINNING_Y),
            FRAME_THICKNESS, GUI_HEIGHT), Color.gray);
    private final Block leftBlock = new Block(new Rectangle(new Point(GUI_WIDTH - FRAME_THICKNESS, FRAME_BEGINNING_Y),
            FRAME_THICKNESS, GUI_HEIGHT), Color.gray);

    /**
     * Constructor of "GameLevel".
     *
     * @param level The information of the level.
     * @param keyboard The keyboard of the game.
     * @param runner The animation runner game.
     * @param score The score of the game.
     */
    public GameLevel(LevelInformation level, KeyboardSensor keyboard, AnimationRunner runner, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksCounter = new Counter();
        this.ballsCounter = new Counter();
        this.score = score;
        this.runner = runner;
        this.keyboard = keyboard;
        this.level = level;
    }

    /**
     * Add the given collidable to the game environment.
     *
     * @param c The given collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add the given sprite to the game.
     *
     * @param s The given sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks, Balls, and Paddle, and add them to the game.
     */
    public void initialize() {
        this.addSprite(this.level.getBackground());
        this.addFrame();
        // Create and add the paddle.
        Paddle paddle = new Paddle(this.keyboard, this.level.paddleColor(), GUI_WIDTH, this.level.paddleStartPoint(),
                                   FRAME_THICKNESS, this.level.paddleSpeed(), this.level.paddleWidth());
        paddle.addToGame(this);
        // Create and add the blocks of the game.
        BallRemover ballsRemover = new BallRemover(this, ballsCounter);
        Block deathRegion = new Block(new Rectangle(new Point(FRAME_THICKNESS, GUI_HEIGHT - FRAME_THICKNESS),
                GUI_WIDTH - 2 * FRAME_THICKNESS, FRAME_THICKNESS), this.level.backgroundColor());
        deathRegion.addHitListener(ballsRemover);
        deathRegion.addToGame(this);
        BlockRemover blockRemover = new BlockRemover(this, this.blocksCounter);
        ScoreTrackingListener scoreTracking = new ScoreTrackingListener(this.score);
        this.addBlocks(this.level.blocks(), blockRemover, scoreTracking);
        this.initialBlocksNum = this.level.blocks().size();
        // Add the balls.
        List<Velocity> velocities = this.level.initialBallVelocities();
        List<Point> locations = this.level.initialBallLocations();
        for (int i = 0; i < this.level.numberOfBalls(); i++) {
            addBall(velocities.get(i), locations.get(i));
        }
        // Add score indicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);
    }

    /**
     * Add a ball to the game.
     * @param velocity The velocity of the ball.
     * @param location The initial location of the ball.
     */
    private void addBall(Velocity velocity, Point location) {
        Ball ball = new Ball(location.getX(), location.getY(), BALL_RADIUS, Color.LIGHT_GRAY, this.environment);
        ball.setVelocity(velocity);
        ball.addToGame(this);
        this.ballsCounter.increase(1);
    }

    /**
     * Add the given blocks to the game.
     *
     * @param blocks The given blocks.
     * @param remover Blocks remover.
     * @param scoreTracking Score tracking indicator.
     */
    private void addBlocks(List<Block> blocks, BlockRemover remover, ScoreTrackingListener scoreTracking) {
        for (Block block : blocks) {
            this.blocksCounter.increase(1);
            block.addHitListener(remover);
            block.addHitListener(scoreTracking);
            block.addToGame(this);
        }
    }

    /**
     * Create a row of blocks.
     *
     * @param numOfBlocks The number of blocks on the row.
     * @param topLeftY The Y value of the top left corner of the first block in the row.
     * @param color The color of the blocks.
     * @param blockWidth The width of a block.
     * @param blockHeight The height of a block.
     * @return A list of the block in the row.
     */
    public static List<Block> createBlocksRow(int numOfBlocks, double topLeftY, java.awt.Color color,
                                              int blockWidth, int blockHeight) {
        List<Block> blocksRow = new ArrayList<Block>();
        for (int i = 0; i < numOfBlocks; i++) {
            Point upperLeft = new Point(GUI_WIDTH - (FRAME_THICKNESS + blockWidth) - i * blockWidth, topLeftY);
            Block block = new FramedBlock(new Rectangle(upperLeft, blockWidth, blockHeight), color);
            blocksRow.add(block);
        }
        return blocksRow;
    }

    /**
     * Add the frame blocks to the game.
     */
    private void addFrame() {
        Block[] frameBlocks = {this.topBlock, this.rightBlock, this.leftBlock};
        for (Block frameBlock : frameBlocks) {
            frameBlock.addToGame(this);
        }
    }

    /**
     * Add background to the given surface.
     *
     * @param d The given surface.
     * @param color The color of the background.
     */
    public static void addBackground(DrawSurface d, Color color) {
        d.setColor(color);
        d.fillRectangle(FRAME_THICKNESS, FRAME_THICKNESS, GUI_WIDTH - FRAME_THICKNESS,
                GUI_HEIGHT - FRAME_THICKNESS);
    }

    /**
     * Run the level.
     */
    public void run() {
        this.runner.run(new animation.CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    @Override
    public boolean shouldStop() {
        if (this.ballsCounter.getValue() == 0) {
            this.running = false;
        }

        if (this.initialBlocksNum - this.level.numberOfBlocksToRemove() == this.blocksCounter.getValue()) {
            this.score.increase(FINISH_LEVEL_SCORE);
            this.running = false;
        }
        return (!this.running);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                            new PauseScreen()));
        }
    }

    /**
     * Remove the given collidable from the game environment of this game.
     *
     * @param c The given collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove the given sprite from this game.
     *
     * @param s The given sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Get the number of blocks left in the game.
     *
     * @return the number of blocks left in the game.
     */
    public int leftBlocksNum() {
        return this.blocksCounter.getValue();
    }

    /**
     * Get the number of balls left in the game.
     *
     * @return the number of balls left in the game.
     */
    public int leftBallsNum() {
        return this.ballsCounter.getValue();
    }
}