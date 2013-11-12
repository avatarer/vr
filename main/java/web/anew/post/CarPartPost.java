package web.anew.post;

import web.anew.model.models.CartPartModel;

import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: IceX
 * Date: 30.10.13
 * Time: 18:13
 * To change this template use File | Settings | File Templates.
 */
public class CarPartPost extends AbstractPost<CartPartModel> {

    @Override
    protected void setHeaders(CartPartModel model) {
        setHeader("Город", model.city);
        setHeader("Выберите категорию", model.category);
        setHeader("Вид товара", model.subCategory);
        setHeader("Тип товара", model.subType);
        setHeader("Название объявления", model.header);
        setHeader("Описание объявления", model.description);
        setHeader("Цена", model.price);
    }

    @Override
    protected List<String> getPhotos(CartPartModel model) {
        return Collections.unmodifiableList(model.photo);
    }
}
