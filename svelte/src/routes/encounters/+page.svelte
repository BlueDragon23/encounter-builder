<script>
	import EncounterCard from '$lib/EncounterCard.svelte';

	// TODO: load data
</script>

<div>
	<div class="p-4">
		<h1 class="h1">Encounters</h1>
		<p>This is a list of encounters</p>
	</div>

	<div class="flex flex-col items-center">
		<ul class="list md:container md:mx-auto">
			<li><EncounterCard name="Test" description="This is an example encounter" id="1" /></li>
		</ul>
	</div>
</div>
