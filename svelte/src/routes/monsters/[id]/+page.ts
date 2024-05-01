import { getMonster } from '$lib/rest/monster.js';
import type { components } from '$lib/generated/client';

export async function load({
	fetch,
	params
}): Promise<components['schemas']['TemplateCreature'] | undefined> {
	console.log(`Getting monster ${params.id}`);
	return await getMonster(parseInt(params.id), fetch);
}

export const prerender = false;
