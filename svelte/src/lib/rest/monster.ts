import { useTestData } from '$lib';
import type { components, operations } from '$lib/generated/client';
import type { ExistingTemplateCreature } from '$lib/monsters/types';
import { TestDataGenerator } from '$lib/test';
import { client, pageableQuerySerialiser, type SvelteFetch } from './utils';

export async function getMonster(
	id: number,
	fetch: SvelteFetch
): Promise<ExistingTemplateCreature> {
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
		if (!data) {
			throw new Error('Expected monster, but found nothing');
		}
		return <ExistingTemplateCreature>data;
	}
}

export async function getMonsters(
	pageable: components['schemas']['Pageable'],
	fetch: SvelteFetch,
	query: Omit<operations['getMonsters']['parameters']['query'], 'pageable'>
): Promise<components['schemas']['PageTemplateCreatureSummary']> {
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
		if (!data) {
			throw new Error('Expected monsters, but found nothing');
		}
		return data;
	}
}

export async function createMonster(
	monster: components['schemas']['TemplateCreature'],
	fetch: SvelteFetch
): Promise<ExistingTemplateCreature> {
	const { data, error } = await client.POST('/monsters', {
		body: monster,
		fetch
	});
	if (error) {
		console.error(error);
	}
	if (!data) {
		throw new Error('Expected created monster, but found nothing');
	}
	return <ExistingTemplateCreature>data;
}
