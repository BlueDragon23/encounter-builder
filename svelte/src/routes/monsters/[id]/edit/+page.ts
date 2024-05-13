import type { ExistingTemplateCreature } from '$lib/monsters/types.js';
import { getMonster } from '$lib/rest/monster.js';

export interface EditMonsterPageData {
	creature: ExistingTemplateCreature;
}

export async function load({ params, fetch }): Promise<EditMonsterPageData> {
	const monster = await getMonster(parseInt(params.id), fetch);
	return {
		creature: monster
	};
}

export const prerender = false;
