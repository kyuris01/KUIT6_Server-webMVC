package jwp.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private Map<String, Controller> routes = new HashMap<>();

    @Override
    public void init() throws ServletException {
        registerRoutes();
    }


    public void registerRoutes() {
        routes.put("/", new HomeController());
        routes.put("/user/signup", new SignupController());
        routes.put("/user/login", new LoginController());
        routes.put("/user/logout", new LogoutController());
        routes.put("/user/list", new ListUserController());
        routes.put("/user/updateForm", new UpdateUserFormController());
        routes.put("/user/update", new UpdateUserController());
    }

    public void redirect(String view, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(view);
    }

    public void forward(String view, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(view);
        rd.forward(req, resp);
    }

    public void distributeToController(String uri, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Controller controller = routes.get(uri);
        if (controller == null) {
            System.out.println("적합한 경로가 아닙니다");
            return;
        }
        String view = controller.process(req, resp);
        if(view.startsWith("redirect:")) {
            String redirectView = view.substring(9);
            redirect(redirectView, req, resp);
        } else {
            forward(view, req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        System.out.println("get uri : " + uri);
        distributeToController(uri, req, resp);
//        Controller controller = routes.get(uri);
//        if (controller == null) {
//            System.out.println("적합한 경로가 아닙니다");
//            return;
//        }
//        String view = controller.process(req, resp);
//        System.out.println("view : " + view);
//        if(view.startsWith("redirect:")) {
//            String redirectView = view.substring(9);
//            redirect(redirectView, req, resp);
//        } else {
//            forward(view, req, resp);
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        System.out.println("post uri : " + uri);
        distributeToController(uri, req, resp);
    }
}
