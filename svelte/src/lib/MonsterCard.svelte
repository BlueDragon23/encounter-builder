<script lang="ts">
	export let id: string;
	export let name: string;
	export let description: string;
</script>

<div class="card card-hover p-4 md:container md:mx-auto text-token">
	<div class="content text-token px-4">
		<h3 class="h3">{name}</h3>
		<div>{description}</div>
	</div>
	<div class="card-footer flex justify-end">
		<div class="btn-group gap-4 text-token">
			<a href="/monsters/{id}/edit" class="btn variant-filled-secondary">Edit</a>
			<a href="/monsters/{id}" class="btn variant-filled-primary">View</a>
		</div>
	</div>
</div>
