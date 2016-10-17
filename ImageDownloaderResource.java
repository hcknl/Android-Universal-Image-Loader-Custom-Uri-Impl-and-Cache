
import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

class ImageDownloaderResource extends BaseImageDownloader {
    ImageDownloaderResource(Context context) {
        super(context);
    }

    @Override
    protected InputStream getStreamFromOtherSource(String imageUri, Object extra) throws IOException {
        if (imageUri.startsWith(ImageCacher.PREFIX)) {
            Bitmap bitmap = ImageCacher.get(imageUri);
            if (bitmap != null) {
                return getStream(bitmap);
            }
        }
        return getStream((Bitmap) extra);
    }

    private InputStream getStream(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
        byte[] bitmapdata = bos.toByteArray();
        return new ByteArrayInputStream(bitmapdata);
    }
}
