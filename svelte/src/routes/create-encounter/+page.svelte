<script lang="ts">
</script>

<div class="p-4">
	<h1 class="h1">Create Encounter</h1>
	<form class="form" method="post">
		<label for="name" class="label">Encounter Name</label>
		<input id="name" name="name" class="input text-token" type="text" />
		<label for="description" class="label">Encounter Description</label>
		<textarea id="description" name="description" rows="4" class="textarea text-token" />
		<!-- TODO: monsters -->
		<button type="submit" class="btn variant-filled-primary my-4">Submit</button>
	</form>
</div>
