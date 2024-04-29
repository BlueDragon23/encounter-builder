<!-- Similar to existing Monster layouts. They're good -->
<script lang="ts">
	import type { MonsterDetails } from '../types';
	import MonsterStats from './MonsterStats.svelte';

	export let id: string;
	export let name: string;
	export let description: string;
	export let details: MonsterDetails;
</script>

<div {id}>
	<!-- Name, size, alignment -->
	<h1 class="h1">{name}</h1>
	<i>{details.creatureSize}, {details.alignment}</i>
	<div class="flex justify-between">
		<p>Challenge Rating: <b>{details.challengeRating}</b></p>
		<p>Proficiency Bonus: <b>{details.proficiencyBonus}</b></p>
	</div>

	<!-- AC, HP, Speed, Initiative -->
	<MonsterStats {...details} />

	<hr class="my-4" />
	<!-- Ability scores -->
	<div class="table-container my-4 text-token">
		<table class="table">
			<thead>
				<tr>
					<th class="text-center">STR</th>
					<th class="text-center">DEX</th>
					<th class="text-center">CON</th>
					<th class="text-center">INT</th>
					<th class="text-center">WIS</th>
					<th class="text-center">CHA</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="text-center">{details.abilityScores.strength}</td>
					<td class="text-center">{details.abilityScores.dexterity}</td>
					<td class="text-center">{details.abilityScores.constitution}</td>
					<td class="text-center">{details.abilityScores.intelligence}</td>
					<td class="text-center">{details.abilityScores.wisdom}</td>
					<td class="text-center">{details.abilityScores.charisma}</td>
				</tr>
			</tbody>
		</table>
	</div>

	<!-- Saving throws, proficiencies -->
	<ul>
		{#if details.savingThrows !== undefined && details.savingThrows.length > 0}
			<li>Saving Throws: {details.savingThrows.join(', ')}</li>
		{/if}
		{#if details.skills !== undefined}
			<li>Skills: {details.skills.join(', ')}</li>
		{/if}
		{#if details.senses !== undefined}
			<li>Senses: {details.senses}</li>
		{/if}
		{#if details.languages !== undefined}
			<li>Languages: {details.languages}</li>
		{/if}
	</ul>

	<!-- Special abilities (including spellcasting) -->
	{#if details.specialAbilities !== undefined}
		<ul>
			{#each details.specialAbilities as specialAbility}
				<li>
					<b>{specialAbility.name}. </b>
					<p>{specialAbility.description}</p>
				</li>
			{/each}
		</ul>
	{/if}

	<!-- Actions -->
	{#if details.attacks !== undefined}
		<ul>
			{#each details.attacks as attack}
				<li>
					<b>{attack.name}. </b>
					<p>{attack.description}</p>
				</li>
			{/each}
		</ul>
	{/if}

	<!-- Legendary Actions -->
	{#if details.legendaryActions !== undefined}
		<ul>
			{#each details.legendaryActions as action}
				<li>
					<b>{action.name}. </b>
					<p>{action.description}</p>
				</li>
			{/each}
		</ul>
	{/if}

	<!-- Description -->
	{#if description}
		<p>{description}</p>
	{/if}
</div>
