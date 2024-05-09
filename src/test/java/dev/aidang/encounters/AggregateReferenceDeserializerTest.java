package dev.aidang.encounters;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

@ExtendWith(MockitoExtension.class)
class AggregateReferenceDeserializerTest {

    private ObjectMapper sut;

    @BeforeEach
    void setup() {
        AggregateReferenceDeserializer aggregateReferenceDeserializer = new AggregateReferenceDeserializer();
        sut = new ObjectMapper()
                .registerModule(
                        new SimpleModule().addDeserializer(AggregateReference.class, aggregateReferenceDeserializer));
    }

    @Test
    void testDeserialise() throws JsonProcessingException {
        // when
        String input = "{ \"id\": 4 }";

        // then
        AggregateReference<?, ?> aggregateReference = sut.readValue(input, AggregateReference.class);

        // verify
        assertThat(aggregateReference.getId()).isEqualTo(4L);
    }

    @Test
    void testDeserialiseList() throws JsonProcessingException {
        // when
        String input = """
                [{"id": 4}, {"id": 5}]
                """;

        // then
        List<AggregateReference<?, ?>> references = sut.readValue(input, new TypeReference<>() {});

        // verify
        assertThat(references).containsExactly(AggregateReference.to(4L), AggregateReference.to(5L));
    }
}
