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

export const damageTypeOptions = [
	{ label: 'bludgeoning', value: 'bludgeoning', keywords: 'physical' },
	{ label: 'piercing', value: 'piercing', keywords: 'physical' },
	{ label: 'slashing', value: 'slashing', keywords: 'physical' },
	{ label: 'lightning', value: 'lightning' },
	{ label: 'thunder', value: 'thunder' },
	{ label: 'poison', value: 'poison' },
	{ label: 'cold', value: 'cold' },
	{ label: 'radiant', value: 'radiant' },
	{ label: 'fire', value: 'fire' },
	{ label: 'necrotic', value: 'necrotic' },
	{ label: 'acid', value: 'acid' },
	{ label: 'psychic', value: 'psychic' },
	{ label: 'force', value: 'force' }
];
