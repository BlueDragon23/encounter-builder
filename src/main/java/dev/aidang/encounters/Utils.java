package dev.aidang.encounters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.json.JsonMapper;

public class Utils {

    public static final JsonMapper JSON = JsonMapper
            .builder()
            .enable(JsonGenerator.Feature.IGNORE_UNKNOWN)
            .build();
}
