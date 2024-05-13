<script lang="ts">
	import EditMonsterAttributes from '$lib/monsters/EditMonsterAttributes.svelte';
	import { Accordion, AccordionItem } from '@skeletonlabs/skeleton';
	import type { EditMonsterPageData } from './+page';
	import EditMonsterActions from '$lib/monsters/actions/EditMonsterActions.svelte';

	export let data: EditMonsterPageData;
</script>

<h1 class="h1 mb-4 ml-4">Edit Monster</h1>
<form action="post">
	<Accordion>
		<AccordionItem open>
			<svelte:fragment slot="summary"><h2 class="h2">Monster Attributes</h2></svelte:fragment>
			<svelte:fragment slot="content">
				<!-- Basic stats, collapsible, open by default. Effectively the create-monster page -->
				<EditMonsterAttributes creature={data.creature} />
			</svelte:fragment>
		</AccordionItem>
		<AccordionItem open>
			<!-- Actions, collapsible, open by default -->
			<svelte:fragment slot="summary"><h2 class="h2">Monster Actions</h2></svelte:fragment>
			<svelte:fragment slot="content">
				<EditMonsterActions actions={data.creature.attacks} />
			</svelte:fragment>
		</AccordionItem>
		<!-- Special abilities, collapsible, closed by default -->
	</Accordion>
	<button type="submit" class="btn variant-filled-primary">Save</button>
</form>
