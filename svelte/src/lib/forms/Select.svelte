<script lang="ts" generics="T">
	import type { SelectOptions } from './utils';

	import { createEventDispatcher } from 'svelte';

	export let value: T;
	export let name: string;
	export let label: string;
	export let options: SelectOptions<T>[];
	let internalValue = value;

	const dispatch = createEventDispatcher<{ update: T }>();
</script>

<div>
	<label class="label" for={name}>{label}</label>
	<!-- TODO: overridable select, to have built in options or a plain text field -->
	<select
		class="select text-token"
		{name}
		id={name}
		bind:value={internalValue}
		on:input={() => dispatch('update', value)}
	>
		{#each options as option}
			<option value={option.value}>{option.label}</option>
		{/each}
	</select>
</div>
