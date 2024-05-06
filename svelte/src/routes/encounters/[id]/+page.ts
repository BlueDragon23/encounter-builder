import { TestDataGenerator } from '$lib/test';
import type { components } from '$lib/generated/client.js';
import { useTestData } from '$lib';
import { getEncounter } from '$lib/rest/encounter.js';

export function load({ params, fetch }): Promise<components['schemas']['Encounter'] | undefined> {
	if (useTestData()) {
		// Make sure our IDs are consistent
		return Promise.resolve({ ...TestDataGenerator.getEncounter(), id: parseInt(params.id) });
	} else {
		return getEncounter(params.id, fetch);
	}
}

export const prerender = false;
