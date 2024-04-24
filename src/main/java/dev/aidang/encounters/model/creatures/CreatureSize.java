package dev.aidang.encounters.model.creatures;

import com.fasterxml.jackson.annotation.JsonAlias;

public enum CreatureSize {
    @JsonAlias("Tiny")
    TINY,
    @JsonAlias("Small")
    SMALL,
    @JsonAlias("Medium")
    MEDIUM,
    @JsonAlias("Large")
    LARGE,
    @JsonAlias("Huge")
    HUGE,
    @JsonAlias("Gargantuan")
    GARGANTUAN
}
