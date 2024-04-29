import type { Monster } from '$lib/types';
import { getMonsters } from '$lib/rest/monster';

export interface MonstersPageData {
	monsters: Promise<Monster[]>;
}

export function load({}): MonstersPageData {
	return {
		monsters: getMonsters()
	};
}

export const prerender = false;
