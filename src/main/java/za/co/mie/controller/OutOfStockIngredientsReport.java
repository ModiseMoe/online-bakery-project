package za.co.mie.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.co.mie.bakeryDao.BakeryDaoIngredientsImpl;
import za.co.mie.bakeryService.BakeryServiceIngredientsImpl;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.Ingridient;
import java.util.Date;
import java.text.SimpleDateFormat;

@WebServlet(name = "OutOfStockIngredientsReport", urlPatterns = {"/OutOfStockIngredientsReport"})
public class OutOfStockIngredientsReport extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DocumentException {
        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        // Retrieve ingredient list from the service
        BakeryServiceIngredientsImpl bsci = new BakeryServiceIngredientsImpl(new BakeryDaoIngredientsImpl(con));
        List<Ingridient> ingredientList = bsci.getAllOutOfStockIngredients();

        // Set the content type to PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=ingredients.pdf");

        // Create the PDF document using iText
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        document.addTitle("Out of Stock Ingredients Report");

        // Add the date, time, and heading
        Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.BOLD);
        Font datetimeFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
        Paragraph heading = new Paragraph("Out Of Stock Ingredients Report", headingFont);
        Paragraph datetime = new Paragraph(getCurrentDateTime(), datetimeFont);
        datetime.setSpacingAfter(10f);
        document.add(heading);
        document.add(datetime);

        // Create the table
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Add table headers
        PdfPCell cell;
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Font.BOLD);

        cell = new PdfPCell(new Phrase("Name", headerFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Quantity on Hand", headerFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Minimum Stock Quantity", headerFont));
        table.addCell(cell);

        // Add ingredients to the table
        Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
        for (Ingridient ingredient : ingredientList) {
            cell = new PdfPCell(new Phrase(ingredient.getIngridientsName(), cellFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(String.valueOf(ingredient.getQuantityOnHand()), cellFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(String.valueOf(ingredient.getMinimumStockQuantity()), cellFont));
            table.addCell(cell);
        }

        // Add the table to the document
        document.add(table);
        document.close();
    }

// Helper method to get the current date and time
    private String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "Date and Time: " + dateFormat.format(new Date());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve ingredient list from the service
        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceIngredientsImpl bsci = new BakeryServiceIngredientsImpl(new BakeryDaoIngredientsImpl(con));
        List<Ingridient> ingredientList = bsci.getAllOutOfStockIngredients();

        // Set the ingredientList attribute
        request.setAttribute("ingredientsList", ingredientList);

        // Forward the request to the JSP file
        request.getRequestDispatcher("outOfStockIngredientsReport.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (DocumentException ex) {
            Logger.getLogger(IngredientsReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
