package servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bestever.BestEverService;

@SuppressWarnings("serial")
public class BestEverServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(BestEverServlet.class.getName());

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String user = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");
        String key = getServletConfig().getInitParameter("key");
        String fromName = getServletConfig().getInitParameter("fromName");

        try {

            LOG.info("Starting best ever service");

            BestEverService service = new BestEverService();
            service.setKey(key);
            service.setPassword(password);
            service.setUser(user);
            service.setFromName(fromName);

            service.sendMail();

            LOG.info("Mail sent!");

        } catch (IllegalStateException e) {
            LOG.log(Level.SEVERE, "Error in service", e);
        }

    }

}
