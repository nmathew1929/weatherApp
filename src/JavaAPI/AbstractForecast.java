

package JavaAPI;

import org.json.JSONObject;

import java.io.Serializable;


public abstract class AbstractForecast extends AbstractResponse {
    /*
    JSON Keys
     */
    static final String JSON_FORECAST_LIST = "list";
    static final String JSON_MESSAGE = "message";
    static final String JSON_CITY = "city";
    static final String JSON_FORECAST_COUNT = "cnt";

    /*
    Instance variables
     */
    private final double message;

    private final City city;
    private final int forecastCount;

    /*
    Constructors
     */
    AbstractForecast() {
        super();

        this.message = Double.NaN;
        this.forecastCount = 0;
        this.city = null;
    }

    AbstractForecast(JSONObject jsonObj) {
        super(jsonObj);

        this.message = (jsonObj != null) ? jsonObj.optDouble(JSON_MESSAGE, Double.NaN) : Double.NaN;

        this.city = (jsonObj != null) ? new City(jsonObj.optJSONObject(JSON_CITY)) : null;

        this.forecastCount = (jsonObj != null) ? jsonObj.optInt(JSON_FORECAST_COUNT, 0) : 0;
    }

    /**
     * @return <code>true</code> if message is available, otherwise <code>false</code>.
     */
    public boolean hasMessage() {
        return (this.message != Double.NaN);
    }

    /**
     * @return <code>true</code> if count of forecasts is available, otherwise <code>false</code>.
     */
    public boolean hasForecastCount() {
        return (this.forecastCount != 0);
    }

    /**
     * @return <code>true</code> if message is available, otherwise <code>false</code>.
     */
    public boolean hasCityInstance() {
        return (this.city != null);
    }

    /**
     * @return Message if available, otherwise <code>Double.NaN</code>.
     */
    public double getMessage() {
        return this.message;
    }

    /**
     * @return Count of forecasts if available, otherwise <code>0</code>.
     */
    public int getForecastCount() {
        return this.forecastCount;
    }

    /**
     * @return City's instance if available, otherwise <code>null</code>.
     */
    public City getCityInstance() {
        return this.city;
    }


    public static class City implements Serializable {
        private static final String JSON_CITY_ID = "id";
        private static final String JSON_CITY_NAME = "name";
        private static final String JSON_CITY_COUNTRY_CODE = "country";
        private static final String JSON_CITY_POPULATION = "population";
        private static final String JSON_CITY_COORD = "coord";

        private final long cityID;
        private final String cityName;
        private final String countryCode;
        private final long population;

        private final Coord coord;

        City() {
            this.cityID = Long.MIN_VALUE;
            this.cityName = null;
            this.countryCode = null;
            this.population = Long.MIN_VALUE;

            this.coord = new Coord();
        }

        City(JSONObject jsonObj) {
            this.cityID = (jsonObj != null) ? jsonObj.optLong(JSON_CITY_ID, Long.MIN_VALUE) : Long.MIN_VALUE;
            this.cityName = (jsonObj != null) ? jsonObj.optString(JSON_CITY_NAME, null) : null;
            this.countryCode = (jsonObj != null) ? jsonObj.optString(JSON_CITY_COUNTRY_CODE, null) : null;
            this.population = (jsonObj != null) ? jsonObj.optLong(JSON_CITY_POPULATION, Long.MIN_VALUE) : Long.MIN_VALUE;

            JSONObject jsonObjCoord = (jsonObj != null) ? jsonObj.optJSONObject(JSON_CITY_COORD) : null;
            this.coord = (jsonObjCoord != null) ? new Coord(jsonObjCoord) : null;
        }

        public boolean hasCityCode() {
            return this.cityID != Long.MIN_VALUE;
        }

        public boolean hasCityName() {
            return this.cityName != null;
        }

        public boolean hasCountryCode() {
            return this.countryCode != null;
        }

        public boolean hasCityPopulation() {
            return this.population != Long.MIN_VALUE;
        }

        /**
         * @return <code>true</code> if Coord instance is available, otherwise <code>false</code>.
         */
        public boolean hasCoordInstance() {
            return coord != null;
        }

        public long getCityCode() {
            return this.cityID;
        }

        public String getCityName() {
            return this.cityName;
        }

        public String getCountryCode() {
            return this.countryCode;
        }

        public long getCityPopulation() {
            return this.population;
        }

        /**
         * @return Coord instance if available, otherwise <code>null</code>.
         */
        public Coord getCoordInstance() {
            return this.coord;
        }


        public static class Coord extends AbstractWeather.Coord {

            Coord() {
                super();
            }

            Coord(JSONObject jsonObj) {
                super(jsonObj);
            }
        }
    }


    public abstract static class Forecast extends AbstractWeather {
        Forecast() {
            super();
        }

        Forecast(JSONObject jsonObj) {
            super(jsonObj);
        }
    }
}
