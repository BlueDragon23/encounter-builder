<script lang="ts">
	import type { components } from './generated/client';

	export let encounter: components['schemas']['EncounterSummary'];
</script>

<div class="card card-hover p-4 md:container md:mx-auto text-token">
	<div class="content text-token px-4">
		<h3 class="h3">{encounter.name}</h3>
		<div>{encounter.description}</div>
	</div>
	<div class="card-footer flex justify-end">
		<div class="btn-group gap-4 text-token">
			<a href="/encounters/{encounter.id}" class="btn variant-filled-secondary">Edit</a>
			<a href="/encounters/{encounter.id}" class="btn variant-filled-primary">Run</a>
		</div>
	</div>
</div>
