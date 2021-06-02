package org.slipp.masil.games.domains.highrow;

public interface HighLowPlayingContextRepository {

    HighLowPlayingContext save(HighLowPlayingContext highLowPlayingContext);

    HighLowPlayingContext findById(Long id);
}
