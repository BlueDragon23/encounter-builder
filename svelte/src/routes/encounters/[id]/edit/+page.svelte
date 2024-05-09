<script lang="ts">
	import type { EditEncounterPageData } from './+page';
	export let data: EditEncounterPageData;
</script>

<!-- Editable title -->
<form method="post">
	<div class="flex justify-between">
		<h1 class="h1 mb-4">Edit Encounter</h1>
		<button type="submit" class="btn variant-filled-primary">Save</button>
	</div>
	<label for="name" class="label">Encounter Name</label>
	<input id="name" name="name" type="text" class="input text-token" value={data.encounter.name} />
	<!-- Editable description - collapsible? -->
	<label for="description" class="label">Encounter Description</label>
	<textarea
		name="description"
		id="description"
		value={data.encounter.description}
		rows="4"
		class="textarea text-token"
	/>
	<!-- Monster listing with filters -->
	{#await data.encounterMonsters then monsters}
		{#if data.encounter.creatures}
			<h2 class="h2">Monsters</h2>
			<ul>
				{#each data.encounter.creatures as creature, index}
					<li class="flex items-center gap-4 justify-around">
						<p>{monsters[creature.templateCreature.id].name}</p>
						<input
							type="text"
							class="hidden"
							name={`creatures[${index}][id]`}
							value={creature.id}
						/>
						<input
							type="text"
							class="hidden"
							name={`creatures[${index}][templateCreatureId]`}
							value={creature.templateCreature.id}
						/>
						<input
							type="number"
							name={`creatures[${index}][count]`}
							class="input text-token w-1/2"
							value={creature.count}
						/>
						<button class="btn-icon btn-sm variant-filled-surface">X</button>
					</li>
				{/each}
			</ul>
		{/if}
	{/await}
</form>
