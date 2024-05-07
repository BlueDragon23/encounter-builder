<script>
	import { toggleTestData, useTestData } from '$lib';
	import '../app.postcss';
	import { AppShell, AppBar, TabGroup, TabAnchor, SlideToggle } from '@skeletonlabs/skeleton';
</script>

<AppShell>
	<svelte:fragment slot="pageHeader">
		<AppBar>
			<svelte:fragment slot="lead">
				<div class="text-token">
					<TabGroup>
						<TabAnchor href="/" name="Home">Home</TabAnchor>
						<TabAnchor href="/encounters" name="Encounters">Encounters</TabAnchor>
						<TabAnchor href="/monsters" name="Monsters">Monsters</TabAnchor>
						<TabAnchor href="/settings" name="Settings">Settings</TabAnchor>
						<!-- ... -->
					</TabGroup>
				</div>
			</svelte:fragment>
			<svelte:fragment slot="trail">
				<div class="flex-item">
					<SlideToggle name="testMode" checked={useTestData()} on:change={toggleTestData}
						>Test Mode</SlideToggle
					>
				</div>
			</svelte:fragment>
		</AppBar>
	</svelte:fragment>
	<!-- ... -->
	<div class="p-4"><slot /></div>
</AppShell>
