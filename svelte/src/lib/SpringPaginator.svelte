<script lang="ts">
	import { Paginator, type PaginationSettings } from '@skeletonlabs/skeleton';
	import type { PageableResponse } from './rest/utils';
	import { goto } from '$app/navigation';

	function getPaginationSettings(pagination: PageableResponse<any>): PaginationSettings {
		return {
			page: pagination.number,
			limit: pagination.size,
			size: pagination.totalElements,
			amounts: [5, 10, 20, 50]
		} satisfies PaginationSettings;
	}

	export let pageable: PageableResponse<any>;
</script>

<Paginator
	settings={getPaginationSettings(pageable)}
	on:page={(number) => goto(`?page=${number.detail}&limit=${pageable.pageable.pageSize}`)}
	on:amount={(amount) => goto(`?page=${pageable.pageable.pageNumber}&limit=${amount.detail}`)}
	showNumerals
/>
