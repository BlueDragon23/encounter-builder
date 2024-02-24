export interface Encounter {
	id: string;
	name: string;
	description: string;
	enemies: any[];
}

export interface Monster {
	id: string;
	name: string;
	description: string;
}

export type MonsterDetails = Monster & {
	size: string;
	alignment: string;
	ac: string;
	hp: string;
	speed: string;
	initiative: number;
	abilityScores: {
		strength: number;
		dexterity: number;
		constitution: number;
		intelligence: number;
		wisdom: number;
		charisma: number;
	};
	savingThrows: string[]; // which throws are they proficient in
	skills: string[];
	senses: string;
	languages: string;
	challengeRating: number;
	proficiencyBonus: number;
	specialAbilities?: [
		{
			name: string;
			description: string;
		}
	];
	// TODO: more details
	attacks?: [
		{
			name: string;
			description: string;
		}
	];
	legendaryActions?: [
		{
			name: string;
			description: string;
		}
	];
};
