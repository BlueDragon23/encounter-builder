<script lang="ts">
	import NumberInput from '$lib/forms/NumberInput.svelte';
	import Select from '$lib/forms/Select.svelte';
	import { damageTypeOptions, type SelectOptions } from '$lib/forms/utils';
	import type { components } from '$lib/generated/client';
	import { writable } from 'svelte/store';

	const diceOptions: SelectOptions<components['schemas']['Dice']['die']>[] = [
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
	export let damages: components['schemas']['Damage'][] | undefined;
	$: editedDamages = writable(damages != undefined ? [...damages] : []);

	function updateDamage(damage: components['schemas']['Damage'], index: number) {
		editedDamages.update((ds) => {
			ds[index] = damage;
			console.log(ds);
			return ds;
		});
	}
</script>

{#each $editedDamages as damage, index (damage.id)}
	<div class="flex gap-4">
		<NumberInput
			name="count"
			label="Count"
			min={1}
			value={damage?.count ?? 1}
			bindValue={damage.count}
			on:update={(event) => {
				updateDamage({ ...damage, count: event.detail }, index);
			}}
		/>
		<Select
			value={damage?.die ?? 'D6'}
			label="Die"
			name="die"
			options={diceOptions}
			on:update={(event) => {
				updateDamage({ ...damage, die: event.detail }, index);
			}}
		/>
		<Select
			value={damage?.damageType ?? 'SLASHING'}
			label="Type"
			name="damageType"
			options={damageTypeOptions}
			on:update={(event) => {
				updateDamage({ ...damage, damageType: event.detail }, index);
			}}
		/>
		{#if damage && damage.modifier}
			<p>{modifierFormatter.format(damage.modifier)}</p>
		{/if}
		<button
			type="button"
			class="btn-icon btn-sm variant-ringed-error"
			on:click={() =>
				editedDamages.update((ds) => {
					ds.splice(index, 1);
					return ds;
				})}
		>
			X
		</button>
	</div>
{/each}
<button
	class="btn my-4 variant-ringed-secondary"
	type="button"
	on:click={() =>
		editedDamages.update((ds) => {
			// TODO: differentiate between temporary IDs and permanent IDs. Somehow
			let nextId;
			if (ds.length > 0) {
				nextId = (ds[ds.length - 1].id ?? 0) + 1;
			} else {
				nextId = 0;
			}
			ds.push({ id: nextId, count: 1, die: 'D6', damageType: 'SLASHING' });
			return ds;
		})}>Add damage</button
>
