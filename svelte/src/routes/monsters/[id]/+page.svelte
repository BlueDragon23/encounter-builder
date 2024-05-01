<script lang="ts">
	import type { components } from '$lib/generated/client';
	import MonsterDetailsCard from '$lib/monsters/MonsterDetailsCard.svelte';

	export let data: Promise<components['schemas']['TemplateCreature']>;
</script>

<div class="p-4">
	{#await data then loaded}
		<MonsterDetailsCard details={loaded} />
	{/await}
</div>
