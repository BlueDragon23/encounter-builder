package dev.aidang.encounters.model.creatures;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class MPMBCreature {

    private String name;
    private List<String> nameAlt;
    private List<List<String>> source;
    private int size;
    private String type;
    private String alignment;
    private int ac;
    private int hp;
    private List<Integer> hd;
    private String speed;
    private List<Integer> scores;

    @JsonProperty("damage_resistances")
    private String damageResistances;

    @JsonProperty("damage_immunities")
    private String damageImmunities;

    @JsonProperty("condition_immunities")
    private String conditionImmunities;

    private String senses;
    private int passivePerception;
    private String languages;
    private String challengeRating;
    private int proficiencyBonus;
    private int attacksAction;
    private List<Attack> attacks;
    private List<Trait> traits;
    private List<Action> actions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getNameAlt() {
        return nameAlt;
    }

    public void setNameAlt(List<String> nameAlt) {
        this.nameAlt = nameAlt;
    }

    public List<List<String>> getSource() {
        return source;
    }

    public void setSource(List<List<String>> source) {
        this.source = source;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public int getAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public List<Integer> getHd() {
        return hd;
    }

    public void setHd(List<Integer> hd) {
        this.hd = hd;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }

    public String getDamageResistances() {
        return damageResistances;
    }

    public void setDamageResistances(String damageResistances) {
        this.damageResistances = damageResistances;
    }

    public String getDamageImmunities() {
        return damageImmunities;
    }

    public void setDamageImmunities(String damageImmunities) {
        this.damageImmunities = damageImmunities;
    }

    public String getConditionImmunities() {
        return conditionImmunities;
    }

    public void setConditionImmunities(String conditionImmunities) {
        this.conditionImmunities = conditionImmunities;
    }

    public String getSenses() {
        return senses;
    }

    public void setSenses(String senses) {
        this.senses = senses;
    }

    public int getPassivePerception() {
        return passivePerception;
    }

    public void setPassivePerception(int passivePerception) {
        this.passivePerception = passivePerception;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getChallengeRating() {
        return challengeRating;
    }

    public void setChallengeRating(String challengeRating) {
        this.challengeRating = challengeRating;
    }

    public int getProficiencyBonus() {
        return proficiencyBonus;
    }

    public void setProficiencyBonus(int proficiencyBonus) {
        this.proficiencyBonus = proficiencyBonus;
    }

    public int getAttacksAction() {
        return attacksAction;
    }

    public void setAttacksAction(int attacksAction) {
        this.attacksAction = attacksAction;
    }

    public List<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(List<Attack> attacks) {
        this.attacks = attacks;
    }

    public List<Trait> getTraits() {
        return traits;
    }

    public void setTraits(List<Trait> traits) {
        this.traits = traits;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MPMBCreature that = (MPMBCreature) o;
        return size == that.size
                && ac == that.ac
                && hp == that.hp
                && passivePerception == that.passivePerception
                && proficiencyBonus == that.proficiencyBonus
                && attacksAction == that.attacksAction
                && Objects.equals(name, that.name)
                && Objects.equals(type, that.type)
                && Objects.equals(alignment, that.alignment)
                && Objects.equals(hd, that.hd)
                && Objects.equals(speed, that.speed)
                && Objects.equals(scores, that.scores)
                && Objects.equals(damageResistances, that.damageResistances)
                && Objects.equals(damageImmunities, that.damageImmunities)
                && Objects.equals(conditionImmunities, that.conditionImmunities)
                && Objects.equals(senses, that.senses)
                && Objects.equals(languages, that.languages)
                && Objects.equals(challengeRating, that.challengeRating)
                && Objects.equals(attacks, that.attacks)
                && Objects.equals(traits, that.traits)
                && Objects.equals(actions, that.actions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                name,
                size,
                type,
                alignment,
                ac,
                hp,
                hd,
                speed,
                scores,
                damageResistances,
                damageImmunities,
                conditionImmunities,
                senses,
                passivePerception,
                languages,
                challengeRating,
                proficiencyBonus,
                attacksAction,
                attacks,
                traits,
                actions);
    }

    @Override
    public String toString() {
        return "MPMBCreature{" + "name='" + name + '\'' + '}';
    }

    public static class Attack {

        private String name;
        private int ability;
        private List<String> damage;
        private String range;
        private String description;
        private Boolean dc;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAbility() {
            return ability;
        }

        public void setAbility(int ability) {
            this.ability = ability;
        }

        public List<String> getDamage() {
            return damage;
        }

        public void setDamage(List<String> damage) {
            this.damage = damage;
        }

        public String getRange() {
            return range;
        }

        public void setRange(String range) {
            this.range = range;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Boolean getDc() {
            return dc;
        }

        public void setDc(Boolean dc) {
            this.dc = dc;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Attack attack = (Attack) o;
            return ability == attack.ability
                    && Objects.equals(name, attack.name)
                    && Objects.equals(damage, attack.damage)
                    && Objects.equals(range, attack.range)
                    && Objects.equals(description, attack.description)
                    && Objects.equals(dc, attack.dc);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, ability, damage, range, description, dc);
        }
    }

    public static class Trait {
        private String name;
        private String description;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Trait trait = (Trait) o;
            return Objects.equals(name, trait.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    public static class Action {
        private String name;
        private String description;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Action action = (Action) o;
            return Objects.equals(name, action.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
