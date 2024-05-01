<script lang="ts">
	import { Paginator, type PaginationSettings } from '@skeletonlabs/skeleton';
	import type { PageableResponse } from './rest/utils';
	import { goto } from '$app/navigation';

	function getPaginationSettings(pagination: PageableResponse<any>): PaginationSettings {
		return {
			page: pagination.number ?? 1,
			limit: pagination.size ?? 20,
			size: pagination.totalElements ?? 1,
			amounts: [5, 10, 20, 50]
		} satisfies PaginationSettings;
	}

	export let pageable: PageableResponse<any>;
</script>

<div class="my-4">
	<Paginator
		settings={getPaginationSettings(pageable)}
		on:page={(number) => goto(`?page=${number.detail}&size=${pageable.pageable?.pageSize}`)}
		on:amount={(amount) => goto(`?page=${pageable.pageable?.pageNumber}&size=${amount.detail}`)}
		showNumerals
	/>
</div>
