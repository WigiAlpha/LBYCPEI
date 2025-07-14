module ph.edu.dlsu.lbycpei.portfolio {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens ph.edu.dlsu.lbycpei.portfolio to javafx.fxml;
    exports ph.edu.dlsu.lbycpei.portfolio;
    exports ph.edu.dlsu.lbycpei.portfolio.Controller;
    opens ph.edu.dlsu.lbycpei.portfolio.Controller to javafx.fxml;
}