import { getMonsters } from '$lib/rest/monster';
import type { components } from '$lib/generated/client';

export interface MonstersPageData {
	monsters: Promise<components['schemas']['PageTemplateCreatureSummary'] | undefined>;
}

export function load({ url, fetch }): MonstersPageData {
	let page = url.searchParams.get('page') || 0;
	let size = url.searchParams.get('size') || 20;
	let name = url.searchParams.get('name') || '';
	return {
		monsters: getMonsters({ size: size as number, page: page as number }, fetch, { name })
	};
}

export const prerender = false;
