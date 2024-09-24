package ar.com.uala.twitter.domain.model;

import java.util.List;

public record Page<T>(
        List<T> items,
        long totalItems
) {
}
