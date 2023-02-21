package ddd.domain.models;

import ddd.core.domain.ValueObject;

public record Player(String name, ChessColor color) implements ValueObject {}
