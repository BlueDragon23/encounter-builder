import type { MonsterDetails } from '$lib/types';
import { getMonster } from '$lib/rest/monster.js';

export async function load({ params }): Promise<MonsterDetails> {
	console.log(`Getting monster ${params.id}`);
	return await getMonster(params.id);
}

export const prerender = false;
