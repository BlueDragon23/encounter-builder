import type { components } from '$lib/generated/client';
import type { RequiredFields } from '$lib/types';

export type ExistingTemplateCreature = RequiredFields<
	components['schemas']['TemplateCreature'],
	'id' | 'name'
>;

type ExistingTemplateCreatureSummary = RequiredFields<
	components['schemas']['TemplateCreatureSummary'],
	'id' | 'name'
>;
export type PageExistingTemplateCreatureSummary =
	components['schemas']['PageTemplateCreatureSummary'] & {
		content?: ExistingTemplateCreatureSummary[];
	};
