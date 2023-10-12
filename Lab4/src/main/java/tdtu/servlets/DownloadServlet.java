package tdtu.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class DownloadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    //yeu cau 4
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String fileName = request.getParameter("file");
//
//        if (fileName == null || fileName.equals("")) {
//            response.setContentType("text/plain");
//            response.getWriter().write("File not found");
//            return;
//        }
//
//        String filePath = getServletContext().getRealPath("/WEB-INF/files/" + fileName);
//        Path file = Paths.get(filePath);
//
//        if (!Files.exists(file)) {
//            response.setContentType("text/plain");
//            response.getWriter().write("File not found");
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }
//
//        response.setContentType("application/octet-stream");
//        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
//
//        try (OutputStream outputStream = response.getOutputStream()) {
//            Files.copy(file, outputStream);
//        }
//    }
    // yeu cau 5
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fileName = request.getParameter("file");
        String speedParam = request.getParameter("speed");

        if (fileName == null || fileName.equals("")) {
            response.setContentType("text/plain");
            response.getWriter().write("File not found");
            return;
        }

        String filePath = getServletContext().getRealPath("/WEB-INF/files/" + fileName);
        Path file = Paths.get(filePath);

        if (!Files.exists(file)) {
            response.setContentType("text/plain");
            response.getWriter().write("File not found");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        // Set the maximum speed limit (in kilobytes per second)
        int speedLimit = 0;
        if (speedParam != null && !speedParam.equals("")) {
            try {
                speedLimit = Integer.parseInt(speedParam);
            } catch (NumberFormatException e) {
                // Handle invalid speed parameter value
            }
        }

        try (OutputStream outputStream = response.getOutputStream();
             FileInputStream inputStream = new FileInputStream(file.toFile())) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            long startTime = System.currentTimeMillis();
            long bytesSent = 0;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                // Check if a speed limit is set
                if (speedLimit > 0) {
                    long elapsedTime = System.currentTimeMillis() - startTime;
                    long expectedTime = (bytesSent / speedLimit) * 1000;
                    long remainingTime = expectedTime - elapsedTime;

//                    if (remainingTime > 0) {
//                        try {
////                            Thread.sleep(remainingTime);
//                        } catch (InterruptedException e) {
//                            // Handle interruption
//                        }
//                    }
                }

                outputStream.write(buffer, 0, bytesRead);
                bytesSent += bytesRead;
            }
        }
    }
}