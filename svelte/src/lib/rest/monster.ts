import { useTestData } from '$lib';
import { TestDataGenerator } from '$lib/test';
import type { MonsterDetails } from '$lib/types';
import { withHost, type PageableResponse } from './utils';

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

export async function getMonsters(): Promise<MonsterDetails[]> {
	const testData = useTestData();
	if (testData) {
		return [
			TestDataGenerator.getMonster(),
			TestDataGenerator.getMonster(),
			TestDataGenerator.getMonster(),
			TestDataGenerator.getMonster()
		];
	} else {
		return fetch(withHost(`/monsters`, false))
			.then((res) =>
				res.json().then((pageable: PageableResponse<MonsterDetails>) => pageable.content)
			)
			.catch((err) => console.error(err)) as Promise<MonsterDetails[]>;
	}
}
