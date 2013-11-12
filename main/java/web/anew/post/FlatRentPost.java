package web.anew.post;

import web.anew.model.models.FlatRentModel;

import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: IceX
 * Date: 30.10.13
 * Time: 17:04
 * To change this template use File | Settings | File Templates.
 */
public class FlatRentPost extends AbstractPost<FlatRentModel> {

    @Override
    protected void setHeaders(FlatRentModel model) {
        setHeader("Ваше имя", model.userName);
        setHeader("Электронная почта", model.email);
        setHeader("Номер телефона", model.phone);
        setHeader("Выберите город", model.city);
        pageTools.sleep(1000);
        setHeader("Выберите станцию метро", model.metro);
        setHeader("Выберите категорию", model.category);
        setHeader("Тип объявления", model.subCategory);
        setHeader("Количество комнат", model.roomCount);
        setHeader("Срок аренды", model.rentTimeType);
        setHeader("Этаж", model.floorNum);
        setHeader("Этажей в доме", model.floorCount);
        setHeader("Площадь", model.area);
        setHeader("Адрес", model.address);
        setHeader("Описание объявления", model.description);
        setHeader("Арендная плата", model.price);
        setHeader("Тип дома", model.houseType);
    }

    @Override
    protected List<String> getPhotos(FlatRentModel model) {
        return Collections.unmodifiableList(model.photo);
    }
}
