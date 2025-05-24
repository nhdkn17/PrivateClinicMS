module com.example.privateclinicms {
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
    requires java.sql;
    requires jbcrypt;

    opens com.privateclinicms to javafx.fxml;
    opens com.privateclinicms.controller to javafx.fxml;
    opens com.privateclinicms.controller.other to javafx.fxml;

    exports com.privateclinicms;
    exports com.privateclinicms.controller;
    exports com.privateclinicms.controller.other;
}