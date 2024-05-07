<script lang="ts">
	import { goto } from '$app/navigation';
	import type { EncounterPageData } from './+page';

	export let data: EncounterPageData;
	const encounter = data.encounter;
</script>

<div class="flex justify-between">
	<h1 class="h1">{encounter.name}</h1>
	<button
		class="btn variant-filled-primary"
		type="button"
		on:click={() => goto(`/encounters/${encounter.id}/edit`)}>Edit</button
	>
</div>
<p class="my-4">{encounter.description}</p>
{#await data.encounterMonsters then monsters}
	{#if encounter.creatures}
		<ul>
			{#each encounter.creatures as creature}
				<li>
					<a class="hover:underline" href={`/monsters/${creature.templateCreature.id}`}
						>{monsters[creature.templateCreature.id].name}</a
					>
					x {creature.count}
				</li>
			{/each}
		</ul>
	{/if}
{/await}
