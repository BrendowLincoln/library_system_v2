package br.org.femass.utils.router;

import br.org.femass.controllers.ControllerBase;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class RouterService {

    private static RouterService routerManager = null;


    private RouterService() { }


    public static RouterService getInstance() {
        if(routerManager == null) {
            routerManager = new RouterService();
        }

        return routerManager;
    }


    public void navigateTo(String router, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        URL location = getClass().getResource(router);

        if(location == null) {
            navigateTo(Router.NOT_FOUND_PAGE, stage);
            return;
        }

        loader.setLocation(location);
        loader.setBuilderFactory(new JavaFXBuilderFactory());

        Parent root = loader.load(location.openStream());
        ControllerBase controller = loader.getController();
        controller.setStage(stage);


        Scene scene = new Scene(root);
        scene.getRoot().setStyle("-fx-font-family: 'sans-serif'");
        stage.setScene(scene);
        stage.show();
    }
}
