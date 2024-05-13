<script lang="ts">
	import MonsterDetailsCard from '$lib/monsters/MonsterDetailsCard.svelte';
	import type { ExistingTemplateCreature } from '$lib/monsters/types';

	export let data: Promise<ExistingTemplateCreature>;
</script>

{#await data then loaded}
	<MonsterDetailsCard details={loaded} />
{/await}
