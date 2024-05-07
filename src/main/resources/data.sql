-- Clear tables on each restart for testing
TRUNCATE encounter CASCADE;
TRUNCATE template_creature CASCADE;

insert into encounter (name, description)
values ('Elevator', 'This is the first fight where the players will encounter enemies');
insert into template_creature (
    name,
    description,
    hitpoints,
    creature_size,
    type,
    alignment,
    armor_class,
    challenge_rating,
    proficiency_bonus,
    dice_die,
    dice_count,
    speed_walk,
    speed_burrow,
    speed_climb,
    speed_fly,
    speed_swim,
    ability_strength,
    ability_dexterity,
    ability_constitution,
    ability_intelligence,
    ability_wisdom,
    ability_charisma
)
values (
    'Cranium Rat',
    'Mind flayers create cranium rats by bombarding rats with psionic energy. If enough cranium rats come together to form a swarm, they merge their minds into a single intelligence with the accumulated memories of all the swarm’s constituents. The rats become smarter as a result, and retain their heightened intelligence for as long as the swarm persists. The swarm also awakens latent psionic abilities implanted within each cranium rat by its mind flayer creators, bestowing upon the swarm psionic powers.\n\nA single cranium rat uses its natural telepathy to communicate hunger, fear, and other base emotions. A swarm of cranium rats communicating telepathically “speaks” as one creature, often referring to itself using the collective pronouns “we” and “us.”\n\nSome mind flayer colonies use cranium rats as spies. The rats invade communities and act as eyes and ears for the colony’s elder brain, transmitting their thoughts when they swarm and are within range of the elder brain’s telepathy.',
    2,
    'TINY',
    'Aberration',
    'Unaligned',
    12,
    0,
    2,
    'D4',
    1,
    30,
    0,
    0,
    0,
    0,
    2,
    14,
    10,
    4,
    11,
    8
);
select * from encounter;
insert into encounter_creature_ref (encounter, template_creature, count)
SELECT e.id, t.id, 4 FROM encounter as e, template_creature as t LIMIT 1;