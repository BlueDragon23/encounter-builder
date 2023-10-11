<script>
	import EncounterCard from '$lib/EncounterCard.svelte';

	export let data;
</script>

<div>
	<div class="p-4">
		<h1 class="h1">Encounters</h1>
		<p>This is a list of encounters</p>
	</div>

	<div class="flex flex-col items-center">
		<ul class="list md:container md:mx-auto">
			{#each data.encounters as encounter}
				<li><EncounterCard {...encounter} /></li>
			{/each}
		</ul>
	</div>
</div>
