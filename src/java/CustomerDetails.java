
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/CustomerDetails"})
public class CustomerDetails extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        System.out.println("CustomerDetails PostMethod");
        
       
        
        HttpSession session=request.getSession();  
        String pid=(String)session.getAttribute("pid");
        int paraid=(Integer.valueOf(pid));
        System.out.println("**********pid********** :"+pid);
        
        
        //code to get parameters from dispara.jsp
        String gender = request.getParameter("gender");
        String profession = request.getParameter("prof");
        String age = request.getParameter("age");

        System.out.println("Paraid : " + paraid + " Gender : " + gender + " Age : " + age + " Profession : " + profession);
        
        //code to insert values in customer_details table
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.117:1521:orcl", "scott", "tiger");

            PreparedStatement stmt = con.prepareStatement("insert into customer_details values(?,?,?,?)");
            stmt.setInt(1, paraid);//1 specifies the first parameter in the query  
            stmt.setString(2, gender);
            stmt.setString(3, age);
            stmt.setString(4, profession);
            int i = stmt.executeUpdate();
            System.out.println(i + " records inserted");
            con.commit();
            con.close();

            RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
