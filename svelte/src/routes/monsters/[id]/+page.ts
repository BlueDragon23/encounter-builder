import type { Monster } from '$lib/types';
import { getMonster } from '$lib/rest/monster.js';

export async function load({ params }): Promise<Monster> {
	return await getMonster(params.id);
}

export const prerender = false;
