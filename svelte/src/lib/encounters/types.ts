import type { components } from '$lib/generated/client';
import type { RequiredFields } from '$lib/types';

export type ExistingEncounterCreatureRef = Omit<
	RequiredFields<components['schemas']['EncounterCreatureRef'], 'id' | 'count'>,
	'templateCreature'
> & {
	templateCreature: RequiredFields<
		components['schemas']['AggregateReferenceTemplateCreatureLong'],
		'id'
	>;
};

// For an encounter to be created, it must have an ID and a name
export type ExistingEncounter = Omit<
	RequiredFields<components['schemas']['Encounter'], 'id' | 'name'>,
	'creatures'
> & { creatures?: ExistingEncounterCreatureRef[] };

export type ExistingEncounterSummary = RequiredFields<
	components['schemas']['EncounterSummary'],
	'id' | 'name'
>;
export type PageExistingEncounterSummary = components['schemas']['PageEncounterSummary'] & {
	content?: ExistingEncounterSummary[];
};
