<!-- Similar to existing Monster layouts. They're good -->
<script lang="ts">
	import type { components } from '$lib/generated/client';
	import MonsterStats from './MonsterStats.svelte';

	const formatter = new Intl.NumberFormat(undefined, {
		signDisplay: 'always'
	});
	function abilityScoreModifier(score?: number): string {
		if (score) {
			const mod = Math.floor((score - 10) / 2);
			return `(${formatter.format(mod)})`;
		}
		return '';
	}

	export let details: components['schemas']['TemplateCreature'];
</script>

<div id={details.id?.toString()}>
	<!-- Name, size, alignment -->
	<h1 class="h1">{details.name}</h1>
	<i>{details.creatureSize}, {details.alignment}</i>
	<div class="flex justify-between">
		<p>Challenge Rating: <b>{details.challengeRating}</b></p>
		<p>Proficiency Bonus: <b>{details.proficiencyBonus}</b></p>
	</div>

	<!-- AC, HP, Speed, Initiative -->
	<MonsterStats
		armorClass={details.armorClass}
		hitpoints={details.hitpoints}
		speed={details.speed}
	/>

	<hr class="my-4" />
	<!-- Ability scores -->
	{#if details.abilityScores}
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
						<td class="text-center"
							>{details.abilityScores.strength}
							{abilityScoreModifier(details.abilityScores.strength)}</td
						>
						<td class="text-center"
							>{details.abilityScores.dexterity}
							{abilityScoreModifier(details.abilityScores.dexterity)}</td
						>
						<td class="text-center"
							>{details.abilityScores.constitution}
							{abilityScoreModifier(details.abilityScores.constitution)}</td
						>
						<td class="text-center"
							>{details.abilityScores.intelligence}
							{abilityScoreModifier(details.abilityScores.intelligence)}</td
						>
						<td class="text-center"
							>{details.abilityScores.wisdom}
							{abilityScoreModifier(details.abilityScores.wisdom)}</td
						>
						<td class="text-center"
							>{details.abilityScores.charisma}
							{abilityScoreModifier(details.abilityScores.charisma)}</td
						>
					</tr>
				</tbody>
			</table>
		</div>
	{/if}

	<!-- Saving throws, proficiencies -->
	<ul>
		{#if details.savingThrows !== undefined && details.savingThrows.length > 0}
			<li>Saving Throw Proficiencies: {details.savingThrows.join(', ')}</li>
		{/if}
	</ul>

	<!-- Special abilities (including spellcasting) -->
	{#if details.specialAbilities !== undefined}
		<h2 class="h2">Special Abilities</h2>
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
		<h2 class="h2">Actions</h2>
		<ul>
			{#each details.attacks as attack}
				{#if attack.attackType == 'ACTION'}
					<li>
						<b>{attack.name}. </b>
						<p>{attack.description}</p>
					</li>
				{/if}
			{/each}
		</ul>
	{/if}

	<!-- Legendary Actions -->
	{#if details.attacks !== undefined}
		{#if details.attacks.some((a) => a.attackType == 'LEGENDARY')}
			<h2 class="h2">Legendary Actions</h2>
			<ul>
				{#each details.attacks.filter((a) => a.attackType == 'LEGENDARY') as action}
					<li>
						<b>{action.name}. </b>
						<p>{action.description}</p>
					</li>
				{/each}
			</ul>
		{/if}
	{/if}

	<!-- Description -->
	{#if details.description}
		<p>{details.description}</p>
	{/if}
</div>
