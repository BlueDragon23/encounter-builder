<script lang="ts">
	import Multiselect from '$lib/forms/Multiselect.svelte';
	import NumberInput from '$lib/forms/NumberInput.svelte';
	import Select from '$lib/forms/Select.svelte';
	import TextInput from '$lib/forms/TextInput.svelte';
	import { size } from '@floating-ui/dom';
	import { Accordion, AccordionItem } from '@skeletonlabs/skeleton';

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
	const damageTypeOptions = [
		{ label: 'bludgeoning', value: 'bludgeoning', keywords: 'physical' },
		{ label: 'piercing', value: 'piercing', keywords: 'physical' },
		{ label: 'slashing', value: 'slashing', keywords: 'physical' },
		{ label: 'lightning', value: 'lightning' },
		{ label: 'thunder', value: 'thunder' },
		{ label: 'poison', value: 'poison' },
		{ label: 'cold', value: 'cold' },
		{ label: 'radiant', value: 'radiant' },
		{ label: 'fire', value: 'fire' },
		{ label: 'necrotic', value: 'necrotic' },
		{ label: 'acid', value: 'acid' },
		{ label: 'psychic', value: 'psychic' },
		{ label: 'force', value: 'force' }
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
</script>

<h1 class="h1">Create Monster</h1>

<form method="post" class="my-4">
	<TextInput name="name" label="Name" />
	<label class="label mt-4" for="description">Description</label>
	<textarea class="textarea text-token" id="description" />
	<div class="flex flex-wrap gap-4">
		<NumberInput name="ability_strength" label="STR" min={1} value="10" />
		<NumberInput name="ability_dexterity" label="DEX" min={1} value="10" />
		<NumberInput name="ability_constitution" label="CON" min={1} value="10" />
		<NumberInput name="ability_wisdom" label="WIS" min={1} value="10" />
		<NumberInput name="ability_intelligence" label="INT" min={1} value="10" />
		<NumberInput name="ability_charisma" label="CHA" min={1} value="10" />
	</div>
	<div class="flex flex-wrap gap-4 my-4">
		<Select name="creatureSize" label="Size" value="MEDIUM" options={sizeOptions} />
		<Select name="type" label="Creature Type" value="humanoid" options={typeOptions} />
		<Select name="alignment" label="Alignment" value="neutral_neutral" options={alignmentOptions} />
		<NumberInput name="armorClass" label="Armor Class" min={0} value="10" />
		<NumberInput name="hitpoints" label="Average Hitpoints" min={0} placeholder="100" />
		<NumberInput name="proficiencyBonus" label="Proficiency Bonus" min={0} placeholder="2" />
	</div>
	<div class="flex flex-wrap gap-4 my-4">
		<div class="w-full md:w-1/2">
			<!-- Speed - default to just walking, expand for more -->
			<div>
				<NumberInput name="speed_walk" label="Walk Speed" value="30" min={0} />
			</div>
			<Accordion>
				<AccordionItem>
					<svelte:fragment slot="summary">Other Speeds</svelte:fragment>
					<svelte:fragment slot="content">
						<div class="flex justify-between gap-4">
							<NumberInput name="speed_swim" label="Swim" min={0} />
							<NumberInput name="speed_fly" label="Fly" min={0} />
							<NumberInput name="speed_climb" label="Climb" min={0} />
							<NumberInput name="speed_burrow" label="Burrow" min={0} />
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
	<button type="submit" class="btn variant-filled-primary my-4">Create</button>
</form>
