import type { Encounter } from "$lib/types";
import { TestDataGenerator } from "$lib/test";

export function load({ }): { "encounters": Encounter[] } {
    return {
        "encounters": [
            TestDataGenerator.getEncounter(),
            TestDataGenerator.getEncounter(),
            TestDataGenerator.getEncounter(),
            TestDataGenerator.getEncounter()
        ]
    };
}