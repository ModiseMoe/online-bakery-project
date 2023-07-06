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
import za.co.mie.db.listener.DBManager;
import java.util.Date;
import java.text.SimpleDateFormat;
import za.co.mie.bakeryDao.BakeryDaoOrderImpl;
import za.co.mie.bakeryService.BakeryServiceOrderImpl;
import za.co.mie.model.Order;

@WebServlet(name = "OutstandingOrdersReport", urlPatterns = {"/OutstandingOrdersReport"})
public class OutstandingOrdersReport extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DocumentException {
        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        // Retrieve ingredient list from the service
        BakeryServiceOrderImpl bsci = new BakeryServiceOrderImpl(new BakeryDaoOrderImpl(con));
        List<Order> outstandingOrderList = bsci.getAllOutstandingOrders();

        // Set the content type to PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=Outstanding Orders.pdf");

        // Create the PDF document using iText
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        document.addTitle("Outstanding Orders Report");

        // Add the date, time, and heading
        Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.BOLD);
        Font datetimeFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
        Paragraph heading = new Paragraph("Outstanding Orders Report", headingFont);
        Paragraph datetime = new Paragraph(getCurrentDateTime(), datetimeFont);
        datetime.setSpacingAfter(10f);
        document.add(heading);
        document.add(datetime);

        // Create the table
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Add table headers
        PdfPCell cell;
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Font.BOLD);

        cell = new PdfPCell(new Phrase("Order Date", headerFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Customer ID", headerFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Order Status", headerFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Payment Status", headerFont));
        table.addCell(cell);

        // Add ingredients to the table
        Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
        for (Order order : outstandingOrderList) {
            cell = new PdfPCell(new Phrase(String.valueOf(order.getOrderDate()), cellFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(order.getUserId(), cellFont));
            table.addCell(cell);
            String lb = String.valueOf(order.isStatus());
            if (lb == "true") {
                lb = "Active";
                cell = new PdfPCell(new Phrase(lb, cellFont));
            } else {
                lb = "Cancelled";
                cell = new PdfPCell(new Phrase(lb, cellFont));
            }
            table.addCell(cell);

            String lb2 = String.valueOf(order.isPayment_status());
            if (lb2 == "true") {
                lb2 = "Payed";
                cell = new PdfPCell(new Phrase(lb2, cellFont));
            } else {
                lb2 = "Pending";
                cell = new PdfPCell(new Phrase(lb2, cellFont));
            }
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
        BakeryServiceOrderImpl bsci = new BakeryServiceOrderImpl(new BakeryDaoOrderImpl(con));
        List<Order> outstandingOrderList = bsci.getAllOutstandingOrders();

        // Set the ingredientList attribute
        request.setAttribute("outstandingOrderList", outstandingOrderList);

        // Forward the request to the JSP file
        request.getRequestDispatcher("outstandingOrdersReport.jsp").forward(request, response);
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
