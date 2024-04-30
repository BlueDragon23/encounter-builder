import { useTestData } from '$lib';
import { TestDataGenerator } from '$lib/test';
import type { MonsterDetails } from '$lib/types';
import { withHost, type PageableRequest, type PageableResponse } from './utils';

export async function getMonster(id: string): Promise<MonsterDetails> {
	const testData = useTestData();
	if (testData) {
		return { ...TestDataGenerator.getMonster(), id };
	} else {
		return fetch(withHost(`/monsters/${id}`, true))
			.then((res) => res.json())
			.catch((err) => console.error(err));
	}
}

export async function getMonsters(
	request: PageableRequest
): Promise<PageableResponse<MonsterDetails>> {
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
			sort: {
				unsorted: true,
				sorted: false,
				empty: false
			},
			first: true,
			empty: false
		};
	} else {
		return fetch(
			withHost(
				`/monsters?size=${request.pageSize}&page=${request.pageNumber}&offset=${request.offset}`,
				false
			)
		)
			.then((res) => res.json())
			.catch((err) => console.error(err)) as Promise<PageableResponse<MonsterDetails>>;
	}
}
