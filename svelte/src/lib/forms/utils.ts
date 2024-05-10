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
