package WebAPI.controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class DoctorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        PrintWriter out = null;
        try {
            out = resp.getWriter();
        } catch (IOException e) {
            resp.setStatus(500);
        }
        out.print("AAAAAAAA");
        out.flush();

        if (resp.getStatus() != 500) {
            resp.setStatus(200);
        }
    }
}
