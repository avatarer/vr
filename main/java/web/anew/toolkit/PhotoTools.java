package web.anew.toolkit;

import com.google.inject.Inject;
import web.anew.model.DefaultModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: IceX
 * Date: 30.10.13
 * Time: 17:54
 * To change this template use File | Settings | File Templates.
 */
public class PhotoTools {

    public List<String> getPhotosFrom(File photoSrcDir, String photoChild) {
        List<String> list = new ArrayList<>();
        if ((photoChild == null) || ("".equals(photoChild))) {
            return list;
        }
        File dir = new File(photoSrcDir, photoChild);
        if (!dir.isDirectory()) {
            return list;
        }
        for (File photo : dir.listFiles()) {
            if (photo.getName().toLowerCase().contains(".jpg")) {
                list.add(photo.toPath().toAbsolutePath().toString());
            }
        }
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        return list;
    }
}
