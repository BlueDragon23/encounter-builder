<script>
	import { toggleTestData, useTestData } from '$lib';
	import '../app.postcss';
	import { AppShell, AppBar, TabGroup, TabAnchor, SlideToggle } from '@skeletonlabs/skeleton';
</script>

<AppShell>
	<svelte:fragment slot="pageHeader">
		<AppBar>
			<div class="text-token">
				<TabGroup>
					<TabAnchor href="/" name="Home">Home</TabAnchor>
					<TabAnchor href="/encounters" name="Encounters">Encounters</TabAnchor>
					<TabAnchor href="/monsters" name="Monsters">Monsters</TabAnchor>
					<TabAnchor href="/settings" name="Settings">Settings</TabAnchor>
					<!-- ... -->
				</TabGroup>
			</div>
			<div>
				<SlideToggle name="testMode" checked={useTestData()} on:change={toggleTestData}
					>Test Mode</SlideToggle
				>
			</div>
		</AppBar>
	</svelte:fragment>
	<!-- ... -->
	<slot />
</AppShell>
