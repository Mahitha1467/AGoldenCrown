package output;

import model.Ruler;
import util.StringUtil;

import java.util.List;
import java.util.stream.Collectors;

public class Printer {
    public void print(Ruler ruler) {
        System.out.println("Who is the ruler of Southeros?");
        System.out.println(ruler.getName() == null ? "None" : ruler.getName());
        System.out.println("Allies of Ruler?");
        System.out.println(ruler.getAllies() == null ? "None" : getFormattedAllies(ruler.getAllies()));
    }

    private String getFormattedAllies(List<String> allies) {
        String capitalizedString = allies.stream()
                .map(StringUtil::getCapitalize)
                .collect(Collectors.toList())
                .toString();
        return StringUtil.removeFirstAndLastChars(capitalizedString);
    }
}
