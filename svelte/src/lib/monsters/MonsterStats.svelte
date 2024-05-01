<script lang="ts">
	import type { components } from '$lib/generated/client';

	export let armorClass: components['schemas']['TemplateCreature']['armorClass'];
	export let hitpoints: components['schemas']['TemplateCreature']['hitpoints'];
	export let speed: components['schemas']['TemplateCreature']['speed'];
</script>

<div>
	<div class="flex justify-between">
		<p>Armor Class: <b>{armorClass}</b></p>
		<p>Hit Points: <b>{hitpoints}</b></p>
	</div>
	{#if speed !== undefined}
		<div class="table-container my-4 text-token">
			<table class="table">
				<!-- TODO: hide speeds of 0 -->
				<thead>
					<tr>
						<th class="text-center">Walk</th>
						<th class="text-center">Swim</th>
						<th class="text-center">Fly</th>
						<th class="text-center">Climb</th>
						<th class="text-center">Burrow</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="text-center">{speed.walk}ft</td>
						<td class="text-center">{speed.swim}ft</td>
						<td class="text-center">{speed.fly}ft</td>
						<td class="text-center">{speed.climb}ft</td>
						<td class="text-center">{speed.burrow}ft</td>
					</tr>
				</tbody>
			</table>
		</div>
	{/if}
</div>
