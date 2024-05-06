import type { components } from './generated/client';

const LOREM_IPSUM = `
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent feugiat nibh quam, vitae elementum nunc placerat facilisis. Proin malesuada, velit vel varius pulvinar, lectus massa auctor diam, ultrices tincidunt massa lorem et risus. Vestibulum ac enim in nisl maximus imperdiet. Suspendisse in tortor congue, interdum turpis sed, scelerisque orci. Cras cursus sapien orci, vitae commodo nunc imperdiet vitae. Phasellus et laoreet quam. Nam lectus ex, tristique at est eget, auctor aliquet elit. Nunc ante sapien, dapibus id odio sed, bibendum suscipit nibh. Cras tristique nibh convallis, pulvinar lorem eget, consequat dui. In in felis volutpat augue sodales venenatis. Donec eget quam vel arcu porttitor luctus.
Suspendisse vel porta mauris. Nunc suscipit in lectus et eleifend. Praesent ut mauris sed velit rutrum malesuada sed a turpis. Curabitur suscipit dolor non metus condimentum, at molestie libero tempor. Curabitur nec tellus magna. Phasellus viverra, mauris ullamcorper volutpat maximus, dui nisl consequat felis, vitae interdum elit turpis ac dolor. Sed et libero elit. Aliquam auctor iaculis eleifend.
Nam tempor non ante vel fermentum. Morbi ac convallis velit. Nulla tempus mauris tempus nisl lobortis pharetra. Etiam mollis auctor urna in tristique. Donec venenatis vulputate velit, vitae faucibus nisl laoreet a. Vivamus libero ex, aliquam nec augue quis, efficitur scelerisque elit. Nullam condimentum ante nec est rhoncus, id vehicula justo consequat. Sed finibus sem fringilla, tincidunt mauris et, ornare orci. Cras venenatis ultrices orci, lacinia vulputate metus eleifend vel. Nam porta arcu lacus, id varius nulla tristique ut. Nullam magna est, fermentum vitae semper quis, euismod venenatis diam. Mauris id justo elit. 
`.split(' ');

function getEncounter(): components['schemas']['Encounter'] {
	let arr = new Uint8Array(8);
	crypto.getRandomValues(arr);

	return {
		id: arr[0],
		name: LOREM_IPSUM[arr[1]] + ' ' + LOREM_IPSUM[arr[2]],
		description: [...arr.slice(3)].map((x) => LOREM_IPSUM[x]).join(' '),
		creatures: []
	};
}

function getMonster(): components['schemas']['TemplateCreature'] {
	let arr = new Uint8Array(8);
	crypto.getRandomValues(arr);

	return {
		id: 0,
		name: LOREM_IPSUM[arr[1]] + ' ' + LOREM_IPSUM[arr[2]],
		description: [...arr.slice(3)].map((x) => LOREM_IPSUM[x]).join(' '),
		creatureSize: 'MEDIUM',
		alignment: 'Neutral',
		armorClass: 10,
		hitpoints: 5,
		speed: {
			walk: 30
		},
		abilityScores: {
			strength: 10,
			dexterity: 10,
			constitution: 10,
			intelligence: 10,
			wisdom: 10,
			charisma: 10
		},
		savingThrows: [],
		challengeRating: 0,
		proficiencyBonus: 0
	};
}

export interface GenerateTestData {
	getEncounter(): components['schemas']['Encounter'];
	getMonster(): components['schemas']['TemplateCreature'];
}

export const TestDataGenerator: GenerateTestData = {
	getEncounter,
	getMonster
};
