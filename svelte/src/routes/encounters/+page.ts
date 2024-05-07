import { TestDataGenerator } from '$lib/test';
import { useTestData } from '$lib';
import { getEncounters } from '$lib/rest/encounter.js';
import type { components } from '$lib/generated/client.js';
import { pageable } from '$lib/rest/utils.js';

export interface EncountersPageData {
	encounters: Promise<components['schemas']['PageEncounterSummary']>;
}

export function load({ url, fetch }): EncountersPageData {
	if (useTestData()) {
		return {
			encounters: Promise.resolve({
				content: [
					TestDataGenerator.getEncounter(),
					TestDataGenerator.getEncounter(),
					TestDataGenerator.getEncounter(),
					TestDataGenerator.getEncounter()
				]
			})
		};
	} else {
		const page = pageable(url);
		return {
			encounters: getEncounters(page, fetch)
		};
	}
}

export const prerender = false;
