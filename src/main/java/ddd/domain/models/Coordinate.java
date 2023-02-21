package ddd.domain.models;

import ddd.core.domain.ValueObject;

public record Coordinate(int x, int y) implements ValueObject {}
