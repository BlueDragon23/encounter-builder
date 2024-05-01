import type { components, paths } from '$lib/generated/client';
import createClient, { type QuerySerializerOptions } from 'openapi-fetch';

export function withHost(path: string): string {
	return `http://localhost:8080${path}`;
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
