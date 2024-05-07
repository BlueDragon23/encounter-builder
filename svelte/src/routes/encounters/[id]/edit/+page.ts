import type { ExistingEncounter } from '$lib/encounters/types.js';
import { getEncounter, getEncounterMonsters, type EncounterMonsters } from '$lib/rest/encounter';

export interface EditEncounterPageData {
	encounter: ExistingEncounter;
	encounterMonsters: Promise<EncounterMonsters>;
}

export async function load({ params, fetch }): Promise<EditEncounterPageData> {
	const encounter = await getEncounter(parseInt(params.id), fetch);
	return {
		encounter,
		encounterMonsters: getEncounterMonsters(encounter, fetch)
	};
}

export const prerender = false;
