<script lang="ts">
	export let label: string;
	export let name: string;
	export let placeholder: string | undefined = undefined;
</script>

<div>
	<label class="label" for={name}>{label}</label>
	<input
		type="text"
		id={name}
		{name}
		class="input text-token max-w-prose"
		{placeholder}
		{...$$restProps}
	/>
</div>
