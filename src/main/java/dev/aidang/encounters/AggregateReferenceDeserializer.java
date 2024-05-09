package dev.aidang.encounters;

import static com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_INT;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import java.io.IOException;
import java.util.List;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

class AggregateReferenceDeserializer extends StdDeserializer<AggregateReference<?, ?>> {
    public AggregateReferenceDeserializer() {
        super(AggregateReference.class);
    }

    @Override
    public AggregateReference<?, ?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JacksonException {
        // Expect { "id": value }
        JsonToken fieldValue = jsonParser.nextValue();
        if (jsonParser.getCurrentName() == null || !"id".equals(jsonParser.getCurrentName())) {
            throw new UnrecognizedPropertyException(
                    jsonParser,
                    "Unexpected field %s".formatted(jsonParser.getCurrentName()),
                    jsonParser.currentLocation(),
                    AggregateReference.class,
                    jsonParser.getCurrentName(),
                    List.of());
        }
        if (fieldValue != VALUE_NUMBER_INT) {
            throw new UnrecognizedPropertyException(
                    jsonParser,
                    "Unexpected value %s".formatted(fieldValue.asString()),
                    jsonParser.currentLocation(),
                    AggregateReference.class,
                    fieldValue.asString(),
                    List.of());
        }
        long longValue = jsonParser.getLongValue();
        // Read the final close object token, otherwise this gets very confused
        jsonParser.nextToken();
        return AggregateReference.to(longValue);
    }
}
