package app.beelabs.com.codebase.support.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

/**
 * Created by arysuryawan on 2/15/18.
 */

public class ShareUtil {

    public static void shareImageOrText(ImageView view, String title, String body, Context context) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (view != null) {
            view.buildDrawingCache();
            Bitmap b = view.getDrawingCache();
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), b, title, null);
            Uri imageUri = Uri.parse(path);
            share.putExtra(Intent.EXTRA_STREAM, imageUri);
            share.setType("image/*");
            view.destroyDrawingCache();
        } else {
            share.setType("text/plain");
        }


        share.putExtra(Intent.EXTRA_SUBJECT, title);
        share.putExtra(Intent.EXTRA_TEXT, body);

        context.startActivity(share);


    }
}
