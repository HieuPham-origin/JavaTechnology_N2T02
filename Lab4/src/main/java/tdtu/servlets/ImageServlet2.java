package tdtu.servlets;
import java.io.*;
import java.util.HashMap;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
public class ImageServlet2 extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String filePath = getServletContext().getRealPath("WEB-INF/images/tiger2.jpg");
        File imageFile = new File(filePath);
        response.setContentType("image/jpg");

        response.setContentLength((int) imageFile.length());
        response.setHeader("Content-Disposition", "attachment; filename=tiger2.jpg");

        FileInputStream in = new FileInputStream(imageFile);
        OutputStream out = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int numRead = 0;
        while ((numRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, numRead);
        }

        in.close();
        out.close();
    }
}
