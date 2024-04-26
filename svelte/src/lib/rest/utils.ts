export function withHost(path: string): string {
	// TODO: make this dynamic
	return `web:8080/${path}`;
}
