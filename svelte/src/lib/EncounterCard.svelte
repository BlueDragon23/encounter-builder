<script lang="ts">
	export let id: string;
	export let name: string;
	export let description: string;
	export let enemies: any[] = [];
</script>

<div class="card card-hover p-4 md:container md:mx-auto text-token">
	<div class="content light px-4">
		<h3 class="h3">{name}</h3>
		<div>{description}</div>
	</div>
	<div class="card-footer flex justify-end">
		<div class="btn-group gap-4">
			<a href="/encounters/{id}" class="btn variant-filled-secondary">Edit</a>
			<a href="/encounters/{id}" class="btn variant-filled-primary">Run</a>
		</div>
	</div>
</div>
