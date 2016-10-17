
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;


import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.DiskCacheUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ImageCacher {

    private static final ImageLoader loader = ImageLoader.getInstance();

    final static String PREFIX = "your_prefix";

    private static DisplayImageOptions options;
    private static Bitmap bmp;

    private static DiskCache getDiskCache() {
        return loader.getDiskCache();
    }

    /**
     * @see ImageDownloaderResource for extraForDownloader
     */
    private static DisplayImageOptions getDisplayOptions(Context context) {
        if (options == null || bmp == null)
            options = new DisplayImageOptions.Builder()
                    .extraForDownloader(loadBitmapIntoMemory(context, R.drawable.profile)) // Necessary resource to be shown if no bitmap found in cache with given ID,
                    // leave your ImageView source empty cuz this will handle it
                    .cacheInMemory(false)
                    .cacheOnDisk(false)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .bitmapConfig(Bitmap.Config.RGB_565).build();
        return options;
    }

    /**
     * init class in your Application class
     *
     * @param context
     */
    static void init(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheSize(400 * 1024 * 1024)
                .imageDownloader(new ImageDownloaderResource(context)) // Custom ImageDownloader resource for specific type
                .tasksProcessingOrder(QueueProcessingType.FIFO).build();

        loader.init(config);
    }

    /**
     * Asynchronous call for cached bitmap
     *
     * @param context
     * @param objectId unique identifier
     * @param view
     */
    public static void display(Context context, String objectId, ImageView view) {
        loader.displayImage(PREFIX + objectId, view, getDisplayOptions(context));
    }

    /**
     * Synchronous call for cached bitmap
     *
     * @param objectId a unique cache id needed for bitmap,such as your data model
     *                 e.g getCurrentUserId
     * @return saved bitmap from diskCache if exists, if not null
     */
    public static Bitmap get(String objectId) {
        File f = getDiskCache().get(PREFIX + objectId);
        return decodeFile(f);
    }

    /**
     * Synchronous saving process for given bitmap with cache id
     *
     * @param objectId unique cache id for bitmap
     * @param bitmap   bitmap will be cached
     */
    public static void put(String objectId, Bitmap bitmap) {
        try {
            getDiskCache().save(PREFIX + objectId, bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clear(String objectId) {
        DiskCacheUtils.removeFromCache(PREFIX + objectId, getDiskCache());
    }

    private static Bitmap decodeFile(File file) {
        if (file == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeFile(file.getPath(), options);
    }

    private static Bitmap loadBitmapIntoMemory(Context context, int id) {
        bmp = BitmapFactory.decodeResource(context.getResources(), id);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return bmp;
    }
}
