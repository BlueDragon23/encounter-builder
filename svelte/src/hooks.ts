import type { HandleClientError, HandleFetch } from '@sveltejs/kit';

export const handleError: HandleClientError = async ({ error, event, status, message }) => {
	console.error(error);
	console.error(event);
	console.error(status);
	console.error(message);
};

export const handleFetch: HandleFetch = async ({ request, fetch }) => {
	console.log(`Rewriting request ${request} with URL ${request.url}`);
	if (request.url.startsWith('http://localhost')) {
		request = new Request(request.url.replace('http://localhost', 'http://web'), request);
	}
	return fetch(request);
};
