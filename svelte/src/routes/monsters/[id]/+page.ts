import { getMonster } from '$lib/rest/monster.js';
import type { components } from '$lib/generated/client';
import type { ExistingTemplateCreature } from '$lib/monsters/types.js';

export async function load({ fetch, params }): Promise<ExistingTemplateCreature> {
	return await getMonster(parseInt(params.id), fetch);
}

export const prerender = false;
