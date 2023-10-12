package tdtu.servlets;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import tdtu.models.Product;

public class ProductServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private List<Product> products;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize the list of products
        products = new ArrayList<>();
        products.add(new Product(1, "Product 1", 100));
        products.add(new Product(2, "Product 2", 200));
        products.add(new Product(3, "Product 3", 300));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");

        // If idParam is not provided, return all products
        if (idParam == null || idParam.isEmpty()) {
            sendResponse(response, products);
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            Product product = getProductById(id);
            if (product != null) {
                sendResponse(response, product);
            } else {
                sendErrorResponse(response, "Product not found");
            }
        } catch (NumberFormatException e) {
            sendErrorResponse(response, "Invalid product id");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String name = request.getParameter("name");
        String priceParam = request.getParameter("price");

        if (idParam == null || idParam.isEmpty() || name == null || name.isEmpty() || priceParam == null
                || priceParam.isEmpty()) {
            sendErrorResponse(response, "Missing parameters");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            int price = Integer.parseInt(priceParam);

            if (getProductById(id) != null) {
                sendErrorResponse(response, "Product already exists");
                return;
            }

            Product newProduct = new Product(id, name, price);
            products.add(newProduct);
            sendResponse(response, newProduct);
        } catch (NumberFormatException e) {
            sendErrorResponse(response, "Invalid id or price");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String name = request.getParameter("name");
        String priceParam = request.getParameter("price");

        if (idParam == null || idParam.isEmpty() || name == null || name.isEmpty() || priceParam == null
                || priceParam.isEmpty()) {
            sendErrorResponse(response, "Missing parameters");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            int price = Integer.parseInt(priceParam);

            Product existingProduct = getProductById(id);
            if (existingProduct != null) {
                existingProduct.setName(name);
                existingProduct.setPrice(price);
                sendResponse(response, existingProduct);
            } else {
                sendErrorResponse(response, "Product not found");
            }
        } catch (NumberFormatException e) {
            sendErrorResponse(response, "Invalid id or price");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            sendErrorResponse(response, "Missing parameter: id");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            Product product = getProductById(id);
            if (product != null) {
                products.remove(product);
                sendResponse(response, "Product deleted successfully");
            } else {
                sendErrorResponse(response, "Product not found");
            }
        } catch (NumberFormatException e) {
            sendErrorResponse(response, "Invalid product id");
        }
    }

    private Product getProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    private void sendResponse(HttpServletResponse response, Object data) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        //List<Product> products = (List<Product>) data;
        PrintWriter out = response.getWriter();
        //out.print("Get product success {\"id\": 0, \"message\": \"Success\" }\n");
//        try {
//            for (int i = 0; i < products.size(); i++) {
//                Product product = products.get(i);
//                out.println("Product " + (i + 1));
//                StringBuilder jsonBuilder = new StringBuilder();
//                jsonBuilder.append("{\n");
//                jsonBuilder.append("  \"id\": ").append(product.getId()).append(",\n");
//                jsonBuilder.append("  \"name\": \"").append(product.getName()).append("\",\n");
//                jsonBuilder.append("  \"price\": ").append(product.getPrice()).append("\n");
//                // Add more properties as needed
//                jsonBuilder.append("}\n");
//
//                out.println(jsonBuilder.toString());
//
//                // Add a comma after each product except the last one
//                if (i < products.size() - 1) {
//                    out.println(",");
//                }
//            }
//
//            out.flush();
//        }catch (Exception e){
        out.print("{\"id\": 0, \"message\": \"Success\", \"data\": ");
        out.print(data.toString());
        out.print("}");
        out.flush();
        //}
    }

    private void sendErrorResponse(HttpServletResponse response, String errorMessage) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print("{\"id\": 1, \"message\": \"" +"Error\", \"data\": \"" + errorMessage + "\"}");
        out.flush();
    }
}
