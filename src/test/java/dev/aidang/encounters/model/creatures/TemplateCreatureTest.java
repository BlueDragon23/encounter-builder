package dev.aidang.encounters.model.creatures;

import static dev.aidang.encounters.Utils.JSON;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TemplateCreatureTest {

    @Test
    void testSerialised() throws JsonProcessingException {
        // when
        TemplateCreature templateCreature = TemplateCreature.builder("Dragon")
                .withArmorClass(10)
                .withChallengeRating(2)
                .withProficiencyBonus(2)
                .withHitpoints(100)
                .build();

        // then
        String creatureString = JSON.writeValueAsString(templateCreature);

        // verify
        assertThat(creatureString)
                .isEqualToIgnoringWhitespace(
                        """
                {
                    "name": "Dragon",
                    "armorClass": 10,
                    "challengeRating": 2.0,
                    "proficiencyBonus": 2,
                    "hitpoints": 100,
                    "attacks": [],
                    "specialAbilities": [],
                    "savingThrows": []
                }
                """);
    }

    @Test
    void testDeserialisation() throws JsonProcessingException {
        // when
        String creatureString =
                """
                {
                    "name": "Dragon"
                }
                """;

        // then
        TemplateCreature creature = JSON.readValue(creatureString, TemplateCreature.class);

        // verify
        assertThat(creature.name()).isEqualTo("Dragon");
    }

    @Test
    void testValidation() {
        // when
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = validatorFactory.getValidator();
            TemplateCreature templateCreature =
                    TemplateCreature.builder("").withHitpoints(100).build();

            // then
            Set<ConstraintViolation<TemplateCreature>> violations = validator.validate(templateCreature);

            // verify
            assertThat(violations).singleElement().satisfies(e -> {
                assertThat(e.getPropertyPath()).singleElement().satisfies(n -> assertThat(n.getName())
                        .isEqualTo("name"));
                assertThat(e.getMessage()).isEqualTo("size must be between 1 and 255");
            });
        }
    }
}
