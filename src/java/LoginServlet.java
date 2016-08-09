
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {
    //post method
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        out.println("dopost");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        //code to get parameters from index.jsp page
        String n = request.getParameter("username");
        String p = request.getParameter("userpass");

        // validation for username and password
        if (n.equalsIgnoreCase("admin") && p.equalsIgnoreCase("admin")) {
            
            //displayParagraphTableRecord is a method which returns single row object
            ArrayList<String> responsepara = (ArrayList<String>) displayParagraphTableRecord();
            out.println("" + responsepara);
            //System.out.println("responsepara1*****------"+responsepara1);
            request.setAttribute("dispara", responsepara);

            ArrayList<String> posts = (ArrayList<String>) request.getAttribute("dispara");
            for (int i = 0; i < posts.size(); i++) {
                System.out.println("Array List values " + posts.get(i));

            }

            RequestDispatcher rd = request.getRequestDispatcher("dispara.jsp");
            rd.forward(request, response);
        } else {
            out.print("Sorry username or password error");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.include(request, response);
        }

        // out.close();
    }

    
    
    //code to retrieve row from a table paragraph 
    public List<String> displayParagraphTableRecord() {

        System.out.println("display paragraph");
        String paragraph = null;
        String inipara = null;
        String finalpara = null;
        String destpara = null;
        String pid = "";
        ArrayList<String> pararecord = new ArrayList<String>();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@192.168.6.117:1521:orcl", "scott", "tiger");

            PreparedStatement ps = con.prepareStatement("select * from paragraph where id=?");
            ps.setInt(1, 4);

            ResultSet rs = ps.executeQuery();

            /* Printing result */
            while (rs.next()) {
                System.out.println("" + rs.getInt("id") + "********" + rs.getString("para"));
                pid = String.valueOf(rs.getInt("id"));
                paragraph = rs.getString("para");
                out.println("Paragraph" + paragraph);
            }
            
            //code to track and replace the placeholders 
            if (paragraph.contains("@")) {
                inipara = paragraph.replace("@", "<input type='text' name='gender' id='gender'/>");
                System.out.println("******inipara***** \n" + inipara);
            }
            if (inipara.contains("$")) {
                finalpara = inipara.replace("$", "<select name='age' id='age'>"
                        + "<option value='18-25'>18-25</option>"
                        + "<option value='26-40'>26-40</option>"
                        + "</select>");

                System.out.println("******finalpara****** " + finalpara);
            }
            if (finalpara.contains("#")) {
                destpara = finalpara.replace("#", "<input type='text' name='prof' id='prof'/>");
                System.out.println("******inipara***** \n" + inipara);
            }

            //System.out.println("******Paragraph****** \n"+paragraph);
            con.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        } finally {
            //out.close();
        }
        System.out.println("pid---" + pid);
        System.out.println("destpara----" + destpara);
        pararecord.add(pid);
        pararecord.add(destpara);
        return pararecord;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        out.println("doget");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //displayParagraphTableRecord is a method which returns single row object
        ArrayList<String> responsepara = (ArrayList<String>) displayParagraphTableRecord();
        out.println("" + responsepara);
        //System.out.println("responsepara1*****------"+responsepara1);
        request.setAttribute("dispara", responsepara);

        ArrayList<String> posts = (ArrayList<String>) request.getAttribute("dispara");
        for (int i = 0; i < posts.size(); i++) {
            System.out.println("Array List values " + posts.get(i));

        }

        RequestDispatcher rd = request.getRequestDispatcher("dispara.jsp");
        rd.forward(request, response);

        // out.close();
    }
}
