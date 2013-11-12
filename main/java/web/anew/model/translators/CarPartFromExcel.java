package web.anew.model.translators;

import com.google.inject.Inject;
import web.anew.model.models.CartPartModel;
import web.anew.toolkit.FieldTools;
import web.anew.toolkit.PhotoTools;
import web.exc.ExcTranslator;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: IceX
 * Date: 30.10.13
 * Time: 17:51
 * To change this template use File | Settings | File Templates.
 */
public class CarPartFromExcel implements ExcTranslator<CartPartModel> {
    private final File photoSrc;

    @Inject
    private FieldTools fieldTools;

    @Inject
    private PhotoTools photoTools;

    public CarPartFromExcel(File photoSrc) {
        this.photoSrc = photoSrc;
    }

    @Override
    public void initMeta(List<String> data) {

    }

    @Override
    public CartPartModel translate(List<String> cells) {
        CartPartModel model = new CartPartModel();
        model.category = "Запчасти и аксессуары";
        model.header = fieldTools.cells(" ", cells, 1, 2, 3, 4, 8, 9);
        if (model.header.length() > 50) {
            model.header = fieldTools.cells(" ", cells, 1, 2, 3, 4, 9);
        }
        model.city = "Москва";
        model.subCategory = "Запчасти";
        model.subType = "Для автомобилей";
        model.description = fieldTools.cells(" ", cells, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        model.price = String.valueOf(Math.abs(Integer.parseInt(fieldTools.doValue(cells.get(16)))));
        model.photo = photoTools.getPhotosFrom(photoSrc, fieldTools.doValue(cells.get(0)));
        return model;
    }
}
