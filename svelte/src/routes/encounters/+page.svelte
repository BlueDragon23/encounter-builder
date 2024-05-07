<script lang="ts">
	import EncounterCard from '$lib/EncounterCard.svelte';
	import type { EncountersPageData } from './+page';

	export let data: EncountersPageData;
</script>

<div class="flex justify-between">
	<h1 class="h1">Encounters</h1>
	<a href="/create-encounter" class="btn variant-filled-primary">Create Encounter</a>
</div>
<p>This is a list of encounters</p>

<div class="flex flex-col items-center mt-4">
	<ul class="list md:container md:mx-auto">
		{#await data.encounters then encounters}
			{#if encounters.content}
				{#each encounters.content as encounter}
					<li><EncounterCard {encounter} /></li>
				{/each}
			{/if}
		{/await}
	</ul>
</div>
