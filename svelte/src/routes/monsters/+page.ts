import type { Monster } from '$lib/types';
import { TestDataGenerator } from '$lib/test';

export function load({}): { monsters: Monster[] } {
	return {
		monsters: [
			TestDataGenerator.getMonster(),
			TestDataGenerator.getMonster(),
			TestDataGenerator.getMonster(),
			TestDataGenerator.getMonster()
		]
	};
}

export const prerender = false;
