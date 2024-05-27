<script lang="ts">
	import TextInput from '$lib/forms/TextInput.svelte';
	import Textarea from '$lib/forms/Textarea.svelte';
	import type { components } from '$lib/generated/client';
	import { writable } from 'svelte/store';
	import DamageInput from './DamageInput.svelte';

	export let actions: components['schemas']['Attack'][] = [];
	$: editedActions = writable([...actions]);
</script>

<!-- List of existing actions -->
<ul>
	{#each $editedActions as action}
		<li>
			{action.attackType}
			<TextInput name="name" label="Name" value={action.name} />
			<DamageInput damages={action.damage} />
			<Textarea name="description" label="Description" value={action.description} />
		</li>
		<hr />
	{/each}
</ul>
<button
	class="btn variant-filled-secondary"
	type="button"
	on:click={() =>
		editedActions.update((actions) => {
			actions.push({ attackType: 'ACTION' });
			return actions;
		})}
>
	Add action
</button>
