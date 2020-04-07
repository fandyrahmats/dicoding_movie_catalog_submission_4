package apptest.jobthing.submissionmoviecatalogue.network;

import apptest.jobthing.submissionmoviecatalogue.BuildConfig;

public final class ApiEndPoint {
    public static final String ENDPOINT_MOVIE = BuildConfig.BASE_URL
            + "/movie?api_key={api_key}&language={language}";

    public static final String ENDPOINT_TV_SHOW = BuildConfig.BASE_URL
            + "/tv?api_key={api_key}&language={language}";

    public static final String ENDPOINT_IMAGE = "https://image.tmdb.org/t/p";
}
