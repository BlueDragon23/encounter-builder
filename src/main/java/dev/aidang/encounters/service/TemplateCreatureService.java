package dev.aidang.encounters.service;

import dev.aidang.encounters.model.Dice;
import dev.aidang.encounters.model.Die;
import dev.aidang.encounters.model.creatures.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static dev.aidang.encounters.Utils.JSON;

@Component
public class TemplateCreatureService {

    public TemplateCreature importCreatureFromMPMB(InputStream inputStream) throws IOException {
        MPMBCreature mpmbCreature = JSON.readValue(inputStream, MPMBCreature.class);
        return TemplateCreature.builder(mpmbCreature.getName())
                .withDescription(mpmbCreature.getName())
                .withCreatureSize(getSize(mpmbCreature.getSize()))
                .withType(mpmbCreature.getType())
                .withAlignment(mpmbCreature.getAlignment())
                .withArmorClass(mpmbCreature.getAc())
                .withHitDice(new Dice(
                        Die.fromInteger(mpmbCreature.getHd().get(0)),
                        mpmbCreature.getHd().get(1)))
                .withSpeed(parseSpeed(mpmbCreature.getSpeed()))
                .withAbilityScores(new AbilityScores(
                        mpmbCreature.getScores().get(0),
                        mpmbCreature.getScores().get(1),
                        mpmbCreature.getScores().get(2),
                        mpmbCreature.getScores().get(3),
                        mpmbCreature.getScores().get(4),
                        mpmbCreature.getScores().get(5)))
                .withHitpoints(mpmbCreature.getHp())
                .withAttacks(mpmbCreature.getAttacks().stream()
                        .map(this::buildAttack)
                        .collect(Collectors.toList()))
                .build();
    }

    private CreatureSize getSize(int mpmbSize) {
        // TODO: is this right?
        return switch (mpmbSize) {
            case -1 -> CreatureSize.TINY;
            case 0 -> CreatureSize.SMALL;
            case 1 -> CreatureSize.MEDIUM;
            case 2 -> CreatureSize.LARGE;
            case 4 -> CreatureSize.HUGE;
            case 5 -> CreatureSize.GARGANTUAN;
            default -> throw new IllegalArgumentException("Unexpected size " + mpmbSize);
        };
    }

    private Speed parseSpeed(String speed) {
        // TODO
        return new Speed(4);
    }

    private Attack buildAttack(MPMBCreature.Attack attack) {
        // Order is dice count, dice type, damage type
        List<String> damage = attack.getDamage();
        return Attack.builder()
                .withName(attack.getName())
                .withDescription(attack.getDescription())
                .withDamage(new Dice(Die.fromInteger(Integer.parseInt(damage.get(1))), Integer.parseInt(damage.get(0))))
                .withDamageType(DamageType.valueOf(damage.get(2).toUpperCase(Locale.ENGLISH)))
                .withRange(attack.getRange())
                .build();
    }
}
