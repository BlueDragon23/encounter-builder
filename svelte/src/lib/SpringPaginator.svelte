<script lang="ts">
	import { Paginator, type PaginationSettings } from '@skeletonlabs/skeleton';
	import type { PageableResponse } from './rest/utils';
	import { goto } from '$app/navigation';
	import { page } from '$app/stores';

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

<!-- on:page={(number) => goto(`?page=${number.detail}&size=${pageable.pageable?.pageSize}`)} -->
<div class="my-4">
	<Paginator
		settings={getPaginationSettings(pageable)}
		on:page={(number) => {
			const params = new URLSearchParams($page.url.searchParams);
			params.set('page', number.detail.toString());
			goto(`?${params.toString()}`);
		}}
		on:amount={(amount) => {
			const params = new URLSearchParams($page.url.searchParams);
			params.set('size', amount.detail.toString());
			goto(`?${params.toString()}`);
		}}
		showNumerals
	/>
</div>
