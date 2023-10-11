import type { Encounter } from "$lib/types";
import { TestDataGenerator } from "$lib/test";

export function load({ params }): Encounter {
    // Make sure our IDs are consistent
    return { ...TestDataGenerator.getEncounter(), id: params.id };
}

export const prerender = false;