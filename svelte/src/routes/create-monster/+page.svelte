<script lang="ts">
	import Multiselect from '$lib/forms/Multiselect.svelte';
	import NumberInput from '$lib/forms/NumberInput.svelte';
	import Select from '$lib/forms/Select.svelte';
	import TextInput from '$lib/forms/TextInput.svelte';
	import EditMonsterAttributes from '$lib/monsters/EditMonsterAttributes.svelte';
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
	<EditMonsterAttributes />
	<button type="submit" class="btn variant-filled-primary my-4">Create</button>
</form>
