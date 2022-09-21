import animation.AnimationRunner;
import biuoop.KeyboardSensor;
import gamelogic.GameFlow;
import levels.ColorfulLevel;
import levels.DirectHitLevel;
import levels.LegoManLevel;
import levels.LevelInformation;
import levels.CandyLandLevel;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Running the game.
 *
 * @author Shoval Weinstock <shovalw12@gmail.com>
 * @version 28.5.21
 */
public class Ass6Game {
    /**
     * Initialize and run the game.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        AnimationRunner runner = new AnimationRunner(60);
        KeyboardSensor keyboard = runner.getGui().getKeyboardSensor();
        GameFlow game = new GameFlow(runner, keyboard);

        TreeMap<String, LevelInformation> levelsMenu = new TreeMap<String, LevelInformation>();
        levelsMenu.put("1", new DirectHitLevel());
        levelsMenu.put("2", new LegoManLevel());
        levelsMenu.put("3", new CandyLandLevel());
        levelsMenu.put("4", new ColorfulLevel());

        List<LevelInformation> levels = new ArrayList<LevelInformation>();

        for (String arg : args) {
            if (levelsMenu.containsKey(arg)) {
                levels.add(levelsMenu.get(arg));
            }
        }
        if (levels.size() == 0) {
            levels.addAll(levelsMenu.values());
        }
        game.runLevels(levels);
    }
}
