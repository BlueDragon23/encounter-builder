<script lang="ts">
	import MonsterCard from '$lib/monsters/MonsterCard.svelte';
	import type { MonstersPageData } from './+page';
	import SpringPaginator from '$lib/SpringPaginator.svelte';
	import { page } from '$app/stores';

	export let data: MonstersPageData;
</script>

<div class="p-4">
	<div class="flex justify-between">
		<h1 class="h1">Monsters</h1>
		<a href="/create-monster" class="btn variant-filled-primary">Create Monster</a>
	</div>

	<form method="get">
		<label for="name">Name:</label>
		<input
			class="input text-token"
			id="name"
			name="name"
			title="Monster Name"
			type="text"
			placeholder="Adult Blue Dragon"
			value={$page.url.searchParams.get('name') ?? ''}
		/>
		<button class="btn variant-filled-primary" type="submit">Search</button>
	</form>

	<div class="flex flex-col items-center mt-4">
		<ul class="list md:container md:mx-auto">
			{#await data.monsters then monsters}
				{#if monsters && monsters.content}
					{#each monsters.content as monster}
						<li>
							<MonsterCard
								id={monster.id ?? 0}
								name={monster.name ?? ''}
								description={monster.description}
							/>
						</li>
					{/each}
				{/if}
			{/await}
		</ul>
	</div>
	{#await data.monsters then pagination}
		{#if pagination}
			<SpringPaginator pageable={pagination} />
		{/if}
	{/await}
</div>
