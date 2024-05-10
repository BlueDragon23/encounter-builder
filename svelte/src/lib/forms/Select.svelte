<script lang="ts">
	interface SelectOptions {
		label: string;
		value: string;
	}
	export let value: string;
	export let name: string;
	export let label: string;
	export let options: SelectOptions[];
</script>

<div>
	<label class="label" for={name}>{label}</label>
	<!-- TODO: overridable select, to have built in options or a plain text field -->
	<select class="select text-token" {name} id={name} {value}>
		{#each options as option}
			<option value={option.value}>{option.label}</option>
		{/each}
	</select>
</div>
