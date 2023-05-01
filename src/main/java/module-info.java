module com.mycompany.vrijemegui {
    requires javafx.controls;
    requires com.google.gson;
    
    opens com.mycompany.vrijemegui to com.google.gson;
    opens com.mycompany.beans to com.google.gson;
    
    exports com.mycompany.vrijemegui;
}
