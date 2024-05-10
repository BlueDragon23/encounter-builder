<script lang="ts">
	export let label: string;
	export let name: string;
	export let min: number | undefined = undefined;
	export let max: number | undefined = undefined;
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
		{...$$restProps}
	/>
</div>
