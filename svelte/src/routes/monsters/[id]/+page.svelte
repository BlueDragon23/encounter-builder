<script>
	import MonsterDetails from '$lib/MonsterDetails.svelte';

	export let data;
</script>

<div class="p-4">
	<h1 class="h1">{data.name}</h1>
	<MonsterDetails {...data} details={data} />
</div>
