package dev.aidang.encounters;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

public class Utils {

    public static final JsonMapper JSON = JsonMapper.builder()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .addModule(
                    new SimpleModule().addDeserializer(AggregateReference.class, new AggregateReferenceDeserializer()))
            .build();
}
