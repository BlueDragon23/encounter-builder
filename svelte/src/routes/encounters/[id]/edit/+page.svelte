<script lang="ts">
	import type { EditEncounterPageData } from './+page';
	export let data: EditEncounterPageData;
</script>

<!-- Editable title -->
<input type="text" class="input" value={data.encounter.name} />
<!-- Editable description - collapsible? -->
<textarea name="description" id="description" value={data.encounter.description} />
<!-- Monster listing with filters -->
{#await data.encounterMonsters then monsters}
	{#if data.encounter.creatures}
		<ul>
			{#each data.encounter.creatures as creature}
				<li>{monsters[creature.templateCreature.id].name}</li>
			{/each}
		</ul>
	{/if}
{/await}
