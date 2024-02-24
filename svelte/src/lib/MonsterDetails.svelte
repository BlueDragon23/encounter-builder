<!-- Similar to existing Monster layouts. They're good -->
<script lang="ts">
	import type { MonsterDetails } from './types';

	export let id: string;
	export let name: string;
	export let description: string;
	export let details: MonsterDetails;
</script>

<div {id}>
	<!-- Name, size, alignment -->
	<h1>{name}</h1>
	<p>{details.size}, {details.alignment}</p>

	<!-- AC, HP, Speed, Initiative -->
	<ul>
		<li>Armor Class: {details.ac}</li>
		<li>Hit Points: {details.hp}</li>
		<li>Speed: {details.speed}</li>
		<li>Initiative: {details.initiative}</li>
	</ul>

	<!-- Ability scores -->
	<ul>
		<li>STR {details.abilityScores.strength}</li>
		<li>DEX {details.abilityScores.dexterity}</li>
		<li>CON {details.abilityScores.constitution}</li>
		<li>INT {details.abilityScores.intelligence}</li>
		<li>WIS {details.abilityScores.wisdom}</li>
		<li>CHA {details.abilityScores.charisma}</li>
	</ul>

	<!-- Saving throws, proficiencies -->
	<ul>
		<li>Saving Throws: {details.savingThrows.join(', ')}</li>
		<li>Skills: {details.skills.join(', ')}</li>
		<li>Senses: {details.senses}</li>
		<li>Languages: {details.languages}</li>
		<li>Challenge Rating: {details.challengeRating}</li>
		<li>Proficiency Bonus: {details.proficiencyBonus}</li>
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
	<p>{description}</p>
</div>
