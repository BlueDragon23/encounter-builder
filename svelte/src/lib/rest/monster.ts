import { useTestData } from '$lib';
import { TestDataGenerator } from '$lib/test';
import type { Monster, MonsterDetails } from '$lib/types';

export async function getMonster(id: string): Promise<MonsterDetails> {
	const testData = useTestData();
	if (testData) {
		return { ...TestDataGenerator.getEncounter(), id };
	} else {
		// TODO: what is my server URL?
		return fetch(`http://localhost:8080/monsters/${id}`)
			.then((res) => res.json())
			.catch((err) => console.error(err));
	}
}
