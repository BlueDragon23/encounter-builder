<script lang="ts">
	import Multiselect from '$lib/forms/Multiselect.svelte';
	import NumberInput from '$lib/forms/NumberInput.svelte';
	import Select from '$lib/forms/Select.svelte';
	import TextInput from '$lib/forms/TextInput.svelte';
	import { Accordion, AccordionItem } from '@skeletonlabs/skeleton';
	import type { ExistingTemplateCreature } from './types';
	import { damageTypeOptions } from '$lib/forms/utils';
	import Textarea from '$lib/forms/Textarea.svelte';

	const sizeOptions = [
		{ value: 'TINY', label: 'Tiny' },
		{ value: 'SMALL', label: 'Small' },
		{ value: 'MEDIUM', label: 'Medium' },
		{ value: 'LARGE', label: 'Large' },
		{ value: 'HUGE', label: 'Huge' },
		{ value: 'GARGANTUAN', label: 'Gargantuan' }
	];
	const typeOptions = [
		{ value: 'aberration', label: 'Aberration' },
		{ value: 'beast', label: 'Beast' },
		{ value: 'celestial', label: 'Celestial' },
		{ value: 'construct', label: 'Construct' },
		{ value: 'dragon', label: 'Dragon' },
		{ value: 'elemental', label: 'Elemental' },
		{ value: 'fey', label: 'Fey' },
		{ value: 'fiend', label: 'Fiend' },
		{ value: 'giant', label: 'Giant' },
		{ value: 'humanoid', label: 'Humanoid' },
		{ value: 'monstrosity', label: 'Monstrosity' },
		{ value: 'ooze', label: 'Ooze' },
		{ value: 'plant', label: 'Plant' },
		{ value: 'undead', label: 'Undead' }
	];
	const alignmentOptions = [
		{ value: 'lawful good', label: 'Lawful Good' },
		{ value: 'neutral good', label: 'Neutral Good' },
		{ value: 'chaotic good', label: 'Chaotic Good' },
		{ value: 'lawful neutral', label: 'Lawful Neutral' },
		{ value: 'neutral', label: 'Neutral' },
		{ value: 'chaotic neutral', label: 'Chaotic Neutral' },
		{ value: 'lawful evil', label: 'Lawful Evil' },
		{ value: 'neutral evil', label: 'Neutral Evil' },
		{ value: 'chaotic evil', label: 'Chaotic Evil' }
	];
	const conditionOptions = [
		{ label: 'blinded', value: 'blinded' },
		{ label: 'charmed', value: 'charmed' },
		{ label: 'deafened', value: 'deafened' },
		{ label: 'exhaustion', value: 'exhaustion' },
		{ label: 'frightened', value: 'frightened' },
		{ label: 'grappled', value: 'grappled' },
		{ label: 'incapacitated', value: 'incapacitated' },
		{ label: 'invisible', value: 'invisible' },
		{ label: 'paralyzed', value: 'paralyzed' },
		{ label: 'petrified', value: 'petrified' },
		{ label: 'poisoned', value: 'poisoned' },
		{ label: 'prone', value: 'prone' },
		{ label: 'restrained', value: 'restrained' },
		{ label: 'stunned', value: 'stunned' },
		{ label: 'unconscious', value: 'unconscious' }
	];

	export let creature: ExistingTemplateCreature | undefined = undefined;
</script>

<TextInput name="name" label="Name" value={creature?.name} />
<Textarea label="Description" name="description" value={creature?.description ?? ''} />
<div class="flex flex-wrap gap-4">
	<NumberInput
		name="ability_strength"
		label="STR"
		min={1}
		value={creature?.abilityScores?.strength ?? 10}
	/>
	<NumberInput
		name="ability_dexterity"
		label="DEX"
		min={1}
		value={creature?.abilityScores?.dexterity ?? 10}
	/>
	<NumberInput
		name="ability_constitution"
		label="CON"
		min={1}
		value={creature?.abilityScores?.constitution ?? 10}
	/>
	<NumberInput
		name="ability_wisdom"
		label="WIS"
		min={1}
		value={creature?.abilityScores?.wisdom ?? 10}
	/>
	<NumberInput
		name="ability_intelligence"
		label="INT"
		min={1}
		value={creature?.abilityScores?.intelligence ?? 10}
	/>
	<NumberInput
		name="ability_charisma"
		label="CHA"
		min={1}
		value={creature?.abilityScores?.charisma ?? 10}
	/>
</div>
<div class="flex flex-wrap gap-4 my-4">
	<Select
		name="creatureSize"
		label="Size"
		value={creature?.creatureSize ?? 'MEDIUM'}
		options={sizeOptions}
	/>
	<Select
		name="type"
		label="Creature Type"
		value={creature?.type ?? 'humanoid'}
		options={typeOptions}
	/>
	<Select
		name="alignment"
		label="Alignment"
		value={creature?.alignment ?? 'neutral'}
		options={alignmentOptions}
	/>
	<NumberInput name="armorClass" label="Armor Class" min={0} value={creature?.armorClass ?? 10} />
	<NumberInput
		name="hitpoints"
		label="Average Hitpoints"
		min={0}
		placeholder="100"
		value={creature?.hitpoints}
	/>
	<NumberInput
		name="proficiencyBonus"
		label="Proficiency Bonus"
		min={0}
		placeholder="2"
		value={creature?.proficiencyBonus}
	/>
</div>
<div class="flex flex-wrap gap-4 my-4">
	<div class="w-full md:w-1/2">
		<!-- Speed - default to just walking, expand for more -->
		<div>
			<NumberInput
				name="speed_walk"
				label="Walk Speed"
				value={creature?.speed?.walk ?? 30}
				min={0}
			/>
		</div>
		<Accordion>
			<AccordionItem>
				<svelte:fragment slot="summary">Other Speeds</svelte:fragment>
				<svelte:fragment slot="content">
					<div class="flex justify-between gap-4">
						<NumberInput name="speed_swim" label="Swim" min={0} value={creature?.speed?.swim} />
						<NumberInput name="speed_fly" label="Fly" min={0} value={creature?.speed?.fly} />
						<NumberInput name="speed_climb" label="Climb" min={0} value={creature?.speed?.climb} />
						<NumberInput
							name="speed_burrow"
							label="Burrow"
							min={0}
							value={creature?.speed?.burrow}
						/>
					</div>
				</svelte:fragment>
			</AccordionItem>
		</Accordion>
	</div>
	<div class="md:w-1/3">
		<Multiselect name="resistances" label="Resistances" options={damageTypeOptions} />
	</div>
	<div class="md:w-1/3">
		<Multiselect name="immunities" label="Immunities" options={damageTypeOptions} />
	</div>
</div>
<div class="flex flex-wrap gap-4 my-4 justify-stretch">
	<TextInput name="senses" label="Senses" placeholder="Darkvision (30ft), ..." />
	<TextInput name="languages" label="Languages" placeholder="Common, ..." />
	<div class="w-full md:w-1/2">
		<Multiselect
			name="condition_immunities"
			label="Condition Immunities"
			options={conditionOptions}
		/>
	</div>
</div>
