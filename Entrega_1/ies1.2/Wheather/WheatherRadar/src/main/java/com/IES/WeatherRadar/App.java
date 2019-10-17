package com.IES.WeatherRadar;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Hello world!
 *
 */
public class App 
{   
    private static final Logger LOGGER = LogManager.getLogger(App.class.getName());
    public static void main( String[] args )
    {   int cityCode=0;
        LOGGER.debug("Debug log message");
        LOGGER.info("Info log message");
        LOGGER.error("Error log message");
        if(args.length==0){
            System.err.println("Por favor indique o codigo da cidade!");
            LOGGER.error("Error getting city code");

            System.exit(0);
        } else {
            cityCode=Integer.parseInt(args[0]);
            LOGGER.info("City code:"+cityCode);

        }
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.ipma.pt/open-data/")
            .addConverterFactory(GsonConverterFactory. create ())
            .build();
        IpmaService service = retrofit.create(IpmaService.class);

        // get city code by the name
        if(args.length == 2) {
            Call<IpmaCities> callSyncCities = service.getAllCities();
            try {
                Response<IpmaCities> apiResponseCities = callSyncCities.execute();
                IpmaCities allCities = apiResponseCities.body();
                for(City c : allCities.getData()) {
                    if(c.getLocal().equals(args[1]))
                        cityCode = c.getGlobalIdLocal();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("City code " + cityCode);
        Call<IpmaCityForecast> callSync = service.getForecastForACity( cityCode );
        try {
            Response<IpmaCityForecast> apiResponse = callSync.execute();
            IpmaCityForecast forecast = apiResponse.body();
            CityForecast ct = forecast.getData().listIterator().next();
            String tempMax=ct.getTMax();
            String tempMin=ct.getTMin();
            String longitude=ct.getLongitude();
            String latitude=ct.getLatitude();
            String format = "%-20s%-20s%-20s%s%n";
            System.out.printf(format,"Latitude","Longitude","Max Temp","Min Temp");
            System.out.printf(format,latitude,longitude,tempMax,tempMin);
            LOGGER.info("Weather for city with code: "+cityCode+"=> Latitude:"+ latitude+", Longitude:"+ longitude+", temperatura máxima: "+ tempMax+", temperatura minima: "+ tempMin);

            System.exit(0);

                
        } catch (Exception ex) {
        ex.printStackTrace();
        LOGGER.error("Error getting forecast");
        }
    }
}
