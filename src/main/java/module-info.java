module edu.guilford {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens edu.guilford to javafx.fxml;
    exports edu.guilford;
}
