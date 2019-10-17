package com.IES.WeatherRadar;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * IPMA API service mapping
 */
public interface IpmaService {

    @GET("forecast/meteorology/cities/daily/{city_id}.json")
    Call<IpmaCityForecast> getForecastForACity(@Path("city_id") int cityId);

    @GET("http://api.ipma.pt/open-data/distrits-islands.json")
    Call<IpmaCities> getAllCities();

}
