package dev.aidang.encounters.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.data.annotation.PersistenceCreator;

/**
 * How many dice should be rolled for an event.
 * This may be damage on an attack, total number of hit dice, etc.
 *
 * @param die      Which die?
 * @param count    How many?
 * @param modifier A constant modifier
 */
public record Dice(Die die, int count, Integer modifier) {

    private static final Pattern DICE_REGEX = Pattern.compile("(\\d+)d(\\d+)\\s*([+-]\\s*\\d+)?|(\\d+)");

    @PersistenceCreator
    public Dice(Die die, int count) {
        this(die, count, 0);
    }

    public Dice withModifier(Integer modifier) {
        return new Dice(die, count, modifier == null ? 0 : modifier);
    }

    /**
     * Convert NdX to Dice
     */
    public static Dice parse(String dice) {
        Matcher matcher = DICE_REGEX.matcher(dice);
        if (matcher.matches()) {
            if (matcher.group(4) != null) {
                // Constant damage value. This doesn't feel like a dice, but I guess
                int modifier = Integer.parseInt(matcher.group(4));
                return new Dice(Die.D6, 0, modifier);
            }
            int count = Integer.parseInt(matcher.group(1));
            Die die = Die.fromInteger(Integer.parseInt(matcher.group(2)));
            int modifier =
                    matcher.group(3) != null ? Integer.parseInt(matcher.group(3).replace(" ", "")) : 0;
            return new Dice(die, count, modifier);
        }
        throw new IllegalArgumentException("String %s is not a valid dice expression".formatted(dice));
    }
}
