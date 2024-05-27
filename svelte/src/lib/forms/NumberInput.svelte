<script lang="ts">
	import { createEventDispatcher } from 'svelte';

	export let label: string;
	export let name: string;
	export let min: number | undefined = undefined;
	export let max: number | undefined = undefined;
	export let value: number | undefined = undefined;

	const dispatch = createEventDispatcher<{ update: number | undefined }>();
</script>

<div>
	<label for={name}>{label}</label>
	<input
		type="number"
		id={name}
		{name}
		class="input text-token max-w-24"
		{min}
		{max}
		bind:value
		on:input={() => dispatch('update', value)}
		{...$$restProps}
	/>
</div>
