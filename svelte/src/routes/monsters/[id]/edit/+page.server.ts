import { getField } from '$lib/forms/utils';
import { redirect } from '@sveltejs/kit';

export const actions = {
	default: async ({ fetch, request }) => {
		const data = await request.formData();
		const id = getField(data, 'id');
		// TODO: save actions
		redirect(302, `/monsters/${id}`);
	}
};

export const prerender = false;
