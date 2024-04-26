import { useTestData } from '$lib';
import { TestDataGenerator } from '$lib/test';
import type { MonsterDetails } from '$lib/types';
import { withHost } from './utils';

export async function getMonster(id: string): Promise<MonsterDetails> {
	const testData = useTestData();
	if (testData) {
		return { ...TestDataGenerator.getMonster(), id };
	} else {
		return fetch(withHost(`/monsters/${id}`))
			.then((res) => res.json())
			.catch((err) => console.error(err));
	}
}
