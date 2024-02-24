let testData = false;

export function useTestData() {
	return testData;
}

export function toggleTestData() {
	testData = !testData;
}
