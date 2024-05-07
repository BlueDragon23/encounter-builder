<script lang="ts">
	import type { EncounterPageData } from './+page';

	export let data: EncounterPageData;
	const encounter = data.encounter;
</script>

<h1 class="h1">{encounter.name}</h1>
<p class="my-4">{encounter.description}</p>
{#await data.encounterMonsters then monsters}
	{#if encounter.creatures}
		<ul>
			{#each encounter.creatures as creature}
				<li>{monsters[creature.id ?? 0].name} - {creature.count}</li>
			{/each}
		</ul>
	{/if}
{/await}
