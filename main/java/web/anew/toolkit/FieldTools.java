package web.anew.toolkit;

import com.google.inject.Singleton;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: IceX
 * Date: 29.10.13
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class FieldTools {
    private final Pattern numRound = Pattern.compile("\\.\\d*");

    public String limitTools(String num, int max) {
        int value = Integer.parseInt(num);
        if (value > max) {
            return "> " + max;
        }
        return String.valueOf(value);
    }

    public String doValue(String s) {
        return numRound.matcher(s).replaceAll("");
    }

    public String cells(String delimiter, List<String> list, int... cells) {
        StringBuilder builder = new StringBuilder();
        for (int i : cells) {
            builder.append(list.get(i)).append(delimiter);
        }
        return builder.toString().trim();
    }
}
