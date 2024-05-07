import { TestDataGenerator } from '$lib/test';
import type { components } from '$lib/generated/client.js';
import { useTestData } from '$lib';
import { getEncounter } from '$lib/rest/encounter.js';
import { getMonster } from '$lib/rest/monster.js';

type EncounterMonsters = { [key: number]: components['schemas']['TemplateCreature'] };

export interface EncounterPageData {
	encounter: components['schemas']['Encounter'];
	encounterMonsters: Promise<EncounterMonsters>;
}

async function getEncounterMonsters(
	encounter: components['schemas']['Encounter']
): Promise<EncounterMonsters> {
	if (!encounter.creatures) {
		return Promise.resolve({});
	} else {
		const monsters = await Promise.all(
			encounter.creatures.map((creature) => getMonster(creature.id ?? 0, fetch))
		);
		return monsters?.reduce<EncounterMonsters>((accumulator, current) => {
			if (current && current.id) {
				accumulator[current.id] = current;
			}
			return accumulator;
		}, {});
	}
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
			encounterMonsters: getEncounterMonsters(encounter)
		};
	}
}

export const prerender = false;
