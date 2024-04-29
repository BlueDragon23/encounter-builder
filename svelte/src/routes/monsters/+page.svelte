<script lang="ts">
	import MonsterCard from '$lib/monsters/MonsterCard.svelte';
	import type { MonstersPageData } from './+page';

	export let data: MonstersPageData;
</script>

<div class="p-4">
	<div class="flex justify-between">
		<h1 class="h1">Monsters</h1>
		<a href="/create-monster" class="btn variant-filled-primary">Create Monster</a>
	</div>
	<p>This is a list of monsters</p>

	<div class="flex flex-col items-center mt-4">
		<ul class="list md:container md:mx-auto">
			{#await data.monsters then monsters}
				{#each monsters as monster}
					<li><MonsterCard {...monster} /></li>
				{/each}
			{/await}
		</ul>
	</div>
</div>
