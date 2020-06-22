package sample.informationGui;

import javafx.beans.property.SimpleStringProperty;

public class properity4TableviewURLS {
    private SimpleStringProperty url;

    public properity4TableviewURLS(SimpleStringProperty url) {
        this.url = url;
    }

    public String getUrl() {
        return url.get();
    }

    public SimpleStringProperty urlProperty() {
        return url;
    }
}
