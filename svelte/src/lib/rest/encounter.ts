import type { components } from '$lib/generated/client';
import { client, pageableQuerySerialiser, type SvelteFetch } from './utils';

export async function getEncounters(
	pageable: components['schemas']['Pageable'],
	fetch: SvelteFetch
): Promise<components['schemas']['PageEncounterSummary'] | undefined> {
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
	return data;
}

export async function getEncounter(
	name: string,
	fetch: SvelteFetch
): Promise<components['schemas']['Encounter'] | undefined> {
	const { data, error } = await client.GET('/encounters/{name}', {
		params: {
			path: {
				name
			}
		},
		fetch
	});
	if (error) {
		console.error(error);
	}
	return data;
}
