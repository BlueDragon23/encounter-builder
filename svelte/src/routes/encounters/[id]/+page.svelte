<script lang="ts">
	import EncounterCard from '$lib/EncounterCard.svelte';
	import type { components } from '$lib/generated/client';

	export let data: Promise<components['schemas']['Encounter'] | undefined>;
</script>

<div class="p-4">
	{#await data then encounter}
		{#if encounter}
			<h1 class="h1">{encounter.name}</h1>
			<EncounterCard {encounter} />
		{/if}
	{/await}
</div>
