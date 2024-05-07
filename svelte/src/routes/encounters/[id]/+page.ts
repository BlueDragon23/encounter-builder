import { TestDataGenerator } from '$lib/test';
import type { components } from '$lib/generated/client.js';
import { useTestData } from '$lib';
import { getEncounter, getEncounterMonsters, type EncounterMonsters } from '$lib/rest/encounter.js';
import type { ExistingEncounter } from '$lib/encounters/types.js';

export interface EncounterPageData {
	encounter: ExistingEncounter;
	encounterMonsters: Promise<EncounterMonsters>;
}

export async function load({ params, fetch }): Promise<EncounterPageData> {
	if (useTestData()) {
		// Make sure our IDs are consistent
		return {
			encounter: { ...TestDataGenerator.getEncounter(), id: parseInt(params.id) },
			encounterMonsters: Promise.resolve({})
		};
	} else {
		const encounter = await getEncounter(parseInt(params.id), fetch);
		return {
			encounter,
			encounterMonsters: getEncounterMonsters(encounter, fetch)
		};
	}
}

export const prerender = false;
