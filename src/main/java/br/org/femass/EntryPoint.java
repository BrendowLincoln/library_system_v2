package br.org.femass;

import br.org.femass.utils.router.Router;
import br.org.femass.utils.router.RouterService;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.stage.Stage;


public class EntryPoint extends Application {

    private final RouterService routerManager = RouterService.getInstance();

    @Override
    public void start(Stage stage) throws Exception {
        routerManager.navigateTo(Router.LOGIN_PAGE, stage);
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
