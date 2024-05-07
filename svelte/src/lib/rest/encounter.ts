import type { ExistingEncounter, PageExistingEncounterSummary } from '$lib/encounters/types';
import type { components } from '$lib/generated/client';
import type { ExistingTemplateCreature } from '$lib/monsters/types';
import { getMonster } from './monster';
import { client, pageableQuerySerialiser, type SvelteFetch } from './utils';

export async function getEncounters(
	pageable: components['schemas']['Pageable'],
	fetch: SvelteFetch
): Promise<components['schemas']['PageEncounterSummary']> {
	const { data, error } = await client.GET('/encounters', {
		params: {
			query: {
				pageable
			}
		},
		fetch,
		querySerializer: pageableQuerySerialiser
	});
	if (error) {
		console.error(error);
	}
	if (!data) {
		throw new Error('Expected encounters, but found nothing');
	}
	return <PageExistingEncounterSummary>data;
}

export async function getEncounter(id: number, fetch: SvelteFetch): Promise<ExistingEncounter> {
	const { data, error } = await client.GET('/encounters/{id}', {
		params: {
			path: {
				id
			}
		},
		fetch
	});
	if (error) {
		console.error(error);
	}
	if (!data) {
		throw new Error('Expected an encounter, but found none');
	}
	return <ExistingEncounter>data;
}

export async function createEncounter(
	encounter: components['schemas']['Encounter'],
	fetch: SvelteFetch
): Promise<ExistingEncounter | undefined> {
	const { data, error } = await client.POST('/encounters', {
		body: encounter,
		fetch
	});
	return <ExistingEncounter>data;
}

export type EncounterMonsters = { [key: number]: ExistingTemplateCreature };

export async function getEncounterMonsters(
	encounter: ExistingEncounter,
	fetch: SvelteFetch
): Promise<EncounterMonsters> {
	if (!encounter.creatures) {
		return Promise.resolve({});
	} else {
		const monsters = await Promise.all(
			encounter.creatures.map((creature) => getMonster(creature.templateCreature.id, fetch))
		);
		return monsters?.reduce<EncounterMonsters>((accumulator, current) => {
			if (current && current.id) {
				accumulator[current.id] = current;
			}
			return accumulator;
		}, {});
	}
}
