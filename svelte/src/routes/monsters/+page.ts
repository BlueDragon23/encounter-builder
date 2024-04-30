import type { MonsterDetails } from '$lib/types';
import { getMonsters } from '$lib/rest/monster';
import type { PageableResponse } from '$lib/rest/utils.js';

export interface MonstersPageData {
	monsters: Promise<PageableResponse<MonsterDetails>>;
}

export function load({ url }): MonstersPageData {
	let page = url.searchParams.get('page') || 1;
	let limit = url.searchParams.get('limit') || 20;
	return {
		monsters: getMonsters({ pageSize: limit as number, pageNumber: page as number, offset: 0 })
	};
}

export const prerender = false;
