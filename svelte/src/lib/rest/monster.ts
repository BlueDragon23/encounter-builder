import { useTestData } from '$lib';
import type { components, operations } from '$lib/generated/client';
import { TestDataGenerator } from '$lib/test';
import { client, pageableQuerySerialiser } from './utils';

type SvelteFetch = (input: RequestInfo | URL, init?: RequestInit) => Promise<Response>;

export async function getMonster(
	id: number,
	fetch: SvelteFetch
): Promise<components['schemas']['TemplateCreature'] | undefined> {
	const testData = useTestData();
	if (testData) {
		return { ...TestDataGenerator.getMonster(), id };
	} else {
		const { data, error } = await client.GET('/monsters/{id}', {
			fetch,
			params: {
				path: {
					id
				}
			}
		});
		if (error) {
			console.error(error);
		}
		return data;
	}
}

export async function getMonsters(
	pageable: components['schemas']['Pageable'],
	fetch: SvelteFetch,
	query: Omit<operations['getMonsters']['parameters']['query'], 'pageable'>
): Promise<components['schemas']['PageTemplateCreatureSummary'] | undefined> {
	const testData = useTestData();
	if (testData) {
		return {
			content: [
				TestDataGenerator.getMonster(),
				TestDataGenerator.getMonster(),
				TestDataGenerator.getMonster(),
				TestDataGenerator.getMonster()
			],
			pageable: {
				pageNumber: 0,
				pageSize: 4,
				offset: 0
			},
			totalPages: 1,
			totalElements: 4,
			last: true,
			numberOfElements: 4,
			size: 4,
			number: 4,
			first: true,
			empty: false
		};
	} else {
		const { data, error } = await client.GET('/monsters', {
			fetch,
			params: {
				query: {
					pageable,
					...query
				}
			},
			querySerializer: pageableQuerySerialiser
		});
		if (error) {
			console.error(error);
		}
		return data;
	}
}
