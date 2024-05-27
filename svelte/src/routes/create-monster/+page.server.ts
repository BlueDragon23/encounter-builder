import { getField, getNumberField, oneOf } from '$lib/forms/utils';
import { createMonster } from '$lib/rest/monster';
import { redirect, type Actions } from '@sveltejs/kit';

export const actions = {
	default: async ({ fetch, request }) => {
		const data = await request.formData();
		// TODO: validation, limits
		// TODO: extract this for use in edit monster
		const created = await createMonster(
			{
				name: getField(data, 'name'),
				description: getField(data, 'description'),
				creatureSize: oneOf(getField(data, 'creatureSize'), [
					'TINY',
					'SMALL',
					'MEDIUM',
					'LARGE',
					'HUGE',
					'GARGANTUAN'
				]),
				type: getField(data, 'type'),
				alignment: getField(data, 'alignment'),
				armorClass: getNumberField(data, 'armorClass'),
				challengeRating: getNumberField(data, 'challengeRating'),
				proficiencyBonus: getNumberField(data, 'proficiencyBonus'),
				hitpoints: getNumberField(data, 'hitpoints'),
				speed: {
					walk: getNumberField(data, 'speed_walk'),
					swim: getNumberField(data, 'speed_swim'),
					fly: getNumberField(data, 'speed_fly'),
					climb: getNumberField(data, 'speed_climb'),
					burrow: getNumberField(data, 'speed_burrow')
				},
				abilityScores: {
					strength: getNumberField(data, 'ability_strength'),
					dexterity: getNumberField(data, 'ability_dexterity'),
					constitution: getNumberField(data, 'ability_constitution'),
					wisdom: getNumberField(data, 'ability_wisdom'),
					intelligence: getNumberField(data, 'ability_intelligence'),
					charisma: getNumberField(data, 'ability_charisma')
				}
			},
			fetch
		);
		// TODO: more error handling
		redirect(302, `/monsters/${created?.id}`);
	}
} satisfies Actions;

export const prerender = false;
