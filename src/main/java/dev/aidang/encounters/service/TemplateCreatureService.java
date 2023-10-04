package dev.aidang.encounters.service;

import dev.aidang.encounters.model.Dice;
import dev.aidang.encounters.model.Die;
import dev.aidang.encounters.model.creatures.*;
import dev.aidang.encounters.repository.TemplateCreatureRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static dev.aidang.encounters.Utils.JSON;

@Component
public class TemplateCreatureService {

    private static final Pattern simpleSpeedPattern = Pattern.compile("(\\d+) ft");

    private final TemplateCreatureRepository templateCreatureRepository;

    public TemplateCreatureService(TemplateCreatureRepository templateCreatureRepository) {
        this.templateCreatureRepository = templateCreatureRepository;
    }

    public TemplateCreature importCreatureFromMPMB(InputStream inputStream) throws IOException {
        MPMBCreature mpmbCreature = JSON.readValue(inputStream, MPMBCreature.class);
        TemplateCreature creature = TemplateCreature.builder(mpmbCreature.getName())
                .withDescription(mpmbCreature.getName())
                .withCreatureSize(getSize(mpmbCreature.getSize()))
                .withType(mpmbCreature.getType())
                .withAlignment(mpmbCreature.getAlignment())
                .withArmorClass(mpmbCreature.getAc())
                .withHitDice(new Dice(
                        Die.fromInteger(mpmbCreature.getHd().get(1)),
                        mpmbCreature.getHd().get(0)))
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
        return templateCreatureRepository.save(creature);
    }

    private CreatureSize getSize(int mpmbSize) {
        return switch (mpmbSize) {
            case 5 -> CreatureSize.TINY;
            case 4 -> CreatureSize.SMALL;
            case 3 -> CreatureSize.MEDIUM;
            case 2 -> CreatureSize.LARGE;
            case 1 -> CreatureSize.HUGE;
            case 0 -> CreatureSize.GARGANTUAN;
            default -> throw new IllegalArgumentException("Unexpected size " + mpmbSize);
        };
    }

    private Speed parseSpeed(String speed) {
        try {
            Matcher matcher = simpleSpeedPattern.matcher(speed);
            if (!matcher.matches()) {
                return new Speed(4);
            }
            int walkSpeed = Integer.parseInt(matcher.group(1));
            return new Speed(walkSpeed);
        } catch (NumberFormatException e) {
            // TODO complicated parsing
            return new Speed(4);
        }
    }

    private Attack buildAttack(MPMBCreature.Attack attack) {
        // Order is dice count, dice type, damage type
        List<String> damage = attack.getDamage();
        DamageType damageType = DamageType.valueOf(damage.get(2).toUpperCase(Locale.ENGLISH));
        return Attack.builder()
                .withName(attack.getName())
                .withDescription(attack.getDescription())
                .withDamage(List.of(new Damage(
                        Die.fromInteger(Integer.parseInt(damage.get(1))), Integer.parseInt(damage.get(0)), damageType)))
                .withRange(attack.getRange())
                .build();
    }
}
