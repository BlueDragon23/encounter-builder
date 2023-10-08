interface Encounter {
    id: string;
    name: string;
    description: string;
    enemies: any[];
}

export function load({params}): Encounter {
    return {
        id: params.id,
        name: "Test",
        description: "Test encounter",
        enemies: []
    } satisfies Encounter;
}