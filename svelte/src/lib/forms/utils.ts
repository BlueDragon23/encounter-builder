import type { components } from '$lib/generated/client';

export function getField(formData: FormData, name: string): string {
	// TODO: error handling
	return <string>formData.get(name);
}

export function getNumberField(formData: FormData, name: string): number {
	// TODO: validate min/max
	return parseInt(<string>formData.get(name));
}

export function oneOf<T>(value: string, allowedValues: T[]): T | undefined {
	if (value in allowedValues) {
		return <T>value;
	} else {
		return undefined;
	}
}

export interface SelectOptions<T> {
	label: string;
	value: T;
	keywords?: string;
}

export const damageTypeOptions: SelectOptions<
	Exclude<components['schemas']['Damage']['damageType'], undefined>
>[] = [
	{ label: 'bludgeoning', value: 'BLUDGEONING', keywords: 'physical' },
	{ label: 'piercing', value: 'PIERCING', keywords: 'physical' },
	{ label: 'slashing', value: 'SLASHING', keywords: 'physical' },
	{ label: 'lightning', value: 'LIGHTNING' },
	{ label: 'thunder', value: 'THUNDER' },
	{ label: 'poison', value: 'POISON' },
	{ label: 'cold', value: 'COLD' },
	{ label: 'radiant', value: 'RADIANT' },
	{ label: 'fire', value: 'FIRE' },
	{ label: 'necrotic', value: 'NECROTIC' },
	{ label: 'acid', value: 'ACID' },
	{ label: 'psychic', value: 'PSYCHIC' },
	{ label: 'force', value: 'FORCE' }
];
