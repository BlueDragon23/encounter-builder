<script lang="ts">
	import MonsterDetailsCard from '$lib/monsters/MonsterDetailsCard.svelte';
	import type { MonsterDetails } from '$lib/types';

	export let data: Promise<MonsterDetails>;
</script>

<div class="p-4">
	{#await data then loaded}
		<MonsterDetailsCard {...loaded} details={loaded} />
	{/await}
</div>
