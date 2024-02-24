<script>
	import '../app.postcss';
	import { AppShell, AppBar, TabGroup, TabAnchor } from '@skeletonlabs/skeleton';
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
		</AppBar>
	</svelte:fragment>
	<!-- ... -->
	<slot />
</AppShell>
