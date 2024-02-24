<script>
	import EncounterCard from '$lib/EncounterCard.svelte';

	export let data;
</script>

<div class="p-4">
	<div class="flex justify-between">
		<h1 class="h1">Encounters</h1>
		<a href="/create-encounter" class="btn variant-filled-primary">Create Encounter</a>
	</div>
	<p>This is a list of encounters</p>

	<div class="flex flex-col items-center mt-4">
		<ul class="list md:container md:mx-auto">
			{#each data.encounters as encounter}
				<li><EncounterCard {...encounter} /></li>
			{/each}
		</ul>
	</div>
</div>
