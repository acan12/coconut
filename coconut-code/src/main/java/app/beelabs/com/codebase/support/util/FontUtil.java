package app.beelabs.com.codebase.support.util;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

/**
 * Created by arysuryawan on 1/22/18.
 */

public class FontUtil {

    public static Typeface fontTypeface(Context context, String fontpath) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontpath);
        return typeface;
    }


    public static void fontFace(int resourceID, String fontPath, View view) {
        try {
            ((TextView) view.findViewById(resourceID)).setTypeface(FontUtil.fontTypeface(view.getContext(), fontPath));
        }catch (Exception e){
            return;
        }
    }

    public static void fontFace(int[] resourceIDs, String fontPath, View view) {
        for (int resourceID : resourceIDs) {
            fontFace(resourceID, fontPath, view);
        }
    }

    public static void fontFace(int[] resourceIDs, String fontPath, View[] views) {
        for(View view : views) {
            for (int resourceID : resourceIDs) {
                fontFace(resourceID, fontPath, view);
            }
        }
    }
}
