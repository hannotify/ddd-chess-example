package ddd.domain.models;

import ddd.core.domain.AggregateIdentifier;

public record GameId(String gameId) implements AggregateIdentifier {}
