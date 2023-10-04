package dev.aidang.encounters.model.creatures;

import dev.aidang.encounters.model.Die;
import org.springframework.data.annotation.Id;

/**
 * The damage dealt by an attack
 * @param die The type of die
 * @param count The number to roll
 * @param damageType The type of damage to deal
 */
public record Damage(@Id Long id, Die die, int count, DamageType damageType) {

    public Damage(Die die, int count, DamageType damageType) {
        this(null, die, count, damageType);
    }
}
