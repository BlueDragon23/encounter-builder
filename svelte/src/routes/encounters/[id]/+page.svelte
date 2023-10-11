<script>
	import EncounterCard from '$lib/EncounterCard.svelte';

	export let data;
</script>

<div class="p-4">
	<h1 class="h1">{data.name}</h1>
	<EncounterCard {...data} />
</div>
