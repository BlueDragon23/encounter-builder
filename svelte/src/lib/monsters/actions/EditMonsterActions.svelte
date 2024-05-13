<script lang="ts">
	import TextInput from '$lib/forms/TextInput.svelte';
	import Textarea from '$lib/forms/Textarea.svelte';
	import type { components } from '$lib/generated/client';
	import DamageInput from './DamageInput.svelte';

	export let actions: components['schemas']['Attack'][] = [];
</script>

<!-- List of existing actions -->
<ul>
	{#each actions as action}
		<li>
			{action.attackType}
			<TextInput name="name" label="Name" value={action.name} />
			{#if action.damage}
				{#each action.damage as damage}
					<DamageInput {damage} />
				{/each}
			{/if}
			<Textarea name="description" label="Description" value={action.description} />
		</li>
		<hr />
	{/each}
</ul>
