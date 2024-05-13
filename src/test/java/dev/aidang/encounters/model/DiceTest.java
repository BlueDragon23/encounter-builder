package dev.aidang.encounters.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DiceTest {

    @ParameterizedTest
    @MethodSource("testDice")
    void testValidDice(String input, Dice expected) {
        // when/then
        Dice result = Dice.parse(input);

        // verify
        assertThat(result.count()).isEqualTo(expected.count());
        assertThat(result.die()).isEqualTo(expected.die());
        assertThat(result.modifier()).isEqualTo(expected.modifier());
    }

    private static Stream<Arguments> testDice() {
        return Stream.of(
                Arguments.of("3d6", new Dice(Die.D6, 3, 0)),
                Arguments.of("5d10", new Dice(Die.D10, 5, 0)),
                Arguments.of("1d8 + 4", new Dice(Die.D8, 1, 4)),
                Arguments.of("3d6+7", new Dice(Die.D6, 3, 7)),
                Arguments.of("1d12-1", new Dice(Die.D12, 1, -1)),
                // This is how much damage a badgers bite does
                Arguments.of("1", new Dice(Die.D6, 0, 1)));
    }
}
