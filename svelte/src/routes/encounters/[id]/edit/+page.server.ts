import type { ExistingEncounterCreatureRef } from '$lib/encounters/types';
import { updateEncounter } from '$lib/rest/encounter';
import { redirect, type Actions } from '@sveltejs/kit';

const matchIndex = /\[(\d+)\]/;
const matchFieldName = /\[\d+\]\[(.+)\]/;

export const actions = {
	default: async ({ fetch, params, request }) => {
		const data = await request.formData();
		console.log(params);
		console.log(data);

		const creatures: ExistingEncounterCreatureRef[] = [];
		const creatureFields: [string, FormDataEntryValue][] = [...data.entries()]
			.filter((entry) => entry[0].startsWith('creatures'))
			.map((entry) => [entry[0].replace('creatures', ''), entry[1]]);

		for (let entry of creatureFields) {
			// Separate array elements
			const result = matchIndex.exec(entry[0]);
			const index = result?.[1];
			const fieldResult = matchFieldName.exec(entry[0]);
			const fieldName = fieldResult?.[1];
			if (!index || !fieldName) {
				console.error(`Expected an index and field name, but only found ${index} and ${fieldName}`);
				continue;
			}
			const indexNumber = parseInt(index);
			if (creatures.length <= indexNumber) {
				// It will be right later
				creatures.push(<ExistingEncounterCreatureRef>{});
			}
			switch (fieldName) {
				case 'id':
					creatures[indexNumber]['id'] = parseInt(entry[1] as string);
					break;
				case 'templateCreatureId':
					creatures[indexNumber]['templateCreature'] = {
						id: parseInt(entry[1] as string)
					};
					break;
				case 'count':
					creatures[indexNumber]['count'] = parseInt(entry[1] as string);
					break;
				default:
					console.log(`Found unexpected field ${fieldName}`);
					break;
			}
		}
		console.log(`Assembled creatures are ${JSON.stringify(creatures)}`);
		// TODO: validation, limits
		const created = await updateEncounter(
			{
				id: parseInt(params.id as string),
				name: data.get('name') as string,
				description: data.get('description') as string,
				creatures
			},
			fetch
		);
		// TODO: more error handling
		redirect(302, `/encounters/${params.id}`);
	}
} satisfies Actions;

export const prerender = false;
