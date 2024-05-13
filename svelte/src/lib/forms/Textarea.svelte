<script lang="ts">
	export let name: string;
	export let label: string;
	export let value: string | undefined = undefined;
</script>

<label for={name} class="label">{label}</label>
<textarea class="textarea text-token lg:w-1/2" rows={3} id={name} {name} {value} {...$$restProps} />
