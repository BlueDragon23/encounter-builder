import type { MonsterDetails } from '$lib/types';
import { getMonster } from '$lib/rest/monster.js';

export async function load({ params }): Promise<MonsterDetails> {
	return await getMonster(params.id);
}

export const prerender = false;
