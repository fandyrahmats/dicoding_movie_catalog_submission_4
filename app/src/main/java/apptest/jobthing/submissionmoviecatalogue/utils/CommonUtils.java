package apptest.jobthing.submissionmoviecatalogue.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import apptest.jobthing.submissionmoviecatalogue.network.ApiEndPoint;


public final class CommonUtils {

    public static String dateFormatter(String date) {
        Date dt = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dt = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(dt.getTime());
    }

    public static String showingImage(String path) {
        return ApiEndPoint.ENDPOINT_IMAGE + "/"+"w185" + path;
    }

}
