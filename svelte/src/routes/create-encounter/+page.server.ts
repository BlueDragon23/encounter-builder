import { createEncounter } from '$lib/rest/encounter';
import { redirect, type Actions } from '@sveltejs/kit';

export const actions = {
	default: async ({ fetch, request }) => {
		const data = await request.formData();
		// TODO: validation, limits
		const created = await createEncounter(
			{
				name: (data.get('name') as string) || undefined,
				description: (data.get('description') as string) || undefined
			},
			fetch
		);
		// TODO: more error handling
		redirect(302, `/encounters/${created?.id}`);
	}
} satisfies Actions;

export const prerender = false;
