package com.greenmist.innkeeper.rest.hs;

import com.greenmist.innkeeper.android.dao.model.HSCard;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by geoff.powell on 2/5/17.
 */
public interface HSWebAPI {

    //TODO Support multiple locales
    @GET("/cards/{idOrName}")
    Observable<List<HSCard>> getCard(@Path("idOrName") String idOrName, @Query("collectible") int collectible, @Query("local") String local);
}
