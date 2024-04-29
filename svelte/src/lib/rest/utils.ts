export function withHost(path: string, ssr: boolean): string {
	// TODO: make this dynamic
	if (ssr) {
		return `http://web:8080${path}`;
	} else {
		return `http://localhost:8080${path}`;
	}
}

export interface PageableResponse<T> {
	content: T[];
	pageable: {
		pageNumber: number;
		pageSize: number;
		offset: number;
	};
	totalPages: number;
	totalElements: number;
	last: boolean;
	numberOfElements: number;
	size: number;
	number: number;
	sort: {
		unsorted: boolean;
		sorted: boolean;
		empty: boolean;
	};
	first: boolean;
	empty: boolean;
}
