<script lang="ts">
	import Multiselect from '$lib/forms/Multiselect.svelte';
	import NumberInput from '$lib/forms/NumberInput.svelte';
	import TextInput from '$lib/forms/TextInput.svelte';
	import { Accordion, AccordionItem } from '@skeletonlabs/skeleton';

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

<form action="POST" class="my-4">
	<TextInput name="name" label="Name" />
	<label class="label mt-4" for="description">Description</label>
	<textarea class="textarea text-token" id="description" />
	<div class="flex flex-wrap gap-4 my-4">
		<div>
			<label class="label" for="creatureType">Creature Type</label>
			<!-- TODO: overridable select, to have built in options or a plain text field -->
			<select class="select text-token" name="type" id="creatureType" value="humanoid">
				<option value="aberration">Aberration</option>
				<option value="beast">Beast</option>
				<option value="celestial">Celestial</option>
				<option value="construct">Construct</option>
				<option value="dragon">Dragon</option>
				<option value="elemental">Elemental</option>
				<option value="fey">Fey</option>
				<option value="fiend">Fiend</option>
				<option value="giant">Giant</option>
				<option value="humanoid">Humanoid</option>
				<option value="monstrosity">Monstrosity</option>
				<option value="ooze">Ooze</option>
				<option value="plant">Plant</option>
				<option value="undead">Undead</option>
			</select>
		</div>
		<div>
			<label for="alignment" class="label">Alignment</label>
			<select name="alignment" id="alignment" class="select text-token" value="neutral_neutral">
				<option value="lawful_good">Lawful Good</option>
				<option value="neutral_good">Neutral Good</option>
				<option value="chaotic_good">Chaotic Good</option>
				<option value="lawful_neutral">Lawful Neutral</option>
				<option value="neutral_neutral">Neutral</option>
				<option value="chaotic_neutral">Chaotic Neutral</option>
				<option value="lawful_evil">Evil</option>
				<option value="neutral_evil">Neutral Evil</option>
				<option value="chaotic_evil">Chaotic Evil</option>
			</select>
		</div>
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
