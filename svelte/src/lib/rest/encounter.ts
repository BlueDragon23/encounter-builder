import type { components } from '$lib/generated/client';
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
	return data;
}

export async function getEncounter(
	id: number,
	fetch: SvelteFetch
): Promise<components['schemas']['Encounter']> {
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
	return data;
}

export async function createEncounter(
	encounter: components['schemas']['Encounter'],
	fetch: SvelteFetch
): Promise<components['schemas']['Encounter'] | undefined> {
	const { data, error } = await client.POST('/encounters', {
		body: encounter,
		fetch
	});
	return data;
}
