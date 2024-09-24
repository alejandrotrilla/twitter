package ar.com.uala.twitter.domain.model;

import java.util.Set;

public record User(
        String id,
        Set<String> following
) {
}
