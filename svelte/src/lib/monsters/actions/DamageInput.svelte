<script lang="ts">
	import NumberInput from '$lib/forms/NumberInput.svelte';
	import Select from '$lib/forms/Select.svelte';
	import { damageTypeOptions } from '$lib/forms/utils';
	import type { components } from '$lib/generated/client';

	const diceOptions = [
		{ label: 'D3', value: 'D3' },
		{ label: 'D4', value: 'D4' },
		{ label: 'D6', value: 'D6' },
		{ label: 'D8', value: 'D8' },
		{ label: 'D10', value: 'D10' },
		{ label: 'D12', value: 'D12' },
		{ label: 'D20', value: 'D20' },
		{ label: 'D100', value: 'D100' }
	];
	const modifierFormatter = new Intl.NumberFormat(undefined, { signDisplay: 'always' });
	export let damage: components['schemas']['Damage'] | undefined = undefined;
</script>

<div class="flex gap-4">
	<NumberInput name="count" label="Count" value={damage?.count ?? 1} />
	<Select value={damage?.die ?? 'D6'} label="Die" name="die" options={diceOptions} />
	<Select
		value={damage?.damageType?.toLowerCase() ?? 'slashing'}
		label="Type"
		name="damageType"
		options={damageTypeOptions}
	/>
	{#if damage && damage.modifier}
		<p>{modifierFormatter.format(damage.modifier)}</p>
	{/if}
</div>
