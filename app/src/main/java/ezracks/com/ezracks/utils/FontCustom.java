package ezracks.com.ezracks.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by aipxperts on 8/4/16.
 */
public  class FontCustom {

    static public Typeface setFont(Context context)
    {
        Typeface font = Typeface.createFromAsset(context.getAssets(),"fonts/avenir-lt-std-45-book.otf");
        return font;
    }






}


