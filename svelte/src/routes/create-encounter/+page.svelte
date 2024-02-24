<script>
	export let data;
</script>

<div class="p-4">
	<h1 class="h1">Create Encounter</h1>
	<form class="form">
		<label for="name" class="label">Encounter Name</label>
		<input id="name" class="input text-token" type="text" />
		<label for="description" class="label">Encounter Description</label>
		<textarea id="description" rows="4" class="textarea text-token" />
		<!-- TODO: monsters -->
		<button type="submit" class="btn variant-filled-primary">Submit</button>
	</form>
</div>
