package dev.aidang.encounters.model;

public enum Die {
    D3,
    D4,
    D6,
    D8,
    D10,
    D12,
    D20,
    D100;

    public static Die fromInteger(int number) {
        return switch (number) {
            case 3 -> D3;
            case 4 -> D4;
            case 6 -> D6;
            case 8 -> D8;
            case 10 -> D10;
            case 12 -> D12;
            case 20 -> D20;
            case 100 -> D100;
            default -> throw new IllegalArgumentException("There is no d" + number);
        };
    }
}
