import { getMonsters } from '$lib/rest/monster';
import type { components } from '$lib/generated/client';

export interface MonstersPageData {
	monsters: Promise<components['schemas']['PageTemplateCreature'] | undefined>;
}

export function load({ url, fetch }): MonstersPageData {
	let page = url.searchParams.get('page') || 0;
	let size = url.searchParams.get('size') || 20;
	return {
		monsters: getMonsters({ size: size as number, page: page as number }, fetch)
	};
}

export const prerender = false;
