package web.anew.model.translators;

import com.google.inject.Inject;
import web.anew.model.DefaultModel;
import web.anew.toolkit.FieldTools;
import web.anew.toolkit.PhotoTools;
import web.anew.model.models.FlatRentModel;
import web.exc.ExcTranslator;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * All rights belong to HighWay Company
 * User: Yuriy
 * Date: 28.10.13
 * Time: 6:15
 */
public class FlatRentFromExcel extends PhotoTools implements ExcTranslator<DefaultModel> {

    private final File photoSrc;

    @Inject
    private FieldTools fieldTools;

    @Inject
    private PhotoTools photoTools;

    public FlatRentFromExcel(File photoSrc) {
        this.photoSrc = photoSrc;
    }

    @Override
    public void initMeta(List<String> data) {

    }

    @Override
    public DefaultModel translate(List<String> cells) {
        FlatRentModel flatRent = new FlatRentModel();
        flatRent.address = cells.get(5);
        flatRent.area = fieldTools.doValue(cells.get(16));
        flatRent.category = "Квартиры";
        flatRent.subCategory = "Сдам";
        flatRent.rentTimeType = "На длительный срок";
        flatRent.city = "Москва";
        flatRent.description = cells.get(12);
        flatRent.floorCount = fieldTools.doValue(cells.get(7));
        flatRent.floorNum = fieldTools.doValue(cells.get(6));
        flatRent.metro = cells.get(3);
        flatRent.roomCount = fieldTools.doValue(cells.get(1));
        flatRent.price = fieldTools.doValue(cells.get(11));
        flatRent.identify = cells.get(0);
        flatRent.houseType = cells.get(8);
        flatRent.photo = photoTools.getPhotosFrom(photoSrc, fieldTools.doValue(cells.get(14)));
        return flatRent;
    }


}
