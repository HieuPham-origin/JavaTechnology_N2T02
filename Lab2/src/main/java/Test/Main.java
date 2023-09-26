package Test;
import Implement.Product;
import Implement.ProductDAO;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        boolean flag = true;
        do{
            System.out.println("----Menu--  --");
            System.out.println("1. Read all product");
            System.out.println("2. Read detail of a product by id");
            System.out.println("3. Add a new product");
            System.out.println("4. Update a product");
            System.out.println("5. Delete a product by id");
            System.out.println("6. Exit");
            System.out.println("Nhap lua chon: ");
            int choice = scanner.nextInt();
            switch(choice){
                case 1:
                    ProductDAO.getInstance().readAll().forEach(p -> System.out.println(p));
                    break;
                case 2:
                    System.out.println("Nhap id: ");
                    int id = scanner.nextInt();
                    System.out.println(ProductDAO.getInstance().read(id));
                    break;
                case 3:
                    System.out.println("Nhap id: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Nhap ten: ");
                    String name = scanner.nextLine();
                    System.out.println("Nhap gia: ");
                    int price = scanner.nextInt();
                    System.out.println("Nhap so luong: ");
                    int amount = scanner.nextInt();
                    Product p = new Product(id, name, price, amount);
                    ProductDAO.getInstance().add(p);
                    break;
                case 4:
                    System.out.println("Nhap id hang muon cap nhat: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Nhap ten: ");
                    name = scanner.nextLine();
                    System.out.println("Nhap gia: ");
                    price = scanner.nextInt();
                    System.out.println("Nhap so luong: ");
                    amount = scanner.nextInt();
                    p = new Product(id, name, price, amount);
                    ProductDAO.getInstance().update(p);
                    break;
                case 5:
                    System.out.println("Nhap id mat hang muon xoa: ");
                    id = scanner.nextInt();
                    ProductDAO.getInstance().delete(id);
                    break;
                case 6:
                    flag = false;
                    break;
            }

        }while(flag);

    }



}
