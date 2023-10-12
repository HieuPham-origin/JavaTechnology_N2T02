package tdtu.servlets;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadServlet extends HttpServlet {
    private HashMap<String, String> userList;

    private final String UPLOAD_DIRECTORY = "WEB-INF/uploads";
    private final Integer MAX_FILE_SIZE = 1024 * 1024 * 5;
    private final Integer MAX_REQUEST_SIZE = 1024 * 1024 * 5 * 5;
    private final Integer MEMORY_THRESHOLD = 1024 * 1024;

    private final String[] EXTENSION_LIST = {"txt", "pdf", "rar", "doc", "docx", "img", "zip"};

    @Override
    public void init() throws ServletException {
        System.out.println("Starting Upload Servlet!!!");
        String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();
    }

    @Override
    public void destroy() {
        System.out.println("Deleting Servlet!!!");
    }

    private boolean validateFileName(String fileName) {
        String extension = FilenameUtils.getExtension(fileName);
        return Arrays.asList(EXTENSION_LIST).contains(extension);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getContentType() != null && request.getContentType().startsWith("multipart/")) {
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            try {
                String fileName = null;
                Boolean overwrite = false;
                Boolean error = false;
                Collection<Part> parts = request.getParts();

                for (Part part : parts) {
                    if (part.getContentType() == null) {
                        continue; // Skip non-file parts
                    }

                    if (part.getName().equals("fileName")) {
                        fileName = getSubmittedFileName(part);
                        if (!validateFileName(fileName)) {
                            request.setAttribute("message", "Unsupported file extension!!");
                            error = true;
                            break;
                        }
                    }

                    if (part.getName().equals("overwrite")) {
                        overwrite = part.getInputStream().available() > 0 && part.getInputStream().read() == 'Y';
                    }

                    if (fileName != null && !error) {
                        String uploadedFileName = getSubmittedFileName(part);
                        if (!validateFileName(uploadedFileName)) {
                            request.setAttribute("message", "Unsupported file extension!!");
                            break;
                        }
                        if (fileName == null) {
                            fileName = uploadedFileName;
                        }
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        if (storeFile.exists()) {
                            if (overwrite) {
                                FileUtils.copyInputStreamToFile(part.getInputStream(), storeFile);
                                request.setAttribute("message", "File has been overwritten!");
                                break;
                            } else {
                                request.setAttribute("message", "File already exists!");
                                break;
                            }
                        }
                        FileUtils.copyInputStreamToFile(part.getInputStream(), storeFile);
                        request.setAttribute("message", "File " + fileName + " has uploaded successfully!");
                    }
                }
            } catch (Exception ex) {
                request.setAttribute("message", "There was an error: " + ex.getMessage());
            }
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
        }
    }
    private String getSubmittedFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String header : contentDisposition.split(";")) {
            if (header.trim().startsWith("filename")) {
                return header.substring(header.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/upload.jsp").forward(request, response);
    }
}