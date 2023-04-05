import java.sql.*;
import java.util.Scanner;

public class Student {
    public static void main(String[] args) {
        try (
                Connection cnt = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "");
        ) {
            do {
                Scanner in = new Scanner(System.in);
                System.out.println("1.Add student records\n2.Display student records\n3.Save\n4.Exit");
                System.out.printf("Choose: ");
                int chon = in.nextInt();
                in.nextLine();
                switch (chon) {
                    case 1:
                        System.out.printf("ID: ");
                        String ID = in.nextLine();
                        System.out.printf("Name: ");
                        String Name = in.nextLine();
                        System.out.printf("Address: ");
                        String Address = in.nextLine();
                        System.out.printf("Phone: ");
                        int Phone = in.nextInt();
                        in.nextLine();

                        String add = "insert into students values(?,?,?,?)";
                        PreparedStatement pstm = cnt.prepareStatement(add);
                        pstm.setString(1, ID);
                        pstm.setString(2, Name);
                        pstm.setString(3, Address);
                        pstm.setInt(4, Phone);
                        int checkAdd = pstm.executeUpdate();
                        if (checkAdd >= 1) {
                            System.out.println("số dòng ảnh hưởng: " + checkAdd);
                            System.out.println("thêm thành công");
                        } else {
                            System.out.println("số dòng ảnh hưởng: " + checkAdd);
                            System.out.println("Thêm thất bại");
                        }
                        break;

                    case 2:
                        String select = "select * from students";
                        PreparedStatement pstm2 = cnt.prepareStatement(select);
                        ResultSet rs = pstm2.executeQuery();
                        ResultSetMetaData rsMD = rs.getMetaData();
                        int countSelect = rsMD.getColumnCount();
                        for (int i = 1; i <= countSelect; ++i) {
                            System.out.printf("%-30s", rsMD.getColumnName(i));
                        }
                        System.out.println();
                        for (int i = 1; i <= countSelect; ++i) {

                            System.out.printf("%-30s", "(" + rsMD.getColumnClassName(i) + ")");
                        }
                        System.out.println();

                        while (rs.next()) {

                            for (int i = 1; i <= countSelect; ++i) {
                                System.out.printf("%-30s", rs.getString(i));
                            }
                            System.out.println();
                        }

                        break;
                    case 3:

                        break;

                    case 4:
                        System.out.println("Exiting...");
                        return;


                }

            } while (true);


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}