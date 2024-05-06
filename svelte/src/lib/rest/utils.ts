import type { components, paths } from '$lib/generated/client';
import createClient, { type QuerySerializerOptions } from 'openapi-fetch';

export type SvelteFetch = (input: RequestInfo | URL, init?: RequestInit) => Promise<Response>;

export function withHost(path: string): string {
	return `http://localhost:8080${path}`;
}

export function pageable(url: URL): components['schemas']['Pageable'] {
	const page = parseInt(url.searchParams.get('page') ?? '0');
	const size = parseInt(url.searchParams.get('size') ?? '20');
	return {
		page,
		size
	};
}

/**
 * Generic form of pageable response, e.g. the PageTemplateCreature
 */
export interface PageableResponse<T> {
	content?: T[];
	pageable?: components['schemas']['PageableObject'];
	totalPages?: number;
	totalElements?: number;
	last?: boolean;
	numberOfElements?: number;
	size?: number;
	number?: number;
	sort?: components['schemas']['SortObject'][];
	first?: boolean;
	empty?: boolean;
}

export const pageableQuerySerialiser: QuerySerializerOptions = {
	object: {
		style: 'form',
		explode: true
	}
};

export const client = createClient<paths>({ baseUrl: 'http://localhost:8080' });
