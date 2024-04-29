import type { HandleClientError } from '@sveltejs/kit';

export const handleError: HandleClientError = async ({ error, event, status, message }) => {
	console.error(error);
	console.error(event);
	console.error(status);
	console.error(message);
};
