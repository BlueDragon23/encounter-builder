<script>
	import MonsterCard from '$lib/MonsterCard.svelte';

	export let data;
</script>

<div class="p-4">
	<div class="flex justify-between">
		<h1 class="h1">Monsters</h1>
		<a href="/create-monster" class="btn variant-filled-primary">Create Monster</a>
	</div>
	<p>This is a list of monsters</p>

	<div class="flex flex-col items-center mt-4">
		<ul class="list md:container md:mx-auto">
			{#each data.monsters as monster}
				<li><MonsterCard {...monster} /></li>
			{/each}
		</ul>
	</div>
</div>
