module unibl.etf.pj2.epj2.epj2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens unibl.etf.pj2.epj2.epj2 to javafx.fxml;
    exports unibl.etf.pj2.epj2.epj2;
    exports unibl.etf.pj2.epj2.epj2.controller;
    opens unibl.etf.pj2.epj2.epj2.controller;
    opens unibl.etf.pj2.epj2.epj2.vehicle;
    exports unibl.etf.pj2.epj2.epj2.vehicle;
    opens unibl.etf.pj2.epj2.epj2.rental;
    exports unibl.etf.pj2.epj2.epj2.rental;
    opens unibl.etf.pj2.epj2.epj2.map;
    exports unibl.etf.pj2.epj2.epj2.map;
    opens unibl.etf.pj2.epj2.epj2.exception;
    exports unibl.etf.pj2.epj2.epj2.exception;
    opens unibl.etf.pj2.epj2.epj2.data;
    exports unibl.etf.pj2.epj2.epj2.data;
}