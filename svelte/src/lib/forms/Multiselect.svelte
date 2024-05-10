<script lang="ts">
	import {
		Autocomplete,
		InputChip,
		popup,
		type AutocompleteOption,
		type PopupSettings
	} from '@skeletonlabs/skeleton';

	let inputValue = '';
	let inputChip: InputChip;
	let selectedValues: string[];
	export let name: string;
	export let label: string;
	export let options: AutocompleteOption<string>[];
	let popupName = `popupAutocomplete${name}`;
	let popupSettings: PopupSettings = {
		event: 'focus-click',
		target: popupName,
		placement: 'bottom'
	};
</script>

<div class="max-w-sm" use:popup={popupSettings}>
	<label for={name}>{label}</label>
	<!-- TODO: work out whether this name binding is working at all -->
	<InputChip
		bind:input={inputValue}
		bind:this={inputChip}
		bind:value={selectedValues}
		regionInput="text-token"
		{label}
		{name}
		placeholder={label}
		id={name}
	/>
	<div class="card max-w-sm w-full max-h-48 overflow-y-auto text-token" data-popup={popupName}>
		<Autocomplete
			bind:input={inputValue}
			{options}
			denylist={selectedValues}
			on:selection={(event) => inputChip.addChip(event.detail.value)}
		/>
	</div>
</div>
